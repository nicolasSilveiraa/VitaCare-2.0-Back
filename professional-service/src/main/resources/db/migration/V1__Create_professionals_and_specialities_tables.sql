IF NOT EXISTS (SELECT * FROM sys.schemas WHERE name = 'professionals_service')
    EXEC('CREATE SCHEMA professionals_service');

IF NOT EXISTS (SELECT * FROM sys.tables t
               JOIN sys.schemas s ON t.schema_id = s.schema_id
               WHERE t.name = 'specialties' AND s.name = 'professionals_service')
BEGIN
CREATE TABLE professionals_service.specialties (
                                                   id          INT IDENTITY(1,1) NOT NULL,
                                                   name        VARCHAR(255) NOT NULL UNIQUE,
                                                   description VARCHAR(MAX),
        CONSTRAINT pk_specialties PRIMARY KEY (id)
    );
END

IF NOT EXISTS (SELECT * FROM sys.tables t
               JOIN sys.schemas s ON t.schema_id = s.schema_id
               WHERE t.name = 'professionals' AND s.name = 'professionals_service')
BEGIN
CREATE TABLE professionals_service.professionals (
                                                     id                   BIGINT IDENTITY(1,1) NOT NULL,
                                                     full_name            VARCHAR(255) NOT NULL,
                                                     professional_license VARCHAR(255) NOT NULL UNIQUE,
                                                     CONSTRAINT pk_professionals PRIMARY KEY (id)
);
END

IF NOT EXISTS (SELECT * FROM sys.tables t
               JOIN sys.schemas s ON t.schema_id = s.schema_id
               WHERE t.name = 'professionals_specialties' AND s.name = 'professionals_service')
BEGIN
CREATE TABLE professionals_service.professionals_specialties (
                                                                 professional_id BIGINT  NOT NULL,
                                                                 specialty_id    INT     NOT NULL,
                                                                 CONSTRAINT pk_professionals_specialties PRIMARY KEY (professional_id, specialty_id),
                                                                 CONSTRAINT fk_pro_spec_to_professionals FOREIGN KEY (professional_id)
                                                                     REFERENCES professionals_service.professionals (id) ON DELETE CASCADE,
                                                                 CONSTRAINT fk_pro_spec_to_specialties FOREIGN KEY (specialty_id)
                                                                     REFERENCES professionals_service.specialties (id) ON DELETE CASCADE
);
END