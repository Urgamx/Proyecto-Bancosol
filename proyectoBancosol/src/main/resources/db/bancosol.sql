CREATE DATABASE IF NOT EXISTS BANCOSOL_DB;

USE BANCOSOL_DB;

CREATE TABLE CAMPANA (
  id_campana        int(10) NOT NULL AUTO_INCREMENT,
  id_tipo_campana int(10) NOT NULL,
  fecha             int(10) NOT NULL,
  activo            int(1) NOT NULL,
  CONSTRAINT id_campana
    PRIMARY KEY (id_campana));

CREATE TABLE CADENAS (
  id_cadena         int(10) NOT NULL AUTO_INCREMENT,
  nombre            varchar(150) NOT NULL,
  persona_contacto  varchar(150),
  telefono_contacto varchar(20),
  CONSTRAINT id_cadena
    PRIMARY KEY (id_cadena));

CREATE TABLE TIENDA (
  id_tienda  int(10) NOT NULL AUTO_INCREMENT,
  id_cadena  int(10) NOT NULL,
  nombre     varchar(150) NOT NULL,
  direccion  varchar(255) NOT NULL,
  localidad  varchar(255) NOT NULL,
  cod_postal varchar(5) NOT NULL,
  CONSTRAINT ID_TIENDA
    PRIMARY KEY (id_tienda));

CREATE TABLE USUARIO (
  id_usuario      int(10) NOT NULL AUTO_INCREMENT,
  id_rol          int(10) NOT NULL,
  email           varchar(150) NOT NULL,
  password        varchar(255) NOT NULL,
  nombre_completo varchar(150) NOT NULL,
  activo          int(1) NOT NULL,
  CONSTRAINT id_usuario
    PRIMARY KEY (id_usuario));

CREATE TABLE COLABORADOR (
  id_colaborador int(10) NOT NULL AUTO_INCREMENT,
  nombre_entidad varchar(150) NOT NULL,
  email          varchar(100) NOT NULL,
  contacto_nom   varchar(255),
  contacto_tlf   varchar(20),
  domicilio      varchar(255),
  localidad      varchar(255),
  zona_geografica  varchar(255),
  observaciones  varchar(255),
  cod_postal     varchar(255),
  estado         int(1) NOT NULL,
  CONSTRAINT id_colaborador
    PRIMARY KEY (id_colaborador));

CREATE TABLE VOLUNTARIO (
  id_voluntario  int(10) NOT NULL AUTO_INCREMENT,
  id_colaborador int(10) NOT NULL,
  nombre         varchar(100) NOT NULL,
  telefono       varchar(255) NOT NULL,
  email          varchar(150) NOT NULL,
  CONSTRAINT id_voluntario
    PRIMARY KEY (id_voluntario));

CREATE TABLE ASIGNACION_TURNOS (
  id_asignacion int(10) NOT NULL AUTO_INCREMENT,
  id_tienda     int(10) NOT NULL,
  id_voluntario int(10) NOT NULL,
  id_colaborador int(10) NOT NULL,
  dia           varchar(20) NOT NULL,
  franja        varchar(10) NOT NULL,
  hora_inicio   time NOT NULL,
  hora_fin      time NOT NULL,
  CONSTRAINT id_asignacion
    PRIMARY KEY (id_asignacion));

CREATE TABLE ROL (
  id_rol      int(10) NOT NULL AUTO_INCREMENT,
  nombre      varchar(50) NOT NULL,
  descripcion varchar(255) NOT NULL,
  CONSTRAINT id_rol
    PRIMARY KEY (id_rol));

CREATE TABLE CAMPANA_CADENAS (
  id_campana int(10) NOT NULL,
  id_cadena  int(10) NOT NULL,
  PRIMARY KEY (id_campana,
  id_cadena));

CREATE TABLE TIENDA_CAMPANA (
  id_tienda int(10) NOT NULL,
  id_campana      int(10) NOT NULL,
  PRIMARY KEY (id_tienda,
  id_campana));

CREATE TABLE USUARIO_TIENDA (
  id_usuario int(10) NOT NULL,
  id_tienda   int(10) NOT NULL,
  PRIMARY KEY (id_usuario,
  id_tienda));

CREATE TABLE INCIDENCIAS (
  id_incidencia int(10) NOT NULL AUTO_INCREMENT,
  id_asignacion int(10) NOT NULL,
  descripcion   varchar(2000) NOT NULL,
  CONSTRAINT id_incidencia
    PRIMARY KEY (id_incidencia));

CREATE TABLE USUARIO_COLABORADOR (
  id_usuario int(10) NOT NULL,
  id_colaborador    int(10) NOT NULL,
  PRIMARY KEY (id_usuario,
  id_colaborador));

CREATE TABLE TIPO_DE_CAMPANA (
  id_tipo_campana int(10) NOT NULL AUTO_INCREMENT,
  nombre     varchar(20) NOT NULL,
  CONSTRAINT id_nombre_campana
    PRIMARY KEY (id_tipo_campana));

ALTER TABLE VOLUNTARIO ADD CONSTRAINT FKVOLUNTARIO582598 FOREIGN KEY (id_colaborador) REFERENCES COLABORADOR (id_colaborador);
ALTER TABLE CAMPANA_CADENAS ADD CONSTRAINT FKCAMPANA_CA600554 FOREIGN KEY (id_campana) REFERENCES CAMPANA (id_campana);
ALTER TABLE CAMPANA_CADENAS ADD CONSTRAINT FKCAMPANA_CA608317 FOREIGN KEY (id_cadena) REFERENCES CADENAS (id_cadena);
ALTER TABLE TIENDA ADD CONSTRAINT FKTIENDA766372 FOREIGN KEY (id_cadena) REFERENCES CADENAS (id_cadena);
ALTER TABLE USUARIO ADD CONSTRAINT FKUSUARIO4727 FOREIGN KEY (id_rol) REFERENCES ROL (id_rol);
ALTER TABLE ASIGNACION_TURNOS ADD CONSTRAINT FKASIGNACION480024 FOREIGN KEY (id_tienda) REFERENCES TIENDA (id_tienda);
ALTER TABLE ASIGNACION_TURNOS ADD CONSTRAINT FKASIGNACION217085 FOREIGN KEY (id_voluntario) REFERENCES VOLUNTARIO (id_voluntario);
ALTER TABLE TIENDA_CAMPANA ADD CONSTRAINT FKTIENDA_CAM829775 FOREIGN KEY (id_tienda) REFERENCES TIENDA (id_tienda);
ALTER TABLE TIENDA_CAMPANA ADD CONSTRAINT FKTIENDA_CAM148906 FOREIGN KEY (id_campana) REFERENCES CAMPANA (id_campana);
ALTER TABLE USUARIO_TIENDA ADD CONSTRAINT FKUSUARIO_TI839307 FOREIGN KEY (id_usuario) REFERENCES USUARIO (id_usuario);
ALTER TABLE USUARIO_TIENDA ADD CONSTRAINT FKUSUARIO_TI779857 FOREIGN KEY (id_tienda) REFERENCES TIENDA (id_tienda);
ALTER TABLE USUARIO_COLABORADOR ADD CONSTRAINT FKUSUARIO_CO598597 FOREIGN KEY (id_usuario) REFERENCES USUARIO (id_usuario);
ALTER TABLE USUARIO_COLABORADOR ADD CONSTRAINT FKUSUARIO_CO215041 FOREIGN KEY (id_colaborador) REFERENCES COLABORADOR (id_colaborador);
ALTER TABLE CAMPANA ADD CONSTRAINT FKCAMPANA967187 FOREIGN KEY (id_tipo_campana) REFERENCES TIPO_DE_CAMPANA (id_tipo_campana);
ALTER TABLE INCIDENCIAS ADD CONSTRAINT FKINCIDENCIA417387 FOREIGN KEY (id_asignacion) REFERENCES ASIGNACION_TURNOS (id_asignacion);
ALTER TABLE ASIGNACION_TURNOS ADD CONSTRAINT FKASIGNACION_COLAB FOREIGN KEY (id_colaborador) REFERENCES COLABORADOR (id_colaborador);