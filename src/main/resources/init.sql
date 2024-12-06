/*
* This script is executed when the database is created.
* It is used to create the tables and insert the initial data.
* The script is executed only once when the database is created.
*/
-- Drop tables if they already exist (useful for re-initialization)
DROP TABLE IF EXISTS location, company, booking, customs, audition;

-- Create the location table
CREATE TABLE location (
                          id BINARY(16) NOT NULL PRIMARY KEY, -- UUID stored as BINARY(16)
                          name VARCHAR(255) NOT NULL,
                          restitution_code VARCHAR(100) NOT NULL,
                          restitution_name VARCHAR(255) NOT NULL
);

-- Create the company table
CREATE TABLE company (
                         id BINARY(16) NOT NULL PRIMARY KEY, -- UUID stored as BINARY(16)
                         line VARCHAR(255) NOT NULL,
                         equipment_owner_code VARCHAR(100) NOT NULL,
                         off_hire VARCHAR(100),
                         agent_code VARCHAR(100),
                         client_code VARCHAR(100),
                         shipper_code VARCHAR(100),
                         consignee_code VARCHAR(100)
);

-- Create the booking table
CREATE TABLE booking (
                         id BINARY(16) NOT NULL PRIMARY KEY, -- UUID stored as BINARY(16)
                         transport_company VARCHAR(255) NOT NULL,
                         vessel_id BINARY(16), -- UUID stored as BINARY(16)
                         voyage_id BINARY(16), -- UUID stored as BINARY(16)
                         transport_bounds VARCHAR(255),
                         booking_process VARCHAR(255),
                         goods_details TEXT,
                         ucn VARCHAR(100),
                         number_of_packs INT,
                         net_weight DOUBLE,
                         gross_weight DOUBLE,
                         temperature DOUBLE,
                         temperature_unit VARCHAR(10),
                         commodity_name VARCHAR(255),
                         commodity_expire_date DATE,
                         equipment_disc_name VARCHAR(255)
);

-- Create the customs table
CREATE TABLE customs (
                         id BINARY(16) NOT NULL PRIMARY KEY, -- UUID stored as BINARY(16)
                         goods_export_declaration TEXT,
                         goods_value DOUBLE,
                         situation VARCHAR(255),
                         custom_seal VARCHAR(100)
);

-- Create the audition table
CREATE TABLE audition (
                          user_id BINARY(16) NOT NULL, -- UUID stored as BINARY(16)
                          date DATE NOT NULL,
                          time TIME NOT NULL
);

-- Insert sample data into the tables
INSERT INTO location (id, name, restitution_code, restitution_name)
VALUES
    (UUID_TO_BIN(UUID()), 'Location A', 'R001', 'Restitution A'),
    (UUID_TO_BIN(UUID()), 'Location B', 'R002', 'Restitution B');

INSERT INTO company (id, line, equipment_owner_code, off_hire, agent_code, client_code, shipper_code, consignee_code)
VALUES
    (UUID_TO_BIN(UUID()), 'Line A', 'EOC001', NULL, 'A001', 'C001', 'S001', 'CS001'),
    (UUID_TO_BIN(UUID()), 'Line B', 'EOC002', 'OffHireCode', 'A002', 'C002', 'S002', 'CS002');

INSERT INTO booking (id, transport_company, vessel_id, voyage_id, transport_bounds, booking_process, goods_details, ucn,
                     number_of_packs, net_weight, gross_weight, temperature, temperature_unit, commodity_name,
                     commodity_expire_date, equipment_disc_name)
VALUES
    (UUID_TO_BIN(UUID()), 'Company A', UUID_TO_BIN(UUID()), UUID_TO_BIN(UUID()), 'Bounds A', 'Process A', 'Details A', 'UCN001',
     10, 500.0, 520.0, 15.0, 'C', 'Commodity A', '2024-12-31', 'Disc A');

INSERT INTO customs (id, goods_export_declaration, goods_value, situation, custom_seal)
VALUES
    (UUID_TO_BIN(UUID()), 'Declaration A', 10000.0, 'Approved', 'Seal001'),
    (UUID_TO_BIN(UUID()), 'Declaration B', 20000.0, 'Pending', 'Seal002');

INSERT INTO audition (user_id, date, time)
VALUES
    (UUID_TO_BIN(UUID()), '2024-11-28', '12:00:00'),
    (UUID_TO_BIN(UUID()), '2024-11-29', '15:30:00');
