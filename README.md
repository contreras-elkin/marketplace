# Agrolazo Marketplace

Marketplace agrícola para Colombia que conecta a **productores** con **compradores**. Los productores publican sus productos (con unidad de venta, precio, stock y municipio) y los compradores los adquieren pagando en línea a través de **Wompi**.

> Estado: en desarrollo. La infraestructura (base de datos y migraciones) está lista; la API se está construyendo por módulos.

## Arquitectura

La API es un **monolito modular** en Spring Boot. Cada módulo es dueño de sus datos y de su esquema en la base de datos, y los demás módulos solo pueden usarlo a través de su interfaz pública. Nunca acceden a sus entidades ni a sus repositorios.

| Módulo | Esquema | Responsabilidad | Interfaz pública |
|---|---|---|---|
| `auth` | `auth` | Usuarios y su rol (`PRODUCER` / `BUYER`) | `ModuleApiAuth` |
| `catalog` | `catalog` | Productos, precio y stock | `ModuleApiCatalog` |
| `transactions` | `transactions` | Órdenes e intentos de pago | — |

Dentro de cada módulo:

```
<modulo>/
├── ModuleApi<Modulo>.java   interfaz expuesta a otros módulos + records públicos
├── aplication/              servicios (casos de uso)
├── domain/                  entidades JPA
├── infraestructure/         repositorios e integraciones externas (Wompi)
└── web/                     controladores REST
```

### Modelo de compra

- Una **orden** se relaciona con un producto y guarda un *snapshot* del precio al momento de comprar: precio unitario, cantidad y monto total en centavos.
- Una orden puede tener **varios intentos de pago** (`payments`). Cada intento tiene su propia referencia única, porque Wompi no permite reutilizar referencias.
- Los montos siempre se manejan como enteros en centavos de COP.

### Flujo de pago (Wompi Widget)

```
1. Front ──crear orden (producto, cantidad)──▶ API
                                               crea Order + Payment PENDING
                                               calcula la firma de integridad
2. Front ◀── llave pública, monto, moneda, referencia, firma
3. Front abre el Widget de Wompi ──▶ Wompi    (el usuario acepta términos y paga)
4. Wompi ──webhook──▶ API                      confirmación real del pago
5. Wompi ──redirige──▶ Front                   solo informativo
```

La firma de integridad (`SHA-256(referencia + monto + moneda + secreto)`) se calcula únicamente en el backend. El estado de un pago solo se actualiza por el webhook, nunca por la redirección.

## Stack

- Java 21 · Spring Boot 4 (Web MVC, Data JPA)
- PostgreSQL 17
- Liquibase (migraciones en SQL)
- Docker Compose
- Wompi (pasarela de pagos, entorno sandbox)

## Estructura del repositorio

```
.
├── api/                     API Spring Boot
├── migrations/
│   ├── db.changelog-master.yaml
│   └── ddl/
│       ├── 01-schemas/      esquemas por módulo
│       ├── 02-tables/       tablas agrupadas por módulo
│       └── 03-relation/     llaves foráneas e índices
├── compose.yml              Postgres + Liquibase
└── .env.example             plantilla de variables de entorno
```

## Puesta en marcha

### Requisitos

- Docker y Docker Compose
- Java 21 (para la API)

### 1. Variables de entorno

```bash
cp .env.example .env
```

Completa las llaves de Wompi con las de tu cuenta sandbox. Un único `.env` en la raíz lo usan Docker Compose y la API.

| Variable | Uso |
|---|---|
| `POSTGRES_USER`, `POSTGRES_PASSWORD`, `POSTGRES_DB` | Credenciales de la base de datos |
| `POSTGRES_HOST`, `POSTGRES_PORT` | Dónde se conecta la API (por defecto `localhost:5432`) |
| `WOMPI_PUBLIC_KEY` | Llave pública; puede llegar al frontend |
| `WOMPI_INTEGRITY_SECRET` | Firma de integridad. **Solo servidor** |
| `WOMPI_EVENTS_SECRET` | Validación del webhook. **Solo servidor** |

### 2. Base de datos y migraciones

```bash
docker compose up -d
```

Levanta Postgres (publicado solo en `127.0.0.1`) y ejecuta las migraciones de Liquibase. El contenedor de Liquibase termina después de aplicarlas. Para revisar el resultado:

```bash
docker compose logs liquibase
```

### 3. API

```bash
cd api
./mvnw spring-boot:run
```

La API lee el `.env` de la raíz y valida al arrancar que las entidades coincidan con el esquema creado por Liquibase (`ddl-auto=validate`).

## Convenciones de migraciones

- Los archivos SQL usan el formato *formatted SQL* de Liquibase (`--liquibase formatted sql` + `--changeset autor:id`).
- Un changeset ya aplicado **no se edita**: los cambios se hacen con un archivo nuevo.
- Cada archivo nuevo se registra en `db.changelog-master.yaml`, respetando el orden esquemas → tablas → relaciones.
