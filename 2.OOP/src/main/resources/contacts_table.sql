CREATE TABLE IF NOT EXISTS Contacts (
    ContactID SERIAL PRIMARY KEY,    -- Auto-incrementing primary key
    FirstName VARCHAR(50) NOT NULL, -- First name of the contact
    LastName VARCHAR(50) NOT NULL,  -- Last name of the contact
    Email VARCHAR(100) UNIQUE,      -- Unique email address
    PhoneNumber VARCHAR(15),        -- Optional phone number
    CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP -- Timestamp of creation
);
