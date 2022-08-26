--https://aws.amazon.com/blogs/database/managing-postgresql-users-and-roles/

--create new user
CREATE USER jatyteam WITH PASSWORD 'password';

--create custom role readwrite
CREATE ROLE readwrite;
GRANT CONNECT ON DATABASE postgres TO readwrite;
GRANT USAGE, CREATE ON SCHEMA public TO readwrite;
GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA public TO readwrite;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT SELECT, INSERT, UPDATE, DELETE ON TABLES TO readwrite;
GRANT USAGE ON ALL SEQUENCES IN SCHEMA public TO readwrite;

--give user jatyteam the role readwrite
grant readwrite to jatyteam;

--view name of current database;
SELECT current_database();

--view schema
SELECT schema_name
FROM information_schema.schemata;

--view all roles
SELECT rolname FROM pg_roles;

--view users and their roles
SELECT usename AS role_name,
 CASE
  WHEN usesuper AND usecreatedb THEN
    CAST('superuser, create database' AS pg_catalog.text)
  WHEN usesuper THEN
    CAST('superuser' AS pg_catalog.text)
  WHEN usecreatedb THEN
    CAST('create database' AS pg_catalog.text)
  ELSE
    CAST('' AS pg_catalog.text)
 END role_attributes
FROM pg_catalog.pg_user
ORDER BY role_name desc;

