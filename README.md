# onboarding-customers-api
Java Spring-boot backend application for onboarding new customers

### Concepts
* Spring JPA - allows ORM and managing relational data in Java applications
* @Entity - The class is a persistent java class
* @Table
* @ID - ID is a primary key
* @GeneratedValue - defined generation strategy for the primary key
* @Column - Defined a column that represents a field or a list of values
* DDL - Data Definition language - defines, creates and modifies [CREATE,  ALTER, DROP, TRUNCATE]
* DML - Data Manipulation Language - allows for manipulation of data in the database such as deleting, updating, creating, reading or accessing data from the database (CRUD operations) [SELECT, INSERT, UPDATE, DELETE]
* DCL - allows for granting privileges in the database, security of the database and controlling concurrent access in the database by instructing the server, sucn as [GRANT, COMMIT, ROLLBACK, LOCK TABLE, CONNECT & REVOKE] commands in the database

### Using Postgres commandline Tool
* sudo -u postgres psql - create an admin user for you to interact with postgres db in the terminal
* \conninfo - checking connection status
* \l - list of databases available in the server
* \du -  a list of all users with their privileges
* \dt - check that your new table is created successfully
* \password postgres - set new password or change to a new password

### Postgre Database statements
* create database registration;
* 