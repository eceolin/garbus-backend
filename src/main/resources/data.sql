
BEGIN;

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
VALUES ('Cheia', 'Lixeira esta com capacidade acima de 80%');

INSERT INTO TRASH_STATUS (NAME, DESCRIPTION)
VALUES ('Vazia', 'Lixeira esta com capacidade abaixo de 20%');

INSERT INTO TRASH_STATUS (NAME, DESCRIPTION)
VALUES ('Manutenção', 'A lixiera esta em manutenção');

INSERT INTO TRASH_STATUS (NAME, DESCRIPTION)
VALUES ('Ativa', 'A lixeira está em funcionamento');

INSERT INTO TRASH_STATUS (NAME, DESCRIPTION)
VALUES ('Inativa', 'A lixeira está forá de funcionamento');

INSERT INTO TRASH_STATUS (NAME, DESCRIPTION)
VALUES ('Excluida', 'A lixeira não existe');

INSERT INTO TYPE_TRASH
VALUES (1, 'Orgânica', 'Lixo Organico');

INSERT INTO TYPE_TRASH
VALUES (2, 'Reciclavel', 'Lixo Seco');

INSERT INTO PROFILES
VALUES (1, 'Operador', 'Operador de lixo');

INSERT INTO PROFILES
VALUES (2, 'Gestor', 'Gestor do sistema');

INSERT INTO EVENTS (DESCRIPTION, CREATION_DATE, PROBLEM_STATUS, ID_TYPE_EVENTS)
VALUES ('REPORT', CURRENT_TIMESTAMP, 'A', 1);

INSERT INTO EVENTS (DESCRIPTION, CREATION_DATE, PROBLEM_STATUS, ID_TYPE_EVENTS)
VALUES ('Problema na lixeira', CURRENT_TIMESTAMP, 'I', 2);

INSERT INTO BUILDINGS (NAME, ID_ZONE)
VALUES ('Prédio 30', 1);

INSERT INTO BUILDINGS (NAME, ID_ZONE)
VALUES ('Prédio 32', 1);

INSERT INTO BUILDINGS (NAME, ID_ZONE)
VALUES ('Prédio 17', 2);

INSERT INTO BUILDINGS (NAME, ID_ZONE)
VALUES ('Prédio 8', 3);

INSERT INTO BUILDINGS (NAME, ID_ZONE)
VALUES ('Prédio 7', 3);

INSERT INTO BUILDINGS (NAME, ID_ZONE)
VALUES ('Prédio 6', 3);

INSERT INTO USERS (EMAIL, NAME, LOGIN, PASSWORD, DT_REGISTER, ID_PROFILE)
VALUES ('mario.araujo@edu.pucrs.br', 'Mario Specht', 'mario.araujo', '102030', CURRENT_TIMESTAMP, 1);

INSERT INTO USERS (EMAIL, NAME, LOGIN, PASSWORD, DT_REGISTER, ID_PROFILE)
VALUES ('teste@pucrs.br', 'Gestor Teste', 'gestor.teste', '102030', CURRENT_TIMESTAMP, 2);

INSERT INTO USER_ZONE (ID_USER, ID_ZONE)
VALUES (1, 1);

INSERT INTO USER_ZONE (ID_USER, ID_ZONE)
VALUES (2, 1);

INSERT INTO TRASHES (BRAND, DESCRIPTION, CAPACITY, OCCUPATION, ID_STATUS, ID_TYPE, ID_BUILDING, ID_ZONE, LONGITUDE, LATITUDE)
VALUES ('Sanremo', 'Lixeira industrial sanremo', 50.0, 0.0, 4, 1, 1, NULL, -51.17444551817851, -30.059002933843015);

INSERT INTO TRASHES (BRAND, DESCRIPTION, CAPACITY, OCCUPATION, ID_STATUS, ID_TYPE, ID_BUILDING, ID_ZONE, LONGITUDE, LATITUDE)
VALUES ('Sanremo', 'Lixeira industrial sanremo', 80.0, 0.0, 2, 2, NULL, 2, -51.17338334743796, -30.058483917289433);

INSERT INTO TRASHES (BRAND, DESCRIPTION, CAPACITY, OCCUPATION, ID_STATUS, ID_TYPE, ID_BUILDING, ID_ZONE, LONGITUDE, LATITUDE)
VALUES ('Sanremo', 'Lixeira industrial sanremo', 40.0, 0.0, 2, 2, NULL, 2, -51.17533544501518, -30.058784836792906);

INSERT INTO TRASHES (BRAND, DESCRIPTION, CAPACITY, OCCUPATION, ID_STATUS, ID_TYPE, ID_BUILDING, ID_ZONE, LONGITUDE, LATITUDE)
VALUES ('Sanremo', 'Lixeira industrial sanremo', 50.0, 0.0, 4, 1, 2, NULL, -51.17573415775563, -30.059571640346782);

INSERT INTO TRASHES (BRAND, DESCRIPTION, CAPACITY, OCCUPATION, ID_STATUS, ID_TYPE, ID_BUILDING, ID_ZONE, LONGITUDE, LATITUDE)
VALUES ('Sanremo', 'Lixeira industrial sanremo', 999.9, 0.0, 4, 1, NULL, NULL, -51.17483147211126, -30.058340359038915);

INSERT INTO TRASHES_EVENTS (ID_EVENT, ID_TRASH, ID_USER, OCCUPATION, DATE)
VALUES (1, 1, 1, 21.2, CURRENT_TIMESTAMP);

INSERT INTO TRASHES_EVENTS (ID_EVENT, ID_TRASH, ID_USER, OCCUPATION, DATE)
VALUES (1, 1, 1, 23.3, CURRENT_TIMESTAMP);

INSERT INTO TRASHES_EVENTS (ID_EVENT, ID_TRASH, ID_USER, OCCUPATION, DATE)
VALUES (1, 1, 1, 53.2, CURRENT_TIMESTAMP);

INSERT INTO TRASHES_EVENTS (ID_EVENT, ID_TRASH, ID_USER, OCCUPATION, DATE)
VALUES (1, 1, 1, 13.8, CURRENT_TIMESTAMP);

COMMIT;