BEGIN TRANSACTION;
CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	approved BOOLEAN,
	first_name VARCHAR (50) NOT NULL,
    last_name VARCHAR (50) NOT NULL,
    email VARCHAR (50) NOT NULL,
    phone VARCHAR(10),
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);
CREATE TABLE amenity (
  amenity_id SERIAL,
  amenity_description VARCHAR (50) NOT NULL,
  CONSTRAINT PK_amenity PRIMARY KEY (amenity_id)
);
CREATE TABLE property (
    property_id SERIAL,
    rent NUMERIC(8,2) NOT NULL,
    property_type VARCHAR (50) NOT NULL,
    bathrooms INTEGER NOT NULL,
    bedrooms INTEGER NOT NULL,
    square_footage NUMERIC(6,0) NOT NULL,
    date_available DATE,
    street_number VARCHAR(10) NOT NULL,
    street_name VARCHAR (50) NOT NULL,
    apartment_unit VARCHAR (10),
    city VARCHAR (50) NOT NULL,
    state_abbreviation CHAR(2) NOT NULL,
    zip NUMERIC (5, 0) NOT NULL,
    pets_allowed BOOLEAN NOT NULL,
    property_description VARCHAR (3000),
    CONSTRAINT PK_property PRIMARY KEY (property_id)
);
CREATE TABLE image (
    image_id SERIAL,
    image_url VARCHAR(150) NOT NULL,
    property_id INT NOT NULL,

    CONSTRAINT PK_image PRIMARY KEY (image_id),
    CONSTRAINT FK_property_id FOREIGN KEY (property_id) REFERENCES property(property_id)
);
CREATE TABLE amenity_property (
  amenity_id SERIAL,
  property_id INTEGER NOT NULL,
  CONSTRAINT FK_amenity_id FOREIGN KEY (amenity_id) REFERENCES amenity(amenity_id),
  CONSTRAINT FK_property_id FOREIGN KEY (property_id) REFERENCES property(property_id)
);
CREATE TABLE person (
  person_id SERIAL,
  first_name VARCHAR (50) NOT NULL,
  last_name VARCHAR (50) NOT NULL,
  email VARCHAR (50) NOT NULL,
  phone VARCHAR(10),
  CONSTRAINT PK_person PRIMARY KEY (person_id)
);
CREATE TABLE property_landlord (
  property_id INTEGER NOT NULL,
  landlord_id INTEGER NOT NULL,
 CONSTRAINT PK_property_property_owner PRIMARY KEY (property_id, landlord_id),
 CONSTRAINT FK_property_id FOREIGN KEY (property_id) REFERENCES property(property_id),
 CONSTRAINT FK_landlord_id FOREIGN KEY (landlord_id) REFERENCES users(user_id)
);
CREATE TABLE property_renter (
  renter_id INTEGER NOT NULL,
  property_id INTEGER NOT NULL,
  CONSTRAINT PK_property_renter PRIMARY KEY (renter_id, property_id),
  CONSTRAINT FK_renter_id FOREIGN KEY (renter_id) REFERENCES users(user_id),
  CONSTRAINT FK_property_id FOREIGN KEY (property_id) REFERENCES property(property_id)
);
CREATE TABLE maintenance (
  maintenance_id SERIAL,
  property_id INTEGER NOT NULL,
  renter_id INTEGER NOT NULL,
  employee_id INTEGER,
  maintenance_status VARCHAR(50) NOT NULL,
  request_details VARCHAR (500) NOT NULL,
  notes VARCHAR (500),
  CONSTRAINT PK_maintenance_id PRIMARY KEY (maintenance_id),
  CONSTRAINT FK_property_id FOREIGN KEY (property_id) REFERENCES property(property_id),
  CONSTRAINT FK_renter_id FOREIGN KEY (renter_id) REFERENCES users(user_id),
  CONSTRAINT FK_employee_id FOREIGN KEY (employee_id) REFERENCES users(user_id)
);
CREATE TABLE rent (
  rent_id SERIAL,
  rent_status VARCHAR(50) NOT NULL,
  property_id INTEGER NOT NULL,
  balance NUMERIC(10,2) NOT NULL,
  rent_due_date DATE NOT NULL,
  CONSTRAINT PK_rent_id PRIMARY KEY (rent_id),
  CONSTRAINT FK_property_id FOREIGN KEY (property_id) REFERENCES property(property_id)
);
CREATE TABLE rent_log (
  rent_log_id SERIAL,
  rent_id INTEGER NOT NULL,
  transaction_type VARCHAR(50) NOT NULL,
  transaction_date DATE NOT NULL,
  amount NUMERIC (10, 2) NOT NULL,
  CONSTRAINT PK_rent_log PRIMARY KEY (rent_log_id),
  CONSTRAINT FK_rent_id FOREIGN KEY (rent_id) REFERENCES rent(rent_id)
);

COMMIT TRANSACTION;