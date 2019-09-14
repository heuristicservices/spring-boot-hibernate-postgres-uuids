CREATE TABLE spring_boot_uuids.users (
    id UUID NOT NULL PRIMARY KEY,
    email TEXT UNIQUE NOT NULL,
    date_of_birth DATE NOT NULL
);