SELECT * FROM dual;


DROP TABLE IF EXISTS PREDIOS;
DROP TABLE IF EXISTS LIXEIRAS;
DROP TABLE IF EXISTS LIXEIRA_EVENTOS;
DROP TABLE IF EXISTS EVENTOS;
DROP TABLE IF EXISTS ZONAS;
DROP TABLE IF EXISTS LIXEIRA_STATUS;
DROP TABLE IF EXISTS TIPO_LIXEIRA;
DROP TABLE IF EXISTS USUARIOS;
DROP TABLE IF EXISTS PERFIS;
DROP TABLE IF EXISTS USUARIO_ZONA;

DROP SEQUENCE IF EXISTS SQ_TIPOS_EVENTOS;
DROP SEQUENCE IF EXISTS SQ_ZONAS;
DROP SEQUENCE IF EXISTS SQ_LIXEIRA_STATUS;
DROP SEQUENCE IF EXISTS SQ_TIPO_LIXEIRA;
DROP SEQUENCE IF EXISTS SQ_PERFIS;
DROP SEQUENCE IF EXISTS SQ_EVENTOS;
DROP SEQUENCE IF EXISTS SQ_PREDIOS;
DROP SEQUENCE IF EXISTS SQ_USUARIOS;
DROP SEQUENCE IF EXISTS SQ_USUARIO_ZONA;
DROP SEQUENCE IF EXISTS SQ_LIXEIRAS;
DROP SEQUENCE IF EXISTS SQ_LIXEIRA_EVENTOS;

/* CREATE TABLES */

CREATE TABLE TIPOS_EVENTOS (
                               ID NUMBER PRIMARY KEY,
                               NOME VARCHAR NOT NULL,
                               DESCRICAO VARCHAR
);


CREATE TABLE ZONAS (
                       ID NUMBER PRIMARY KEY,
                       NOME VARCHAR NOT NULL NOT NULL,
                       DESCRICAO VARCHAR NOT NULL,
                       LOCAL VARCHAR NOT NULL
);


CREATE TABLE LIXEIRA_STATUS (
                                ID NUMBER PRIMARY KEY,
                                NOME VARCHAR NOT NULL,
                                DESCRICAO VARCHAR NOT NULL
);


CREATE TABLE TIPO_LIXEIRA (
                              ID NUMBER PRIMARY KEY,
                              NOME VARCHAR NOT NULL,
                              DESCRICAO VARCHAR NOT NULL
);


CREATE TABLE PERFIS (
                        ID NUMBER PRIMARY KEY,
                        NOME VARCHAR NOT NULL,
                        DESCRICAO VARCHAR NOT NULL
);


CREATE TABLE EVENTOS (
                         ID NUMBER PRIMARY KEY,
                         DESCRICAO VARCHAR,
                         DATA_CRIACAO TIMESTAMP NOT NULL,
                         STATUS_PROBLEMA VARCHAR NOT NULL,
                         ID_TIPO_EVENTOS NUMBER
);

ALTER TABLE EVENTOS ADD CONSTRAINT FK_TIPO_EVENTOS
    FOREIGN KEY (ID_TIPO_EVENTOS)
        REFERENCES TIPOS_EVENTOS (ID);


CREATE TABLE PREDIOS (
                         ID NUMBER PRIMARY KEY,
                         NOME VARCHAR NOT NULL,
                         ID_ZONA NUMBER NOT NULL
);

ALTER TABLE PREDIOS ADD CONSTRAINT FK_PREDIOS_ZONAS
    FOREIGN KEY (ID_ZONA)
        REFERENCES ZONAS (ID);


CREATE TABLE USUARIOS (
                          ID NUMBER PRIMARY KEY,
                          EMAIL VARCHAR,
                          NOME VARCHAR NOT NULL,
                          LOGIN VARCHAR NOT NULL,
                          SENHA VARCHAR NOT NULL,
                          BLOQUEADO BOOLEAN NOT NULL DEFAULT FALSE,
                          DT_CADASTRO TIMESTAMP NOT NULL,
                          ID_PERFIL NUMBER
);

ALTER TABLE USUARIOS ADD CONSTRAINT FK_USUARIOS_PERFIS
    FOREIGN KEY (ID_PERFIL)
        REFERENCES PERFIS (ID);


CREATE TABLE USUARIO_ZONA (
                              ID NUMBER PRIMARY KEY,
                              ID_USUARIO NUMBER,
                              ID_ZONA NUMBER
);

ALTER TABLE USUARIO_ZONA ADD CONSTRAINT FK_USUARIO_ZONA
    FOREIGN KEY (ID_USUARIO)
        REFERENCES USUARIOS (ID);

ALTER TABLE USUARIO_ZONA ADD CONSTRAINT FK_ZONA_USUARIO
    FOREIGN KEY (ID_ZONA)
        REFERENCES USUARIOS (ID);

CREATE TABLE LIXEIRAS (
                          ID NUMBER PRIMARY KEY,
                          MARCA VARCHAR NOT NULL,
                          DESCRICAO VARCHAR NOT NULL,
                          CAPACIDADE DOUBLE NOT NULL,
                          OCUPACAO DOUBLE NOT NULL,
                          ID_STATUS NUMBER,
                          ID_TIPO NUMBER,
                          ID_PREDIO NUMBER,
                          ID_ZONA NUMBER
);

ALTER TABLE LIXEIRAS ADD CONSTRAINT FK_LIXEIRAS_PREDIOS
    FOREIGN KEY (ID_PREDIO)
        REFERENCES PREDIOS (ID);

ALTER TABLE LIXEIRAS ADD CONSTRAINT FK_TIPO_LIV
    FOREIGN KEY (ID_TIPO)
        REFERENCES TIPO_LIXEIRA (ID);

ALTER TABLE LIXEIRAS ADD CONSTRAINT FK_LIXEIRA_STATUS
    FOREIGN KEY (ID_STATUS)
        REFERENCES LIXEIRA_STATUS (ID);

ALTER TABLE LIXEIRAS ADD CONSTRAINT FK_ZONAS_LIXEIRAS
    FOREIGN KEY (ID_ZONA)
        REFERENCES ZONAS (ID);

ALTER TABLE LIXEIRAS ADD CONSTRAINT LIXEIRA_PREDIO_OR_ZONA
    CHECK (ID_ZONA IS NULL OR ID_PREDIO IS NULL);


CREATE TABLE LIXEIRA_EVENTOS (
                                 ID NUMBER PRIMARY KEY,
                                 ID_EVENTO NUMBER,
                                 ID_LIXEIRA NUMBER,
                                 ID_USUARIO NUMBER,
                                 OCUPACAO DOUBLE,
                                 DATA TIMESTAMP NOT NULL
);

ALTER TABLE LIXEIRA_EVENTOS ADD CONSTRAINT FK_LIXEIRA_EVENTOS_EVENTOS
    FOREIGN KEY (ID_EVENTO)
        REFERENCES EVENTOS (ID);

ALTER TABLE LIXEIRA_EVENTOS ADD CONSTRAINT FK_LIXEIRA_EVENTOS_USUARIOS
    FOREIGN KEY (ID_USUARIO)
        REFERENCES USUARIOS (ID);

ALTER TABLE LIXEIRA_EVENTOS ADD CONSTRAINT FK_LIXEIRA_EVENTOS_LIXEIRA
    FOREIGN KEY (ID_LIXEIRA)
        REFERENCES LIXEIRAS (ID);

/* CREATE SEQUENCES */
CREATE SEQUENCE SQ_TIPOS_EVENTOS START 0;
CREATE SEQUENCE SQ_ZONAS START 0;
CREATE SEQUENCE SQ_LIXEIRA_STATUS START 0;
CREATE SEQUENCE SQ_TIPO_LIXEIRA START 0;
CREATE SEQUENCE SQ_PERFIS START 0;
CREATE SEQUENCE SQ_EVENTOS START 0;
CREATE SEQUENCE SQ_PREDIOS START 0;
CREATE SEQUENCE SQ_USUARIOS START 0;
CREATE SEQUENCE SQ_USUARIO_ZONA START 0;
CREATE SEQUENCE SQ_LIXEIRAS START 0;
CREATE SEQUENCE SQ_LIXEIRA_EVENTOS START 0;

/* INSERTS */

INSERT INTO TIPOS_EVENTOS VALUES (SQ_TIPOS_EVENTOS.nextval,
                                  'Report Lixeira',
                                  'Report de status da lixeira');

INSERT INTO TIPOS_EVENTOS VALUES (SQ_TIPOS_EVENTOS.nextval,
                                  'Manutenção',
                                  'Problemas no sensor');

INSERT INTO TIPOS_EVENTOS VALUES (SQ_TIPOS_EVENTOS.nextval,
                                  'Fogo',
                                  'Colocaram fogo na lixeira');

INSERT INTO TIPOS_EVENTOS VALUES (SQ_TIPOS_EVENTOS.nextval,
                                  'Outros',
                                  'Lixeira com infiltração');

INSERT INTO ZONAS VALUES (SQ_ZONAS.nextval,
                          'Norte',
                          'Prédios lado Bento Gonçalves',
                          'LTD; LGTD');

INSERT INTO ZONAS VALUES (SQ_ZONAS.nextval,
                          'Leste',
                          'Prédios lado Colégio',
                          'LTD; LGTD');

INSERT INTO ZONAS VALUES (SQ_ZONAS.nextval,
                          'Sul',
                          'Prédios lado Ipiranga',
                          'LTD; LGTD');

INSERT INTO LIXEIRA_STATUS VALUES (SQ_LIXEIRA_EVENTOS.nextval,
                                   'Cheia',
                                   'Lixeira esta com capacidade acima de 80%');

INSERT INTO LIXEIRA_STATUS VALUES (SQ_LIXEIRA_EVENTOS.nextval,
                                   'Vazia',
                                   'Lixeira esta com capacidade abaixo de 20%');

INSERT INTO LIXEIRA_STATUS VALUES (SQ_LIXEIRA_EVENTOS.nextval,
                                   'Manutenção',
                                   'A lixiera esta em manutenção');

INSERT INTO LIXEIRA_STATUS VALUES (SQ_LIXEIRA_EVENTOS.nextval,
                                   'Ativa',
                                   'A lixeira está em funcionamento');

INSERT INTO LIXEIRA_STATUS VALUES (SQ_LIXEIRA_EVENTOS.nextval,
                                   'Inativa',
                                   'A lixeira está forá de funcionamento');

INSERT INTO LIXEIRA_STATUS VALUES (SQ_LIXEIRA_EVENTOS.nextval,
                                   'Excluida',
                                   'A lixeira não existe');

INSERT INTO TIPO_LIXEIRA VALUES (SQ_TIPO_LIXEIRA.nextval,
                                 'Orgânica',
                                 'Lixo Organico');

INSERT INTO TIPO_LIXEIRA VALUES (SQ_TIPO_LIXEIRA.nextval,
                                 'Reciclavel',
                                 'Lixo Seco');

INSERT INTO PERFIS VALUES (SQ_PERFIS.nextval,
                           'Operador',
                           'Operador de lixo');

INSERT INTO PERFIS VALUES (SQ_PERFIS.nextval,
                           'Gestor',
                           'Gestor do sistema');

INSERT INTO EVENTOS VALUES (SQ_EVENTOS.nextval,
                            'REPORT',
                            CURRENT_TIMESTAMP,
                            'A',
                            1);

INSERT INTO EVENTOS VALUES (SQ_EVENTOS.nextval,
                            'Problema na lixeira',
                            CURRENT_TIMESTAMP,
                            'I',
                            2);

INSERT INTO PREDIOS VALUES (SQ_PREDIOS.nextval,
                            'Prédio 30',
                            1);

INSERT INTO PREDIOS VALUES (SQ_PREDIOS.nextval,
                            'Prédio 32',
                            1);

INSERT INTO PREDIOS VALUES (SQ_PREDIOS.nextval,
                            'Prédio 17',
                            2);

INSERT INTO PREDIOS VALUES (SQ_PREDIOS.nextval,
                            'Prédio 8',
                            3);

INSERT INTO PREDIOS VALUES (SQ_PREDIOS.nextval,
                            'Prédio 7',
                            3);

INSERT INTO PREDIOS VALUES (SQ_PREDIOS.nextval,
                            'Prédio 6',
                            3);

INSERT INTO USUARIOS(ID, EMAIL, NOME, LOGIN, SENHA, DT_CADASTRO, ID_PERFIL)
                        VALUES (SQ_USUARIOS.nextval,
                                'mario.araujo@edu.pucrs.br',
                                'Mario Specht',
                                'mario.araujo',
                                '102030',
                                CURRENT_TIMESTAMP,
                                1);

INSERT INTO USUARIOS(ID, EMAIL, NOME, LOGIN, SENHA, DT_CADASTRO, ID_PERFIL)
                        VALUES (SQ_USUARIOS.nextval,
                                'teste@pucrs.br',
                                'Gestor Teste',
                                'gestor.teste',
                                '102030',
                                CURRENT_TIMESTAMP,
                                2);

INSERT INTO USUARIO_ZONA VALUES (SQ_USUARIO_ZONA.nextval,
                                 1,
                                 1);

INSERT INTO USUARIO_ZONA VALUES (SQ_USUARIO_ZONA.nextval,
                                 2,
                                 1);

INSERT INTO LIXEIRAS VALUES (SQ_LIXEIRAS.nextval,
                             'Sanremo',
                             'Lixeira industrial sanremo',
                             50.0,
                             0.0,
                             4,
                             1,
                             1,
                             1);

INSERT INTO LIXEIRAS VALUES (SQ_LIXEIRAS.nextval,
                             'Sanremo',
                             'Lixeira industrial sanremo',
                             80.0,
                             0.0,
                             2,
                             2,
                             2,
                             2);

INSERT INTO LIXEIRA_EVENTOS VALUES (SQ_LIXEIRA_EVENTOS.nextval,
                                    1,
                                    1,
                                    1,
                                    21.2,
                                    CURRENT_TIMESTAMP);

INSERT INTO LIXEIRA_EVENTOS VALUES (SQ_LIXEIRA_EVENTOS.nextval,
                                    1,
                                    1,
                                    1,
                                    23.3,
                                    CURRENT_TIMESTAMP);

INSERT INTO LIXEIRA_EVENTOS VALUES (SQ_LIXEIRA_EVENTOS.nextval,
                                    1,
                                    1,
                                    1,
                                    53.2,
                                    CURRENT_TIMESTAMP);

INSERT INTO LIXEIRA_EVENTOS VALUES (SQ_LIXEIRA_EVENTOS.nextval,
                                    1,
                                    1,
                                    1,
                                    13.8,
                                    CURRENT_TIMESTAMP);