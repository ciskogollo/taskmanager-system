-- INICIAR CON ADMIN
DROP USER admintm CASCADE;

-- crear el nuevo usuario
CREATE USER admintm
IDENTIFIED BY root420
DEFAULT tablespace users
TEMPORARY TABLESPACE temp;

-- Damos permisos al nuevo usuario
GRANT connect to admintm;
GRANT resource to admintm;
GRANT create session to admintm;
GRANT create table TO admintm;
GRANT create view TO admintm;

-- cambiar el usuario de conexion...
-- Es mejor cambiar directamente en SQL Developer!
-- conn admintm/root420

CREATE TABLE cliente (
    id_cliente      INTEGER NOT NULL,
    nombre_cliente  VARCHAR2(35) NOT NULL,
    habilitado      CHAR(1) NOT NULL
);

ALTER TABLE cliente ADD CONSTRAINT cliente_pk PRIMARY KEY ( id_cliente );

CREATE TABLE funcion (
    id_funcion        INTEGER NOT NULL,
    unidad_id_unidad  INTEGER NOT NULL
);

ALTER TABLE funcion ADD CONSTRAINT funcion_pk PRIMARY KEY ( id_funcion );

CREATE TABLE problema (
    id_problema         INTEGER NOT NULL,
    comentario          VARCHAR2(255) NOT NULL,
    usuario_id_usuario  INTEGER NOT NULL
);

ALTER TABLE problema ADD CONSTRAINT problema_pk PRIMARY KEY ( id_problema );

CREATE TABLE proceso (
    id_proceso           INTEGER NOT NULL,
    tip_flujo            VARCHAR2(25) NOT NULL,
    cliente_id_cliente   INTEGER NOT NULL,
    responsable_id_resp  INTEGER NOT NULL
);

ALTER TABLE proceso ADD CONSTRAINT proceso_pk PRIMARY KEY ( id_proceso );

CREATE TABLE responsable (
    id_resp             INTEGER NOT NULL,
    tarea_id_tarea      INTEGER NOT NULL,
    usuario_id_usuario  INTEGER NOT NULL
);

ALTER TABLE responsable ADD CONSTRAINT responsable_pk PRIMARY KEY ( id_resp );

CREATE TABLE rol (
    id_rol               INTEGER NOT NULL,
    tipo_rol_id_tiporol  INTEGER NOT NULL
);

ALTER TABLE rol ADD CONSTRAINT rol_pk PRIMARY KEY ( id_rol );

CREATE TABLE status_work (
    id_status    INTEGER NOT NULL,
    tipo_status  VARCHAR2(25) NOT NULL
);

ALTER TABLE status_work ADD CONSTRAINT status_work_pk PRIMARY KEY ( id_status );

CREATE TABLE tarea (
    id_tarea               INTEGER NOT NULL,
    descripcion            VARCHAR2(420) NOT NULL,
    fecha_ingreso          DATE NOT NULL,
    fecha_plazo            DATE,
    fecha_recepcion        DATE NOT NULL,
    status_work_id_status  INTEGER NOT NULL,
    funcion_id_funcion     INTEGER NOT NULL,
    usuario_id_usuario     INTEGER NOT NULL,
    tarea_id_tarea         INTEGER,
    tarea_id_tarea1        INTEGER
);

ALTER TABLE tarea ADD CONSTRAINT tarea_pk PRIMARY KEY ( id_tarea );

CREATE TABLE tarea_subordinada (
    id_tsub              INTEGER NOT NULL,
    responsable_id_resp  INTEGER NOT NULL,
    tarea_id_tarea       INTEGER NOT NULL,
    usuario_id_usuario   INTEGER NOT NULL
);

ALTER TABLE tarea_subordinada ADD CONSTRAINT tarea_subordinada_pk PRIMARY KEY ( id_tsub );

CREATE TABLE tipo_funcion (
    id_tipofunc         INTEGER NOT NULL,
    funcion_id_funcion  INTEGER NOT NULL,
    proceso_id_proceso  INTEGER NOT NULL
);

ALTER TABLE tipo_funcion ADD CONSTRAINT tipo_funcion_pk PRIMARY KEY ( id_tipofunc );

CREATE TABLE tipo_rol (
    id_tiporol  INTEGER NOT NULL,
    nombre_rol  VARCHAR2(30)
);

ALTER TABLE tipo_rol ADD CONSTRAINT tipo_rol_pk PRIMARY KEY ( id_tiporol );

CREATE TABLE unidad (
    id_unidad    INTEGER NOT NULL,
    id_proceso   INTEGER NOT NULL,
    tipo_unidad  VARCHAR2(35) NOT NULL
);

ALTER TABLE unidad ADD CONSTRAINT unidad_pk PRIMARY KEY ( id_unidad );

CREATE TABLE usuario (
    id_usuario  INTEGER NOT NULL,
    correo      VARCHAR2(40) NOT NULL,
    hash        VARCHAR2(60) NOT NULL,
    nombre      VARCHAR2(35) NOT NULL,
    rut         VARCHAR2(10) NOT NULL,
    direccion   VARCHAR2(4000),
    rol_id_rol  INTEGER NOT NULL
);

ALTER TABLE usuario ADD CONSTRAINT usuario_pk PRIMARY KEY ( id_usuario );

ALTER TABLE funcion
    ADD CONSTRAINT funcion_unidad_fk FOREIGN KEY ( unidad_id_unidad )
        REFERENCES unidad ( id_unidad );

ALTER TABLE problema
    ADD CONSTRAINT problema_usuario_fk FOREIGN KEY ( usuario_id_usuario )
        REFERENCES usuario ( id_usuario );

ALTER TABLE proceso
    ADD CONSTRAINT proceso_cliente_fk FOREIGN KEY ( cliente_id_cliente )
        REFERENCES cliente ( id_cliente );

ALTER TABLE proceso
    ADD CONSTRAINT proceso_responsable_fk FOREIGN KEY ( responsable_id_resp )
        REFERENCES responsable ( id_resp );

ALTER TABLE responsable
    ADD CONSTRAINT responsable_tarea_fk FOREIGN KEY ( tarea_id_tarea )
        REFERENCES tarea ( id_tarea );

ALTER TABLE responsable
    ADD CONSTRAINT responsable_usuario_fk FOREIGN KEY ( usuario_id_usuario )
        REFERENCES usuario ( id_usuario );

ALTER TABLE rol
    ADD CONSTRAINT rol_tipo_rol_fk FOREIGN KEY ( tipo_rol_id_tiporol )
        REFERENCES tipo_rol ( id_tiporol );

ALTER TABLE tarea
    ADD CONSTRAINT tarea_funcion_fk FOREIGN KEY ( funcion_id_funcion )
        REFERENCES funcion ( id_funcion );

ALTER TABLE tarea
    ADD CONSTRAINT tarea_status_work_fk FOREIGN KEY ( status_work_id_status )
        REFERENCES status_work ( id_status );

ALTER TABLE tarea_subordinada
    ADD CONSTRAINT tarea_subordinada_resp_fk FOREIGN KEY ( responsable_id_resp )
        REFERENCES responsable ( id_resp );

ALTER TABLE tarea_subordinada
    ADD CONSTRAINT tarea_subordinada_tarea_fk FOREIGN KEY ( tarea_id_tarea )
        REFERENCES tarea ( id_tarea );

ALTER TABLE tarea_subordinada
    ADD CONSTRAINT tarea_subordinada_usuario_fk FOREIGN KEY ( usuario_id_usuario )
        REFERENCES usuario ( id_usuario );

ALTER TABLE tarea
    ADD CONSTRAINT tarea_tarea_fk FOREIGN KEY ( tarea_id_tarea )
        REFERENCES tarea ( id_tarea );

ALTER TABLE tarea
    ADD CONSTRAINT tarea_tarea_fkv1 FOREIGN KEY ( tarea_id_tarea1 )
        REFERENCES tarea ( id_tarea );

ALTER TABLE tarea
    ADD CONSTRAINT tarea_usuario_fk FOREIGN KEY ( usuario_id_usuario )
        REFERENCES usuario ( id_usuario );

ALTER TABLE tipo_funcion
    ADD CONSTRAINT tipo_funcion_funcion_fk FOREIGN KEY ( funcion_id_funcion )
        REFERENCES funcion ( id_funcion );

ALTER TABLE tipo_funcion
    ADD CONSTRAINT tipo_funcion_proceso_fk FOREIGN KEY ( proceso_id_proceso )
        REFERENCES proceso ( id_proceso );

ALTER TABLE usuario
    ADD CONSTRAINT usuario_rol_fk FOREIGN KEY ( rol_id_rol )
        REFERENCES rol ( id_rol );



-- Informe de Resumen de Oracle SQL Developer Data Modeler: 
-- 
-- CREATE TABLE                            13
-- CREATE INDEX                             0
-- ALTER TABLE                             31
-- CREATE VIEW                              0
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           0
-- ALTER TRIGGER                            0
-- CREATE COLLECTION TYPE                   0
-- CREATE STRUCTURED TYPE                   0
-- CREATE STRUCTURED TYPE BODY              0
-- CREATE CLUSTER                           0
-- CREATE CONTEXT                           0
-- CREATE DATABASE                          0
-- CREATE DIMENSION                         0
-- CREATE DIRECTORY                         0
-- CREATE DISK GROUP                        0
-- CREATE ROLE                              0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE SEQUENCE                          0
-- CREATE MATERIALIZED VIEW                 0
-- CREATE MATERIALIZED VIEW LOG             0
-- CREATE SYNONYM                           0
-- CREATE TABLESPACE                        0
-- CREATE USER                              0
-- 
-- DROP TABLESPACE                          0
-- DROP DATABASE                            0
-- 
-- REDACTION POLICY                         0
-- 
-- ORDS DROP SCHEMA                         0
-- ORDS ENABLE SCHEMA                       0
-- ORDS ENABLE OBJECT                       0
-- 
-- ERRORS                                   0
-- WARNINGS                                 0
