# Country Database Management App

A Java console application to manage a SQLite database of countries. Supports viewing, adding, updating, deleting, and counting countries.

## Features
1. **View All Countries**: Lists all countries with ID, name, area, and population.
2. **Add a Country**: Insert a new country into the database.
3. **Update a Country**: Modify details of a country by ID.
4. **Delete a Country**: Remove a country by ID.
5. **Count Countries**: Display the total number of countries.

## Setup
1. Create a SQLite database file `src/Labb3WassimTajeddin.db`.
2. Add a table `country`:
   ```sql
   CREATE TABLE country (
       countryId INTEGER PRIMARY KEY,
       countryName TEXT NOT NULL,
       countryArea INTEGER NOT NULL,
       countryPopulation INTEGER NOT NULL
   );
