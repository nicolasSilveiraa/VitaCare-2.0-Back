/*
  Flyway Migration: V1
  Description: Cria o schema 'professionals_service' e as tabelas
               para a gestão de profissionais e suas especialidades,
               utilizando uma relação Muitos-para-Muitos.
*/

CREATE SCHEMA IF NOT EXISTS professionals_service;


CREATE TABLE professionals_service.specialties (
                                                   id SERIAL PRIMARY KEY,
                                                   name VARCHAR(255) UNIQUE NOT NULL,
                                                   description TEXT
);


CREATE TABLE professionals_service.professionals (
                                                     id                   BIGSERIAL PRIMARY KEY,
                                                     full_name            VARCHAR(255) NOT NULL,
                                                     professional_license VARCHAR(255) UNIQUE NOT NULL
);


CREATE TABLE professionals_service.professionals_specialties (
                                                                 professional_id BIGINT NOT NULL,
                                                                 specialty_id    INTEGER NOT NULL,

                                                                 PRIMARY KEY (professional_id, specialty_id),

                                                                 CONSTRAINT fk_pro_spec_to_professionals
                                                                     FOREIGN KEY (professional_id) REFERENCES professionals_service.professionals (id) ON DELETE CASCADE,

                                                                 CONSTRAINT fk_pro_spec_to_specialties
                                                                     FOREIGN KEY (specialty_id) REFERENCES professionals_service.specialties (id) ON DELETE CASCADE
);