
/* INSERTS */
INSERT INTO TYPES_EVENTS (NAME, DESCRIPTION)
VALUES ('Report Lixeira', 'Report de status da lixeira');

INSERT INTO TYPES_EVENTS (NAME, DESCRIPTION)
VALUES ('Manutenção', 'Problemas no sensor');

INSERT INTO TYPES_EVENTS (NAME, DESCRIPTION)
VALUES ('Fogo', 'Colocaram fogo na lixeira');

INSERT INTO TYPES_EVENTS (NAME, DESCRIPTION)
VALUES ('Outros', 'Lixeira com infiltração');

INSERT INTO ZONES
VALUES (1, 'Norte', 'Prédios lado Bento Gonçalves', 0.0, 0.0);

INSERT INTO ZONES
VALUES (2, 'Leste', 'Prédios lado Colégio', 0.0, 0.0);

INSERT INTO ZONES
VALUES (3, 'Sul', 'Prédios lado Ipiranga', 0.0, 0.0);

INSERT INTO TRASH_STATUS (NAME, DESCRIPTION)
VALUES ('Cheia', 'Lixeira ultrapasou capacidade máxima definida');

INSERT INTO TRASH_STATUS (NAME, DESCRIPTION)
VALUES ('Vazia', 'Lixeira está com capacidade abaixo do definido');

INSERT INTO TRASH_STATUS (NAME, DESCRIPTION)
VALUES ('Manutenção', 'A lixeira está em manutenção');

INSERT INTO TRASH_STATUS (NAME, DESCRIPTION)
VALUES ('Ativa', 'A lixeira está em funcionamento');

INSERT INTO TRASH_STATUS (NAME, DESCRIPTION)
VALUES ('Inativa', 'A lixeira está fora de funcionamento');

INSERT INTO TRASH_STATUS (NAME, DESCRIPTION)
VALUES ('Excluída', 'A lixeira não existe');

INSERT INTO TYPE_TRASH
VALUES (1, 'Orgânica', 'Lixo Orgânico');

INSERT INTO TYPE_TRASH
VALUES (2, 'Reciclável', 'Lixo Seco');

INSERT INTO PROFILES
VALUES (1, 'Operador', 'Operador de lixo');

INSERT INTO PROFILES
VALUES (2, 'Gestor', 'Gestor do sistema');

INSERT INTO EVENTS (DESCRIPTION, CREATION_DATE, PROBLEM_STATUS, ID_TYPE_EVENTS)
VALUES ('REPORT', CURRENT_TIMESTAMP, 'A', 1);

INSERT INTO EVENTS (DESCRIPTION, CREATION_DATE, PROBLEM_STATUS, ID_TYPE_EVENTS)
VALUES ('Problema na lixeira', CURRENT_TIMESTAMP, 'E', 2);

INSERT INTO EVENTS (DESCRIPTION, CREATION_DATE, PROBLEM_STATUS, ID_TYPE_EVENTS)
VALUES ('Problema na lixeira', CURRENT_TIMESTAMP, 'E', 3);

INSERT INTO EVENTS (DESCRIPTION, CREATION_DATE, PROBLEM_STATUS, ID_TYPE_EVENTS)
VALUES ('Problema na lixeira', CURRENT_TIMESTAMP, 'E', 4);

INSERT INTO BUILDINGS (NAME, ID_ZONE)
VALUES ('Prédio 30', 1);

INSERT INTO BUILDINGS (NAME, ID_ZONE)
VALUES ('Prédio 32', 1);

INSERT INTO BUILDINGS (NAME, ID_ZONE)
VALUES ('Prédio 15', 2);

INSERT INTO BUILDINGS (NAME, ID_ZONE)
VALUES ('Prédio 17', 2);

INSERT INTO BUILDINGS (NAME, ID_ZONE)
VALUES ('Prédio 8', 3);

INSERT INTO BUILDINGS (NAME, ID_ZONE)
VALUES ('Prédio 7', 3);

INSERT INTO BUILDINGS (NAME, ID_ZONE)
VALUES ('Prédio 6', 3);


INSERT INTO USERS (EMAIL, NAME, LOGIN, PASSWORD, DT_REGISTER, ID_PROFILE)
VALUES ('garbus@garbus.com.br', 'Usuario Default Operador', 'garbus', 'garbus', CURRENT_TIMESTAMP, 1);

INSERT INTO USERS (EMAIL, NAME, LOGIN, PASSWORD, DT_REGISTER, ID_PROFILE)
VALUES ('admin@admin.com.br', 'Usuario Gestor Admin', 'admin', 'admin', CURRENT_TIMESTAMP, 2);

INSERT INTO USER_ZONE (ID_USER, ID_ZONE)
VALUES (1, 1);

INSERT INTO TRASHES (BRAND, DESCRIPTION, CAPACITY, OCCUPATION, ID_STATUS, ID_TYPE, ID_BUILDING, ID_ZONE, LONGITUDE, LATITUDE)
VALUES ('Sanremo', 'Lixeira Industrial Sanremo', 50.0, 30.0, 4, 1, NULL, 1, -51.173491, -30.061278);

-- PREDIO 32
INSERT INTO USER_ZONE (ID_USER, ID_ZONE)
VALUES (2, 1);

INSERT INTO TRASHES (BRAND, DESCRIPTION, CAPACITY, OCCUPATION, ID_STATUS, ID_TYPE, ID_BUILDING, ID_ZONE, LONGITUDE, LATITUDE)
VALUES ('3M', 'Lixeira Papeleira Amarela', 50.0, 80.0, 2, 2, NULL, 1, -51.173494, -30.061085);

INSERT INTO TRASHES (BRAND, DESCRIPTION, CAPACITY, OCCUPATION, ID_STATUS, ID_TYPE, ID_BUILDING, ID_ZONE, LONGITUDE, LATITUDE)
VALUES ('Betanin', 'Lixeira Papeleira Vermelha', 50.0, 10.0, 2, 2, NULL, 1,-51.173929, -30.060899);

INSERT INTO TRASHES (BRAND, DESCRIPTION, CAPACITY, OCCUPATION, ID_STATUS, ID_TYPE, ID_BUILDING, ID_ZONE, LONGITUDE, LATITUDE)
VALUES ('Plastisul', 'Lixeira Papeleira Verde', 40.0, 38.6, 4, 1, NULL, 1, -51.173183, -30.060866);

-- PREDIO 30
INSERT INTO TRASHES (BRAND, DESCRIPTION, CAPACITY, OCCUPATION, ID_STATUS, ID_TYPE, ID_BUILDING, ID_ZONE, LONGITUDE, LATITUDE)
VALUES ('Sanremo', 'Lixeira Industrial Sanremo', 50.0, 30.0, 4, 1, NULL, 1, -51.173985, -30.060236);

INSERT INTO TRASHES (BRAND, DESCRIPTION, CAPACITY, OCCUPATION, ID_STATUS, ID_TYPE, ID_BUILDING, ID_ZONE, LONGITUDE, LATITUDE)
VALUES ('3M', 'Lixeira Papeleira Amarela', 50.0, 80.0, 2, 2, NULL, 1, -51.174709, -30.059707);

INSERT INTO TRASHES (BRAND, DESCRIPTION, CAPACITY, OCCUPATION, ID_STATUS, ID_TYPE, ID_BUILDING, ID_ZONE, LONGITUDE, LATITUDE)
VALUES ('Betanin', 'Lixeira Papeleira Vermelha', 50.0, 10.0, 2, 2, NULL, 1, -51.174253, -30.059542);

INSERT INTO TRASHES (BRAND, DESCRIPTION, CAPACITY, OCCUPATION, ID_STATUS, ID_TYPE, ID_BUILDING, ID_ZONE, LONGITUDE, LATITUDE)
VALUES ('Plastisul', 'Lixeira Papeleira Verde', 40.0, 38.6, 4, 1, NULL, 1, -51.173867, -30.059537);

-- PREDIO 15
INSERT INTO TRASHES (BRAND, DESCRIPTION, CAPACITY, OCCUPATION, ID_STATUS, ID_TYPE, ID_BUILDING, ID_ZONE, LONGITUDE, LATITUDE)
VALUES ('Sanremo', 'Lixeira Industrial Sanremo', 50.0, 30.0, 4, 1, NULL, 1, -51.174347, -30.059354);

INSERT INTO TRASHES (BRAND, DESCRIPTION, CAPACITY, OCCUPATION, ID_STATUS, ID_TYPE, ID_BUILDING, ID_ZONE, LONGITUDE, LATITUDE)
VALUES ('3M', 'Lixeira Papeleira Amarela', 50.0, 80.0, 2, 2, 2, NULL, -51.174768, -30.059085);

INSERT INTO TRASHES (BRAND, DESCRIPTION, CAPACITY, OCCUPATION, ID_STATUS, ID_TYPE, ID_BUILDING, ID_ZONE, LONGITUDE, LATITUDE)
VALUES ('Betanin', 'Lixeira Papeleira Vermelha', 50.0, 10.0, 2, 2, 2, NULL, -51.174277, -30.058892);

INSERT INTO TRASHES (BRAND, DESCRIPTION, CAPACITY, OCCUPATION, ID_STATUS, ID_TYPE, ID_BUILDING, ID_ZONE, LONGITUDE, LATITUDE)
VALUES ('Plastisul', 'Lixeira Papeleira Verde', 40.0, 38.6, 4, 1, 1, null, -51.173891, -30.058588);



--CASA DO CLEY - TERCEIRO ANDAR
INSERT INTO TRASHES (BRAND, DESCRIPTION, CAPACITY, OCCUPATION, ID_STATUS, ID_TYPE, ID_BUILDING, ID_ZONE, LONGITUDE, LATITUDE)
VALUES ('Cley SA', 'Cleytinho', 999.9, 30.0, 4, 1, 1, NULL, -51.175211877770096, -30.062374183731382);

INSERT INTO TRASHES_EVENTS (ID_EVENT, ID_TRASH, ID_USER, OCCUPATION, DATE)
VALUES (1, 1, 1, 21.2, CURRENT_TIMESTAMP);

INSERT INTO TRASHES_EVENTS (ID_EVENT, ID_TRASH, ID_USER, OCCUPATION, DATE)
VALUES (1, 1, 1, 23.3, CURRENT_TIMESTAMP);

INSERT INTO TRASHES_EVENTS (ID_EVENT, ID_TRASH, ID_USER, OCCUPATION, DATE)
VALUES (1, 1, 1, 53.2, CURRENT_TIMESTAMP);

INSERT INTO TRASHES_EVENTS (ID_EVENT, ID_TRASH, ID_USER, OCCUPATION, DATE)
VALUES (1, 1, 1, 82.2, CURRENT_TIMESTAMP);

INSERT INTO TRASHES_THRESHOLD (MAX_OCCUPATION, COLOR, ID_TRASH)
VALUES (9999999.99, 'RED', 2);

INSERT INTO TRASHES_THRESHOLD (MAX_OCCUPATION, COLOR, ID_TRASH)
VALUES (43.2, 'YELLOW', 2);

INSERT INTO TRASHES_THRESHOLD (MAX_OCCUPATION, COLOR, ID_TRASH)
VALUES (16.8, 'GREEN', 2);

INSERT INTO TRASHES_THRESHOLD (MAX_OCCUPATION, COLOR, ID_TRASH)
VALUES (9999999.99, 'RED', 1);

INSERT INTO TRASHES_THRESHOLD (MAX_OCCUPATION, COLOR, ID_TRASH)
VALUES (70.0, 'YELLOW', 1);

INSERT INTO TRASHES_THRESHOLD (MAX_OCCUPATION, COLOR, ID_TRASH)
VALUES (20.0, 'GREEN', 1);

INSERT INTO TRASHES_THRESHOLD (MAX_OCCUPATION, COLOR, ID_TRASH)
VALUES (9999999.99, 'RED', 5);

INSERT INTO TRASHES_THRESHOLD (MAX_OCCUPATION, COLOR, ID_TRASH)
VALUES (70.0, 'YELLOW', 5);

INSERT INTO TRASHES_THRESHOLD (MAX_OCCUPATION, COLOR, ID_TRASH)
VALUES (20.0, 'GREEN', 5);

INSERT INTO TRASHES_THRESHOLD (MAX_OCCUPATION, COLOR, ID_TRASH)
VALUES (9999999.99, 'RED', 4);

INSERT INTO TRASHES_THRESHOLD (MAX_OCCUPATION, COLOR, ID_TRASH)
VALUES (70.0, 'YELLOW', 4);

INSERT INTO TRASHES_THRESHOLD (MAX_OCCUPATION, COLOR, ID_TRASH)
VALUES (20.0, 'GREEN', 4);

INSERT INTO TRASHES_THRESHOLD (MAX_OCCUPATION, COLOR, ID_TRASH)
VALUES (9999999.99, 'RED', 3);

INSERT INTO TRASHES_THRESHOLD (MAX_OCCUPATION, COLOR, ID_TRASH)
VALUES (70.0, 'YELLOW', 3);

INSERT INTO TRASHES_THRESHOLD (MAX_OCCUPATION, COLOR, ID_TRASH)
VALUES (20.0, 'GREEN', 3);

INSERT INTO TRASHES_THRESHOLD (MAX_OCCUPATION, COLOR, ID_TRASH)
VALUES (9999999.99, 'RED', 6);

INSERT INTO TRASHES_THRESHOLD (MAX_OCCUPATION, COLOR, ID_TRASH)
VALUES (70.0, 'YELLOW', 6);

INSERT INTO TRASHES_THRESHOLD (MAX_OCCUPATION, COLOR, ID_TRASH)
VALUES (20.0, 'GREEN', 6);

INSERT INTO TRASHES_THRESHOLD (MAX_OCCUPATION, COLOR, ID_TRASH)
VALUES (9999999.99, 'RED', 7);

INSERT INTO TRASHES_THRESHOLD (MAX_OCCUPATION, COLOR, ID_TRASH)
VALUES (70.0, 'YELLOW', 7);

INSERT INTO TRASHES_THRESHOLD (MAX_OCCUPATION, COLOR, ID_TRASH)
VALUES (20.0, 'GREEN', 7);

INSERT INTO TRASHES_THRESHOLD (MAX_OCCUPATION, COLOR, ID_TRASH)
VALUES (9999999.99, 'RED', 8);

INSERT INTO TRASHES_THRESHOLD (MAX_OCCUPATION, COLOR, ID_TRASH)
VALUES (70.0, 'YELLOW', 8);

INSERT INTO TRASHES_THRESHOLD (MAX_OCCUPATION, COLOR, ID_TRASH)
VALUES (20.0, 'GREEN', 8);

INSERT INTO TRASHES_THRESHOLD (MAX_OCCUPATION, COLOR, ID_TRASH)
VALUES (9999999.99, 'RED', 9);

INSERT INTO TRASHES_THRESHOLD (MAX_OCCUPATION, COLOR, ID_TRASH)
VALUES (70.0, 'YELLOW', 9);

INSERT INTO TRASHES_THRESHOLD (MAX_OCCUPATION, COLOR, ID_TRASH)
VALUES (20.0, 'GREEN', 9);

INSERT INTO TRASHES_THRESHOLD (MAX_OCCUPATION, COLOR, ID_TRASH)
VALUES (9999999.99, 'RED', 10);

INSERT INTO TRASHES_THRESHOLD (MAX_OCCUPATION, COLOR, ID_TRASH)
VALUES (70.0, 'YELLOW', 10);

INSERT INTO TRASHES_THRESHOLD (MAX_OCCUPATION, COLOR, ID_TRASH)
VALUES (20.0, 'GREEN', 10);

INSERT INTO TRASHES_THRESHOLD (MAX_OCCUPATION, COLOR, ID_TRASH)
VALUES (9999999.99, 'RED', 11);

INSERT INTO TRASHES_THRESHOLD (MAX_OCCUPATION, COLOR, ID_TRASH)
VALUES (70.0, 'YELLOW', 11);

INSERT INTO TRASHES_THRESHOLD (MAX_OCCUPATION, COLOR, ID_TRASH)
VALUES (20.0, 'GREEN', 11);

INSERT INTO TRASHES_THRESHOLD (MAX_OCCUPATION, COLOR, ID_TRASH)
VALUES (9999999.99, 'RED', 12);

INSERT INTO TRASHES_THRESHOLD (MAX_OCCUPATION, COLOR, ID_TRASH)
VALUES (70.0, 'YELLOW', 12);

INSERT INTO TRASHES_THRESHOLD (MAX_OCCUPATION, COLOR, ID_TRASH)
VALUES (20.0, 'GREEN', 12);

INSERT INTO TRASHES_THRESHOLD (MAX_OCCUPATION, COLOR, ID_TRASH)
VALUES (9999999.99, 'RED', 13);

INSERT INTO TRASHES_THRESHOLD (MAX_OCCUPATION, COLOR, ID_TRASH)
VALUES (70.0, 'YELLOW', 13);

INSERT INTO TRASHES_THRESHOLD (MAX_OCCUPATION, COLOR, ID_TRASH)
VALUES (20.0, 'GREEN', 13);