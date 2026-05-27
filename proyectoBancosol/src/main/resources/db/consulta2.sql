USE `bancosol_db`;

-- =======================================================
-- PASO 1: BORRAR DATOS ANTIGUOS (Orden estricto de claves foráneas)
-- =======================================================
DELETE FROM incidencias;
DELETE FROM asignacion_turnos;
DELETE FROM voluntario;
DELETE FROM colaborador;
DELETE FROM tienda_campana;
DELETE FROM campana;
DELETE FROM tipo_de_campana;
DELETE FROM tienda;
DELETE FROM cadenas;

-- =======================================================
-- PASO 2: INSERTAR DATOS MAESTROS BASE
-- =======================================================

-- 1. Cadena
INSERT INTO cadenas VALUES (1, 'Super del Sur', NULL, NULL);

-- 2. Las 4 Tiendas
INSERT INTO tienda VALUES 
(1, 1, 'Mercadona Centro', 'Calle Larios, 4', '29005'),
(2, 1, 'Carrefour Alameda', 'Avenida de Andalucía, 10', '29007'),
(3, 1, 'Lidl El Palo', 'Avenida Juan Sebastián Elcano, 75', '29017'),
(4, 1, 'Dia Vélez-Málaga', 'Calle Camino de Málaga, 12', '29700');

-- 3. Tipos de Campaña
INSERT INTO tipo_de_campana VALUES (1, 'Gran Recogida'); 
INSERT INTO tipo_de_campana VALUES (2, 'Primavera');       

-- 4. Campañas
INSERT INTO campana VALUES (1, 1, 2026, 1);
INSERT INTO campana VALUES (2, 2, 2026, 1);

-- 5. Relación Tienda-Campaña
INSERT INTO tienda_campana VALUES (1, 1), (1, 2), (2, 1), (3, 2), (4, 1);

-- =======================================================
-- PASO 3: COLABORADORES Y VOLUNTARIOS (Con datos obligatorios)
-- =======================================================

-- 1. Insertar Colaboradores
INSERT INTO colaborador (id_colaborador, nombre_entidad, email) VALUES 
(1, 'Cruz Roja Málaga', 'info@cruzroja.es'),
(2, 'Ayuntamiento Vélez', 'contacto@velez.es');

-- 2. Insertar Voluntarios (Incluyendo teléfono y email obligatorios)
INSERT INTO voluntario (id_voluntario, nombre, id_colaborador, telefono, email) VALUES 
(1, 'Ghazghkull', 1, '600111222', 'orks@example.com'),
(2, 'Konrad', 2, '600333444', 'dominusnox@example.com');

-- =======================================================
-- PASO 4: TURNOS E INCIDENCIAS
-- =======================================================

-- 3. Insertar Asignaciones de Turnos (id, id_campana, id_tienda, id_voluntario, dia, franja, hora_inicio, hora_fin)
INSERT INTO asignacion_turnos VALUES 
(1, 1, 1, 1, 'Sábado', 'Mañana', '09:00:00', '14:00:00'),
(2, 1, 1, 2, 'Sábado', 'Tarde', '14:00:00', '19:00:00');

-- 4. Insertar Incidencia (Vinculada al turno 1)
INSERT INTO incidencias VALUES (1, 1, 'El voluntario llegó tarde.');