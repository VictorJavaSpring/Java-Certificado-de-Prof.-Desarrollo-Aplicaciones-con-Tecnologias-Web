# eWok

Proyecto Java EE legacy basado en Servlets/JSP.

## Tecnologías

- Java 8
- Eclipse Dynamic Web Project
- Apache Tomcat 8.5
- MariaDB / MySQL
- Jersey 1.19 REST
- JSTL 1.2

## Requisitos

- JDK 8
- Eclipse Enterprise Edition
- Apache Tomcat 8.5
- MariaDB

## Configuración base de datos

Crear base de datos:

```sql
CREATE DATABASE ewok CHARACTER SET utf8 COLLATE utf8_general_ci;
```

Crear usuario:

```sql
CREATE USER 'soctardes'@'localhost' IDENTIFIED BY 'soctardes';
GRANT ALL PRIVILEGES ON ewok.* TO 'soctardes'@'localhost';
FLUSH PRIVILEGES;
```

## Importar estructura SQL

Ejecutar:

```text
test/resources/scripts/DBCreation.sql
```

Después importar los ficheros `*Data.sql`.

## Configuración Eclipse

1. Import Existing Projects into Workspace
2. Configurar JDK 8
3. Añadir Tomcat 8.5 Runtime
4. Marcar Tomcat en:
   Project Properties → Targeted Runtimes

## Ejecutar

Run As → Run on Server

URL principal:

```text
http://localhost:8080/eWok/
```

## Endpoints

Home:
```text
http://localhost:8080/eWok/home
```

Menú:
```text
http://localhost:8080/eWok/ViewMenuServlet
```

REST:
```text
http://localhost:8080/eWok/data/
```