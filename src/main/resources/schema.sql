-- DROP TABLE IF EXISTS TYPES_EVENTS CASCADE;
-- DROP TABLE IF EXISTS USER_ZONE CASCADE;
-- DROP TABLE IF EXISTS TYPE_TRASH CASCADE;
-- DROP TABLE IF EXISTS PROFILES;
-- DROP TABLE IF EXISTS EVENTS;
-- DROP TABLE IF EXISTS BUILDINGS CASCADE;
-- DROP TABLE IF EXISTS ZONES CASCADE;
-- DROP TABLE IF EXISTS USERS CASCADE;
-- DROP TABLE IF EXISTS TRASH_STATUS CASCADE;
-- DROP TABLE IF EXISTS TRASHES_EVENTS;
-- DROP TABLE IF EXISTS TRASHES;
-- DROP TABLE IF EXISTS TRASHES_THRESHOLD;

-- DROP SEQUENCE IF EXISTS SQ_TYPES_EVENTS;
-- DROP SEQUENCE IF EXISTS SQ_ZONES;
-- DROP SEQUENCE IF EXISTS SQ_TRASH_STATUS;
-- DROP SEQUENCE IF EXISTS SQ_TYPE_TRASH;
-- DROP SEQUENCE IF EXISTS SQ_PROFILES;
-- DROP SEQUENCE IF EXISTS SQ_EVENTS;
-- DROP SEQUENCE IF EXISTS SQ_BUILDINGS;
-- DROP SEQUENCE IF EXISTS SQ_USERS;
-- DROP SEQUENCE IF EXISTS SQ_USER_ZONE;
-- DROP SEQUENCE IF EXISTS SQ_TRASHES;
-- DROP SEQUENCE IF EXISTS SQ_TRASHES_EVENTS;
-- DROP SEQUENCE IF EXISTS SQ_TRASHES_THRESHOLD;

/* CREATE TABLES */

CREATE TABLE IF NOT EXISTS TYPES_EVENTS (
                               ID SERIAL PRIMARY KEY,
                               NAME VARCHAR NOT NULL,
                               DESCRIPTION VARCHAR
);


CREATE TABLE IF NOT EXISTS ZONES (
                       ID SERIAL PRIMARY KEY,
                       NAME VARCHAR NOT NULL NOT NULL,
                       DESCRIPTION VARCHAR NOT NULL,
                       LONGITUDE NUMERIC,
                       LATITUDE NUMERIC
);


CREATE TABLE IF NOT EXISTS TRASH_STATUS (
                                ID SERIAL PRIMARY KEY,
                                NAME VARCHAR NOT NULL,
                                DESCRIPTION VARCHAR NOT NULL
);


CREATE TABLE IF NOT EXISTS TYPE_TRASH (
                              ID SERIAL PRIMARY KEY,
                              NAME VARCHAR NOT NULL,
                              DESCRIPTION VARCHAR NOT NULL
);


CREATE TABLE IF NOT EXISTS PROFILES (
                        ID SERIAL PRIMARY KEY,
                        NAME VARCHAR NOT NULL,
                        DESCRIPTION VARCHAR NOT NULL
);


CREATE TABLE IF NOT EXISTS EVENTS (
                         ID SERIAL PRIMARY KEY,
                         DESCRIPTION VARCHAR,
                         CREATION_DATE TIMESTAMP NOT NULL,
                         PROBLEM_STATUS VARCHAR NOT NULL,
                         ID_TYPE_EVENTS INTEGER
);

ALTER TABLE EVENTS ADD CONSTRAINT FK_TYPE_EVENTS
    FOREIGN KEY (ID_TYPE_EVENTS)
        REFERENCES TYPES_EVENTS (ID);


CREATE TABLE IF NOT EXISTS BUILDINGS (
                         ID SERIAL PRIMARY KEY,
                         NAME VARCHAR NOT NULL,
                         ID_ZONE INTEGER NOT NULL
);

ALTER TABLE BUILDINGS ADD CONSTRAINT FK_BUILDINGS_ZONES
    FOREIGN KEY (ID_ZONE)
        REFERENCES ZONES (ID);


CREATE TABLE IF NOT EXISTS USERS (
                          ID SERIAL PRIMARY KEY,
                          EMAIL VARCHAR,
                          NAME VARCHAR NOT NULL,
                          LOGIN VARCHAR NOT NULL,
                          PASSWORD VARCHAR NOT NULL,
                          BLOCKED BOOLEAN NOT NULL DEFAULT FALSE,
                          DT_REGISTER TIMESTAMP NOT NULL,
                          ID_PROFILE INTEGER
);

ALTER TABLE USERS ADD CONSTRAINT FK_USERS_PROFILES
    FOREIGN KEY (ID_PROFILE)
        REFERENCES PROFILES (ID);


CREATE TABLE IF NOT EXISTS USER_ZONE (
                              ID SERIAL PRIMARY KEY,
                              ID_USER INTEGER,
                              ID_ZONE INTEGER
);

ALTER TABLE USER_ZONE ADD CONSTRAINT FK_USER_ZONE
    FOREIGN KEY (ID_USER)
        REFERENCES USERS (ID);

ALTER TABLE USER_ZONE ADD CONSTRAINT FK_ZONE_USER
    FOREIGN KEY (ID_ZONE)
        REFERENCES USERS (ID);

CREATE TABLE IF NOT EXISTS TRASHES (
                         ID SERIAL PRIMARY KEY,
                         BRAND VARCHAR NOT NULL,
                         DESCRIPTION VARCHAR NOT NULL,
                         CAPACITY FLOAT NOT NULL,
                         OCCUPATION FLOAT NOT NULL,
                         ID_STATUS INTEGER,
                         ID_TYPE INTEGER,
                         ID_BUILDING INTEGER,
                         ID_ZONE INTEGER,
                         LONGITUDE NUMERIC,
                         LATITUDE NUMERIC
);

ALTER TABLE TRASHES ADD CONSTRAINT FK_TRASHES_BUILDINGS
    FOREIGN KEY (ID_BUILDING)
        REFERENCES Buildings (ID);

ALTER TABLE TRASHES ADD CONSTRAINT FK_TYPE_LIV
    FOREIGN KEY (ID_TYPE)
        REFERENCES TYPE_TRASH (ID);

ALTER TABLE TRASHES ADD CONSTRAINT FK_TRASH_STATUS
    FOREIGN KEY (ID_STATUS)
        REFERENCES TRASH_STATUS (ID);

ALTER TABLE TRASHES ADD CONSTRAINT FK_ZONES_TRASHES
    FOREIGN KEY (ID_ZONE)
        REFERENCES ZONES (ID);

ALTER TABLE TRASHES ADD CONSTRAINT TRASH_BUILDING_OR_ZONE
    CHECK (ID_ZONE IS NULL OR ID_BUILDING IS NULL);


CREATE TABLE IF NOT EXISTS TRASHES_EVENTS (
                                 ID SERIAL PRIMARY KEY,
                                 ID_EVENT INTEGER,
                                 ID_TRASH INTEGER,
                                 ID_USER INTEGER,
                                 OCCUPATION FLOAT,
                                 DATE TIMESTAMP NOT NULL
);

ALTER TABLE TRASHES_EVENTS ADD CONSTRAINT FK_TRASHES_EVENTS_EVENTS
    FOREIGN KEY (ID_EVENT)
        REFERENCES EVENTS (ID);

ALTER TABLE TRASHES_EVENTS ADD CONSTRAINT FK_TRASHES_EVENTS_USERS
    FOREIGN KEY (ID_USER)
        REFERENCES USERS (ID);

ALTER TABLE TRASHES_EVENTS ADD CONSTRAINT FK_TRASHES_EVENTS_TRASH
    FOREIGN KEY (ID_TRASH)
        REFERENCES TRASHES (ID);

CREATE TABLE IF NOT EXISTS TRASHES_THRESHOLD (
        ID SERIAL PRIMARY KEY,
        MAX_OCCUPATION FLOAT,
        COLOR VARCHAR NOT NULL,
        ID_TRASH INTEGER NOT NULL
);


/* CREATE SEQUENCES */
-- CREATE SEQUENCE SQ_TYPES_EVENTS START 0;
-- CREATE SEQUENCE SQ_ZONES START 0;
-- CREATE SEQUENCE SQ_TRASH_STATUS START 0;
-- CREATE SEQUENCE SQ_TYPE_TRASH START 0;
-- CREATE SEQUENCE SQ_PROFILES START 0;
-- CREATE SEQUENCE SQ_EVENTS START 0;
-- CREATE SEQUENCE SQ_BUILDINGS START 0;
-- CREATE SEQUENCE SQ_USERS START 0;
-- CREATE SEQUENCE SQ_USER_ZONE START 0;
-- CREATE SEQUENCE SQ_TRASHES START 0;
-- CREATE SEQUENCE SQ_TRASHES_EVENTS START 0;
-- CREATE SEQUENCE SQ_TRASHES_THRESHOLD START 0;