USE BANCOSOL_DB;

-- Desactivar la validación de claves foráneas temporalmente para evitar bloqueos
SET FOREIGN_KEY_CHECKS = 0;

-- 1. Insertar/Asegurar los Roles
TRUNCATE TABLE ROL;
INSERT INTO ROL (id_rol, nombre, descripcion) VALUES
(1, 'ADMIN', 'Administrador global del sistema con acceso total'),
(2, 'RESP_ENTIDAD', 'Responsable de una entidad o colaborador externo'),
(3, 'COORDINADOR', 'Coordinador general de las campañas de recogida'),
(4, 'RESP_TIENDA', 'Responsable o gerente de un supermercado/tienda específico'),
(5, 'CAPITAN', 'Responsable de la logistica cercana a las tiendas');

-- 2. Insertar Tipos de Campaña
TRUNCATE TABLE TIPO_DE_CAMPANA;
INSERT INTO TIPO_DE_CAMPANA (id_tipo_campana, nombre) VALUES
(1, 'Gran Recogida'),
(2, 'Operación Kilo');

-- 3. Insertar Cadenas de Supermercados
TRUNCATE TABLE CADENAS;
INSERT INTO CADENAS (id_cadena, nombre, persona_contacto, telefono_contacto) VALUES
(1, 'Mercadona', 'Juan Roig', '600123456'),
(2, 'Carrefour', 'Marie Dubois', '611234567'),
(3, 'Lidl', 'Hans Müller', '622345678');

-- 4. Insertar Entidades Colaboradoras
TRUNCATE TABLE COLABORADOR;
INSERT INTO COLABORADOR (id_colaborador, nombre_entidad, email, contacto_nom, contacto_tlf, domicilio, localidad, zona_geografica, observaciones, cod_postal, estado) VALUES
(1, 'Cruz Roja Málaga', 'malaga@cruzroja.es', 'María García', '633456789', 'Calle Principal 12', 'Málaga', 'MÁLAGA CENTRO', 'Dispone de 15 voluntarios activos para el fin de semana.', '29012', 1),
(2, 'Asociación Solidaria', 'contacto@solidaria.org', 'Pedro López', '644567890', 'Av. de la Solidaridad 4', 'Torremolinos', 'TORREMOLINOS', 'Pendiente de confirmar horario de la reunión informativa.', '29620', 1),
(3, 'A0362-AYUNTAMIENTO DE ALMACHAR', 'contacto@almachar.es', 'María Vallejo', '666 666 666', 'Calle Almeria, 14', 'Almachar', 'RINCON DE LA VICTORIA', '10 voluntarios. Formación Jueves 30, a las 19:45', '29718', 1);

-- 5. Insertar Campañas
TRUNCATE TABLE CAMPANA;
INSERT INTO CAMPANA (id_campana, id_tipo_campana, fecha, activo) VALUES
(1, 1, 20261128, 1),
(2, 2, 20260615, 0);

-- 6. Insertar Tiendas (CORREGIDO: Ahora incluye la columna 'localidad' requerida por el esquema)
TRUNCATE TABLE TIENDA;
INSERT INTO TIENDA (id_tienda, id_cadena, nombre, direccion, localidad, cod_postal) VALUES
(1, 1, 'Mercadona Vialia', 'Explanada de la Estación s/n', 'Málaga Centro', '29002'),
(2, 1, 'Mercadona Larios', 'Calle Larios 5', 'Málaga Centro', '29005'),
(3, 2, 'Carrefour Alameda', 'Av. de Andalucía s/n', 'Málaga Pasaje', '29007'),
(4, 3, 'Lidl El Palo', 'Av. Juan Sebastián Elcano 45', 'El Palo (Málaga)', '29017');

-- 7. Insertar Usuarios
TRUNCATE TABLE USUARIO;
INSERT INTO USUARIO (id_usuario, id_rol, email, password, nombre_completo, activo) VALUES
(1, 1, 'admin@bancosol.org', 'admin123', 'Carlos Administrador', 1),
(2, 2, 'responsable.entidad@cruzroja.es', 'entidad123', 'Ana Martínez', 1),
(3, 3, 'coordinador1@bancosol.org', 'coord123', 'Luis Coordinador', 1),
(4, 4, 'responsable.vialia@mercadona.es', 'tienda123', 'Sofía Responsable Tienda', 1),
(5, 5, 'capitan@bancosol.org', 'capitan', 'Capitan Sanchez', 1);

-- 8. Insertar Voluntarios
TRUNCATE TABLE VOLUNTARIO;
INSERT INTO VOLUNTARIO (id_voluntario, id_colaborador, nombre, telefono, email) VALUES
(1, 1, 'Juan Pérez', '655111222', 'juan.perez@email.com'),
(2, 2, 'Elena Gómez', '655333444', 'elena.gomez@email.com'),
(3, 3, 'Marcos Ruiz', '655555666', 'marcos.ruiz@email.com');

-- 9. Relaciones auxiliares
TRUNCATE TABLE CAMPANA_CADENAS;
INSERT INTO CAMPANA_CADENAS (id_campana, id_cadena) VALUES (1, 1), (1, 2), (1, 3), (2, 1);

TRUNCATE TABLE TIENDA_CAMPANA;
INSERT INTO TIENDA_CAMPANA (id_tienda, id_campana) VALUES (1, 1), (1, 2), (3, 1), (4, 1);

TRUNCATE TABLE USUARIO_TIENDA;
INSERT INTO USUARIO_TIENDA (id_usuario, id_tienda) VALUES (4, 1);

TRUNCATE TABLE USUARIO_COLABORADOR;
INSERT INTO USUARIO_COLABORADOR (id_usuario, id_colaborador) VALUES (3, 1);
INSERT INTO USUARIO_COLABORADOR (id_usuario, id_colaborador) VALUES (3, 2);
INSERT INTO USUARIO_COLABORADOR (id_usuario, id_colaborador) VALUES (3, 3);

-- 10. Asignación de Turnos e Incidencias (CORREGIDO: Incluye id_campana en la posición correspondiente)
TRUNCATE TABLE ASIGNACION_TURNOS;
INSERT INTO ASIGNACION_TURNOS (id_asignacion, id_campana, id_tienda, id_voluntario, id_colaborador, dia, franja, hora_inicio, hora_fin) VALUES
(1, 1, 1, 1, 1, '2026-11-28', 'MAÑANA', '09:00:00', '14:00:00'),
(2, 2, 1, 2, 2, '2026-11-28', 'TARDE', '14:00:00', '19:00:00');

TRUNCATE TABLE INCIDENCIAS;
INSERT INTO INCIDENCIAS (id_incidencia, id_asignacion, descripcion) VALUES
(1, 1, 'El voluntario notificó un retraso de 15 minutos debido al tráfico, pero cumplió su turno.');

-- Volver a activar la verificación de claves foráneas
SET FOREIGN_KEY_CHECKS = 1;