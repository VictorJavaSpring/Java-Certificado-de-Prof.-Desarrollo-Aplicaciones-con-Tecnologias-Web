# eWok - Modernization Branch

Branch de modernización del proyecto legacy Java EE `eWok`.

## Objetivo

Mantener una rama separada del sistema legacy funcional (`main`) para realizar:
- refactorizaciones
- actualización de dependencias
- migración tecnológica
- limpieza de arquitectura
- automatización de despliegue

sin comprometer la versión estable original.

---

# Estado actual del proyecto

## Stack legacy original

- Java 8
- Eclipse Dynamic Web Project
- Apache Tomcat 8.5
- JSP / Servlets
- Jersey 1.19
- JSTL 1.2
- MariaDB / MySQL
- JDBC manual
- Commons DBCP

## Base de datos

Base de datos:
```text
ewok
```

Usuario:
```text
soctardes
```

Password:
```text
soctardes
```

---

# Objetivos de modernización

## 1. Gestión de dependencias

- [ ] Migrar a Maven
- [ ] Eliminar jars manuales en `WEB-INF/lib`
- [ ] Versionado centralizado

---

## 2. Actualización Java

- [ ] Validar compatibilidad Java 11
- [ ] Migrar posteriormente a Java 17 o 21
- [ ] Revisar APIs deprecated

---

## 3. Modernización backend

- [ ] Migrar JDBC manual
- [ ] Introducir JPA/Hibernate
- [ ] Separar capas correctamente
- [ ] Mejorar manejo de excepciones
- [ ] Revisar pooling de conexiones

---

## 4. Migración web

- [ ] Revisar JSP legacy
- [ ] Evaluar Thymeleaf
- [ ] Revisar JSTL
- [ ] Modernizar frontend

---

## 5. REST API

- [ ] Sustituir Jersey 1.x
- [ ] Migrar a JAX-RS moderno o Spring REST
- [ ] Revisar serialización JSON

---

## 6. Seguridad

- [ ] Externalizar credenciales
- [ ] Variables de entorno
- [ ] Revisar autenticación
- [ ] Revisar sesiones

---

## 7. DevOps

- [ ] Dockerizar aplicación
- [ ] Docker Compose para MariaDB
- [ ] GitHub Actions
- [ ] Pipeline CI/CD

---

## 8. Limpieza técnica

- [ ] Eliminar código muerto
- [ ] Eliminar conflictos SVN antiguos
- [ ] Revisar naming
- [ ] Revisar encoding UTF-8
- [ ] Corregir warnings Eclipse

---

# Branches

## main

Versión legacy estable y funcional.

NO realizar cambios disruptivos directamente aquí.

---

## modernization

Rama experimental de evolución tecnológica.

---

# Cómo ejecutar el proyecto legacy

## Requisitos

- JDK 8
- Eclipse Enterprise
- Apache Tomcat 8.5
- MariaDB

---

## Base de datos

Crear:

```sql
CREATE DATABASE ewok CHARACTER SET utf8 COLLATE utf8_general_ci;
```

Usuario:

```sql
CREATE USER 'soctardes'@'localhost' IDENTIFIED BY 'soctardes';
GRANT ALL PRIVILEGES ON ewok.* TO 'soctardes'@'localhost';
FLUSH PRIVILEGES;
```

---

## Importación SQL

Ejecutar:
```text
test/resources/scripts/DBCreation.sql
```

Después importar:
```text
*Data.sql
```

---

# URLs

## Home
```text
http://localhost:8080/eWok/
```

## Menú
```text
http://localhost:8080/eWok/ViewMenuServlet
```

## REST
```text
http://localhost:8080/eWok/data/
```

---

# Notas técnicas

## Compatibilidad

El proyecto original fue desarrollado aproximadamente entre 2016-2017.

Puede requerir:
- Servlet 2.5
- APIs `javax.*`
- Tomcat legacy

NO usar Tomcat 10+ sin migración Jakarta.

---

# Roadmap recomendado

## Fase 1
- Estabilizar build
- Migrar a Maven

## Fase 2
- Actualizar JDBC y dependencias

## Fase 3
- Docker + CI/CD

## Fase 4
- Migración progresiva a Spring Boot

## Fase 5
- Sustitución JSP/frontend