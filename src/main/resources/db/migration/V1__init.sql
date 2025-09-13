-- ROLES
create table roles (
                       id bigserial primary key,
                       nombre varchar(50) not null,
                       descripcion varchar(255)
);

-- USUARIO (simulado)
create table usuario (
                         id bigserial primary key,
                         nombre varchar(120) not null,
                         apellido varchar(120),
                         num_documento varchar(60),
                         celular varchar(40),
                         fecha_nacimiento date,
                         correo varchar(160) not null unique,
                         clave varchar(255),
                         id_rol bigint references roles(id)
);

-- ESTADO (si decides mantener catálogo de estados)
create table estado (
                        id bigserial primary key,
                        nombre varchar(30) not null,        -- CREATED/PAID/SHIPPED/DELIVERED/CANCELLED
                        descripcion varchar(255)
);

-- CATEGORIA
create table categoria (
                           id bigserial primary key,
                           nombre varchar(120) not null,
                           descripcion varchar(255)
);

-- PRODUCTO (sin stock aquí; stock separado es mejor)
create table producto (
                          id bigserial primary key,
                          id_categoria bigint references categoria(id),
                          nombre varchar(255) not null,
                          descripcion varchar(255),
                          precio double precision not null,
                          url_imagen varchar(255),
                          created_at date,
                          updated_at date
);

-- STOCK por producto (locking optimista)
create table product_stock (
                               product_id bigint primary key references producto(id),
                               available_qty int not null check (available_qty >= 0),
                               version bigint not null default 0
);

-- PEDIDO
create table pedido (
                        id bigserial primary key,
                        id_usuario bigint not null references usuario(id),
                        monto_total double precision not null default 0,
                        estado bigint not null references estado(id),  -- si prefieres varchar, cambia a varchar(20)
                        direccion_envio varchar(255),
                        metodo_de_pago varchar(60),
                        fecha_pedido date,
                        fecha_final date,
                        version bigint not null default 0            -- @Version para optimistic locking
);
create index idx_pedido_usuario on pedido(id_usuario);
create index idx_pedido_estado on pedido(estado);
create index idx_pedido_fecha on pedido(fecha_pedido desc);

-- ITEMS del pedido (relación N y snapshot de precios)
create table pedidos_productos (
                                   id bigserial primary key,                           -- si prefieres, usa PK compuesta (id_pedido, line_number)
                                   id_pedido bigint not null references pedido(id) on delete cascade,
                                   id_producto bigint not null references producto(id),
                                   cantidad int not null check (cantidad > 0),
                                   precio_total double precision not null        -- cantidad * precio_unitario del momento
);
create index idx_pp_pedido on pedidos_productos(id_pedido);
create index idx_pp_producto on pedidos_productos(id_producto);

-- TRAZABILIDAD (historial de estados)
create table trazabilidad (
                              id bigserial primary key,
                              id_pedido bigint not null references pedido(id) on delete cascade,
                              id_usuario bigint references usuario(id),       -- quién cambió el estado (opcional)
                              fecha timestamp not null default now(),
                              estado_anterior varchar(20),
                              estado_nuevo varchar(20) not null
);
create index idx_trz_pedido on trazabilidad(id_pedido);

-- Roles
insert into roles(nombre, descripcion) values
                                           ('ADMIN', 'Administrador de todo'),
                                           ('USER', 'Usuario que puede hacer pedidos');

-- Estados si usas catálogo
insert into estado(nombre) values
                               ('CREATED'),
                               ('PAID'),
                               ('SHIPPED'),
                               ('DELIVERED'),
                               ('CANCELLED');

-- Usuario normal
insert into usuario(nombre, apellido, num_documento, celular, fecha_nacimiento, correo, clave, id_rol)
select 'Juan',
       'Pérez',
       '12345678',
       '3001234567',
       '1995-05-15',
       'user@test.com',
       '$2a$10$bF95jhosXHG0eptY.AM/9uYXLoIZ2zm..k/Z73ZvheKBlo9.yn9fG',   -- user123
       id
from roles where nombre = 'USER';

-- Usuario admin
insert into usuario(nombre, apellido, num_documento, celular, fecha_nacimiento, correo, clave, id_rol)
select 'Ana',
       'Gómez',
       '87654321',
       '3017654321',
       '1990-03-20',
       'admin@test.com',
       '$2a$10$FEIMQrKNyYznikIwmhdSHuvvQMFTjpmJduGDgKilZiT2uEBfw3qVa',  -- admin123
       id
from roles where nombre = 'ADMIN';

-- V2__seed_productos.sql (ejemplo)
insert into categoria (nombre, descripcion) values ('Electrónica','');

-- H2 autogen id = 1 para categoria anterior
insert into producto (id_categoria, nombre, descripcion, precio, url_imagen, created_at, updated_at)
values (1, 'Audífonos', 'Over-ear', 120000, null, now(), now()); -- id=1

insert into producto (id_categoria, nombre, descripcion, precio, url_imagen, created_at, updated_at)
values (1, 'Teclado', 'Mecánico', 350000, null, now(), now());   -- id=2

insert into product_stock(product_id, available_qty, version) values
                                                                  (1, 50, 0),
                                                                  (2, 15, 0);