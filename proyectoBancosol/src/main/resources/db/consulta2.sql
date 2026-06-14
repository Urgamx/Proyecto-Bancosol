USE BANCOSOL_DB;

-- Desactivar la validación de claves foráneas temporalmente para evitar bloqueos
SET FOREIGN_KEY_CHECKS = 0;

-- =========================================================================
-- 1. INSERTAR / ASEGURAR LOS ROLES
-- =========================================================================
TRUNCATE TABLE ROL;
INSERT INTO ROL (id_rol, nombre, descripcion) VALUES
(1, 'ADMIN', 'Administrador global del sistema con acceso total'),
(2, 'RESP_ENTIDAD', 'Responsable de una entidad o colaborador externo'),
(3, 'COORDINADOR', 'Coordinador general de las campañas de recogida'),
(4, 'RESP_TIENDA', 'Responsable o gerente de un supermercado/tienda específico'),
(5, 'CAPITAN', 'Responsable de la logistica cercana a las tiendas');

-- =========================================================================
-- 2. INSERTAR TIPOS DE CAMPAÑA
-- =========================================================================
TRUNCATE TABLE TIPO_DE_CAMPANA;
INSERT INTO TIPO_DE_CAMPANA (id_tipo_campana, nombre) VALUES
(1, 'Gran Recogida'),
(2, 'Operación Kilo');

-- =========================================================================
-- 3. INSERTAR CADENAS DE SUPERMERCADOS
-- =========================================================================
TRUNCATE TABLE CADENAS;
INSERT INTO CADENAS (id_cadena, nombre, persona_contacto, telefono_contacto) VALUES
(1, 'Mercadona', 'Juan Roig', '600123456'),
(2, 'Carrefour', 'Marie Dubois', '611234567'),
(3, 'Lidl', 'Hans Müller', '622345678'),
(4, 'Dia', 'Roberto Alcázar', '633987654'),
(5, 'Eroski', 'Ainhoa Zubiri', '644112233'),
(6, 'Aldi', 'Michael Schmidt', '655445566');

-- =========================================================================
-- 4. INSERTAR ENTIDADES COLABORADORAS (Organizaciones y Pequeños Colaboradores)
-- =========================================================================
TRUNCATE TABLE COLABORADOR;
INSERT INTO COLABORADOR (id_colaborador, nombre_entidad, email, contacto_nom, contacto_tlf, domicilio, localidad, zona_geografica, observaciones, cod_postal, estado) VALUES
-- Grandes Organizaciones (Formatos A / B)
(1, 'A002-Cruz Roja Málaga', 'malaga@cruzroja.es', 'María García', '633456789', 'Calle Principal 12', 'Málaga', 'MÁLAGA CENTRO', 'Dispone de 15 voluntarios activos para el fin de semana.', '29012', 1),
(2, 'B001-Asociación Solidaria', 'contacto@solidaria.org', 'Pedro López', '644567890', 'Av. de la Solidaridad 4', 'Torremolinos', 'TORREMOLINOS', 'Pendiente de confirmar horario de la reunión informativa.', '29620', 1),
(3, 'A001-AYUNTAMIENTO DE ALMACHAR', 'contacto@almachar.es', 'María Vallejo', '666 666 666', 'Calle Almeria, 14', 'Almachar', 'RINCON DE LA VICTORIA', '10 voluntarios. Formación Jueves 30, a las 19:45', '29718', 1),
(4, 'A003-Cáritas Diocesana Málaga', 'malaga@caritas.es', 'Francisco Javier', '677111222', 'Calle Rampa de la Aurora 3', 'Málaga', 'MÁLAGA CENTRO', 'Aporta un grupo grande de voluntarios senior.', '29007', 1),
(5, 'B002-Asociación Vecinal El Palo', 'vecinos.elpalo@gmail.com', 'Carmen Carmen', '688222333', 'Plaza del Niño de las Alcaparras 2', 'El Palo (Málaga)', 'EL PALO', 'Disponibilidad completa para coordinar la zona este.', '29017', 1),
(6, 'A004-AYUNTAMIENTO DE TORROX', 'social@torrox.es', 'Manuel Salvatierra', '699333444', 'Plaza de la Constitución 1', 'Torrox', 'AXARQUÍA', 'Convenio activo. Aportan transporte para voluntarios.', '29770', 1),
(7, 'B003-Juventudes Solidarias', 'info@juventudessolidarias.org', 'Lucas Gámez', '600444555', 'Av. de Carlos Haya 88', 'Málaga', 'MÁLAGA OSTE', 'Voluntarios jóvenes disponibles principalmente tardes y fines de semana.', '29010', 1),

-- Pequeños Colaboradores / Familias (Formato CXXXX)
(8, 'C0001-Familia Heredia Ruiz', 'familia.heredia@email.com', 'Carlos Heredia', '622111222', 'Calle Zapateros 4, 2A', 'Málaga', 'MÁLAGA CENTRO', 'Colaboración familiar de fin de semana. Traen su propio coche.', '29005', 1),
(9, 'C0002-Grupo Amigos Teatinos', 'amigos.teatinos@email.com', 'Laura Vega', '633222333', 'Av. de Plutarco 12', 'Málaga Teatinos', 'MÁLAGA OSTE', 'Pequeño grupo de universitarios del barrio.', '29010', 1);

-- =========================================================================
-- 5. INSERTAR CAMPAÑAS
-- =========================================================================
TRUNCATE TABLE CAMPANA;
INSERT INTO CAMPANA (id_campana, id_tipo_campana, id_usuario, fecha, activo) VALUES
(1, 1, 1, 20261128, 1),
(2, 2, 3, 20260615, 0);

-- =========================================================================
-- 6. INSERTAR TIENDAS
-- =========================================================================
TRUNCATE TABLE TIENDA;
INSERT INTO TIENDA (id_tienda, id_cadena, nombre, direccion, localidad, cod_postal) VALUES
(1, 1, 'Mercadona Vialia', 'Explanada de la Estación s/n', 'Málaga Centro', '29002'),
(2, 1, 'Mercadona Larios', 'Calle Larios 5', 'Málaga Centro', '29005'),
(3, 2, 'Carrefour Alameda', 'Av. de Andalucía s/n', 'Málaga Pasaje', '29007'),
(4, 3, 'Lidl El Palo', 'Av. Juan Sebastián Elcano 45', 'El Palo (Málaga)', '29017'),
(5, 1, 'Mercadona Torrox Costa', 'Av. de Andalucía km 185', 'Torrox', '29770'),
(6, 4, 'Dia Maxi Torremolinos', 'Calle Cruz 14', 'Torremolinos', '29620'),
(7, 5, 'Eroski Center Larios', 'Centro Comic. Larios Centro', 'Málaga Centro', '29002'),
(8, 6, 'Aldi Teatinos', 'Av. Dr. Manuel Domínguez 10', 'Málaga Teatinos', '29010');

-- =========================================================================
-- 7. INSERTAR USUARIOS
-- =========================================================================
TRUNCATE TABLE USUARIO;
INSERT INTO USUARIO (id_usuario, id_rol, email, password, nombre_completo, activo) VALUES
(1, 1, 'admin@bancosol.org', 'admin123', 'Carlos Administrador', 1),
(2, 2, 'responsable.entidad@cruzroja.es', 'entidad123', 'Ana Martínez', 1),
(3, 3, 'coordinador1@bancosol.org', 'coord123', 'Luis Coordinador', 1),
(4, 4, 'responsable.vialia@mercadona.es', 'tienda123', 'Sofía Responsable Tienda', 1),
(5, 5, 'capitan@bancosol.org', 'capitan', 'Capitan Sanchez', 1), -- CAPITÁN ZONA CENTRO/OESTE
(6, 2, 'responsable.caritas@caritas.es', 'caritas2026', 'Javier Fernández', 1),
(7, 4, 'gerente.larios@eroski.es', 'eroski123', 'Marta Jiménez', 1),
(8, 5, 'capitan.axarquia@bancosol.org', 'capitanax', 'Rocío Benítez', 1), -- CAPITÁN ZONA ESTE/AXARQUÍA
(9, 3, 'coordinador2@bancosol.org', 'coord456', 'Andrés Viso', 1);

-- =========================================================================
-- 8. INSERTAR VOLUNTARIOS
-- =========================================================================
TRUNCATE TABLE VOLUNTARIO;
INSERT INTO VOLUNTARIO (id_voluntario, id_colaborador, nombre, telefono, email) VALUES
-- Voluntarios de organizaciones
(1, 1, 'Juan Pérez', '655111222', 'juan.perez@email.com'),
(2, 2, 'Elena Gómez', '655333444', 'elena.gomez@email.com'),
(3, 3, 'Marcos Ruiz', '655555666', 'marcos.ruiz@email.com'),
(4, 4, 'Antonio Garrido', '611777888', 'antonio.garrido@email.com'),
(5, 4, 'Francisca Ortiz', '622888999', 'paqui.ortiz@email.com'),
(6, 5, 'Lucía Molina', '633999000', 'lucia.molina@email.com'),
(7, 6, 'Jorge Crespo', '644000111', 'jorge.crespo@email.com'),
(8, 7, 'Sara Villanueva', '655222333', 'sara.villa@email.com'),

-- Voluntarios que pertenecen a los pequeños colaboradores (CXXXX)
(9, 8, 'Carlos Heredia (Padre)', '622111222', 'carlos.padre@email.com'),
(10, 8, 'Sofía Ruiz (Madre)', '622111223', 'sofia.madre@email.com'),
(11, 9, 'Laura Vega', '633222333', 'laura.vega@email.com');

-- =========================================================================
-- 9. RELACIONES AUXILIARES
-- =========================================================================
TRUNCATE TABLE CAMPANA_CADENAS;
INSERT INTO CAMPANA_CADENAS (id_campana, id_cadena) VALUES 
(1, 1), (1, 2), (1, 3), (1, 4), (1, 5), (1, 6),
(2, 1);

TRUNCATE TABLE TIENDA_CAMPANA;
INSERT INTO TIENDA_CAMPANA (id_tienda, id_campana) VALUES 
(1, 1), (1, 2), (3, 1), (4, 1),
(5, 1), (6, 1), (7, 1), (8, 1);

TRUNCATE TABLE USUARIO_TIENDA;
INSERT INTO USUARIO_TIENDA (id_usuario, id_tienda) VALUES 
-- Responsables de tienda específicos (Rol 4)
(4, 1), 
(7, 7),

-- Responsables de Entidad (Rol 2) asignados a Tiendas supervisadas
(2, 1), -- Ana Martínez (Cruz Roja) asignada a Mercadona Vialia (Tienda 1)
(2, 2), -- Ana Martínez (Cruz Roja) asignada a Mercadona Larios (Tienda 2)
(6, 4), -- Javier Fernández (Cáritas) asignado a Lidl El Palo (Tienda 4)

-- ASIGNACIÓN DE CAPITANES (Rol 5) A TODAS LAS TIENDAS:
(5, 1), -- Capitan Sanchez asignado a Tienda 1
(5, 2), -- Capitan Sanchez asignado a Tienda 2
(5, 3), -- Capitan Sanchez asignado a Tienda 3
(5, 6), -- Capitan Sanchez asignado a Tienda 6
(8, 4), -- Capitana Rocío asignada a Tienda 4
(8, 5), -- Capitana Rocío asignada a Tienda 5
(8, 7), -- Capitana Rocío asignada a Tienda 7
(8, 8); -- Capitana Rocío asignada a Tienda 8

TRUNCATE TABLE USUARIO_COLABORADOR;
INSERT INTO USUARIO_COLABORADOR (id_usuario, id_colaborador) VALUES 
(3, 1), (3, 2), (3, 3),
(9, 4), (9, 6), (9, 7), (3, 5),
(3, 8), -- El coordinador 1 supervisa a la Familia Heredia (Colaborador 8)
(9, 9); -- El coordinador 2 supervisa al Grupo de Amigos (Colaborador 9)

-- =========================================================================
-- 10. ASIGNACIÓN DE TURNOS E INCIDENCIAS
-- =========================================================================
TRUNCATE TABLE ASIGNACION_TURNOS;
INSERT INTO ASIGNACION_TURNOS (id_asignacion, id_campana, id_tienda, id_voluntario, id_colaborador, dia, franja, hora_inicio, hora_fin) VALUES
(1, 1, 1, 1, 1, '2026-11-28', 'MAÑANA', '09:00:00', '14:00:00'),
(2, 2, 3, 2, 2, '2026-11-28', 'TARDE', '14:00:00', '19:00:00'),
(3, 1, 5, 7, 6, '2026-11-28', 'MAÑANA', '09:00:00', '14:00:00'), 
(4, 1, 7, 4, 4, '2026-11-28', 'TARDE', '16:00:00', '21:30:00'),  
(5, 1, 8, 8, 7, '2026-11-29', 'MAÑANA', '10:00:00', '15:00:00'),
-- Turnos asignados a los colaboradores pequeños (CXXXX)
(6, 1, 2, 9, 8, '2026-11-28', 'TARDE', '17:00:00', '21:00:00'),  -- Carlos (Familia Heredia) en Mercadona Larios
(7, 1, 8, 11, 9, '2026-11-28', 'TARDE', '16:00:00', '21:00:00'); -- Laura (Amigos Teatinos) en Aldi Teatinos

TRUNCATE TABLE INCIDENCIAS;
INSERT INTO INCIDENCIAS (id_incidencia, id_asignacion, descripcion) VALUES
(1, 1, 'El voluntario notificó un retraso de 15 minutos debido al tráfico, pero cumplió su turno.'),
(2, 4, 'El supermercado proporcionó un espacio excelente cerca de las cajas principales. Alta recaudación de alimentos.'),
(3, 6, 'Asistió el matrimonio completo a cubrir el punto de recogida familiar. Todo excelente.');

-- Volver a activar la verificación de claves foráneas
SET FOREIGN_KEY_CHECKS = 1;