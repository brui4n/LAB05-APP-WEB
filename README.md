# 📘 Sistema de Gestión Académica - LAB05

Proyecto desarrollado en **Java (Servlets + JSP + JDBC + Maven)** con base de datos **MySQL**.  
Este sistema implementa el **módulo de Matrícula** del proceso académico.

---

## 🗄️ Configuración de Base de Datos
1. En la carpeta [`db_query/`](./db_query) encontrarás el script `schema.sql` con todas las tablas necesarias.  
   Ejecutar en MySQL Workbench u otra interfaz:
   ```sql
   SOURCE db_query/schema.sql;
2. Verifica que la base de datos se haya creado con el nombre lab05_db.
3. Configura la conexión en el archivo:
   src/main/resources/db.properties

## Datos de prueba
1. En caso quisiera probar datos de prueba directamente se le ah dejado en el archivo [`test/DataTest`](./src/main/java/pe/edu/tecsup/lab05/app05web/test/DataTest.java) para que pueda cargar datos directamente al ejecutar el test

## 📌 Rutas principales del Módulo de Matrícula 

1. Alumnos:http://localhost:8080/App05-web/alumnos
2. Periodos Academicos: http://localhost:8080/App05-web/periodos
3. Matriculas: http://localhost:8080/App05-web/matriculas
4. Detalle de matricula: http://localhost:8080/App05-web/detalleMatricula

### 📖 Notas adicionales

•	Los demás módulos (Notas y Asistencia) aún no están terminados.

•	El alcance principal de este proyecto es el módulo de Matrícula.

•	El archivo db.properties debe ser configurado antes de ejecutar la aplicación.

•	Datos de prueba pueden insertarse en la base con los scripts adicionales en db_query/.