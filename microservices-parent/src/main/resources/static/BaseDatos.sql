-- Crear el esquema de la base de datos
CREATE DATABASE IF NOT EXISTS demo_db;
USE demo_db;

-- Crear la tabla 'person'
CREATE TABLE person (
                        pr_secuencial INT AUTO_INCREMENT PRIMARY KEY,    -- ID autoincremental
                        pr_name VARCHAR(255) NOT NULL,                    -- Nombre
                        gender VARCHAR(10),                               -- Género
                        age INT,                                          -- Edad
                        identification VARCHAR(50),                       -- Identificación
                        address TEXT,                                     -- Dirección
                        phoneNumber VARCHAR(50)                           -- Teléfono
);

-- Crear la tabla 'client' (relacionada con 'person' por 'cl_id_person')
CREATE TABLE client (
                        cl_secuencial INT AUTO_INCREMENT PRIMARY KEY,    -- ID autoincremental
                        cl_password VARCHAR(255),                         -- Contraseña
                        cl_status VARCHAR(20),                            -- Estado
                        cl_id_person INT NOT NULL,                        -- Relación con 'person'
                        CONSTRAINT fk_client_person FOREIGN KEY (cl_id_person)
                            REFERENCES person(pr_secuencial)
                            ON DELETE CASCADE
);

-- Crear la tabla 'account' (relacionada con 'client' por 'ac_identification_client')
CREATE TABLE account (
                         ac_secuencial INT AUTO_INCREMENT PRIMARY KEY,      -- ID autoincremental
                         ac_number VARCHAR(50) NOT NULL,                    -- Número de cuenta
                         ac_type VARCHAR(20),                               -- Tipo de cuenta
                         ac_balance DECIMAL(15, 2) DEFAULT 0.00,            -- Balance de cuenta
                         ac_status VARCHAR(20),                             -- Estado de la cuenta
                         ac_identification_client INT,                      -- Relación con 'client' (ahora INT)
                         CONSTRAINT fk_account_client FOREIGN KEY (ac_identification_client)
                             REFERENCES client(cl_secuencial)
                             ON DELETE CASCADE
);

-- Crear la tabla 'movement' (relacionada con 'account' por 'mv_account')
CREATE TABLE movement (
                          mv_secuencial INT AUTO_INCREMENT PRIMARY KEY,      -- ID autoincremental
                          mv_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,       -- Fecha y hora
                          mv_type VARCHAR(20),                               -- Tipo de movimiento
                          mv_amount DECIMAL(15, 2),                          -- Monto del movimiento
                          mv_remaining DECIMAL(15, 2),                       -- Balance restante
                          mv_account INT,                                    -- Relación con 'account'
                          CONSTRAINT fk_movement_account FOREIGN KEY (mv_account)
                              REFERENCES account(ac_secuencial)
                              ON DELETE CASCADE
);
