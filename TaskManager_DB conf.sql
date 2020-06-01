INSERT INTO USUARIO (id_usuario, correo, hash, nombre, rut, direccion, rol_id_rol) 
VALUES (1,'admin@taskmanager.com', '1234', 'Admin', '11111111-1', 'central alerce', 1);

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



-- Informe de Resumen de Oracle SQL Developer Data Modeler: 
-- 
-- CREATE TABLE                            10
-- CREATE INDEX                             0
-- ALTER TABLE                             20
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