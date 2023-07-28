# Spring Boot Basic App

Este es un proyecto de una aplicación básica desarrollada en Spring Boot con Java. Es un ejercicio sencillo que implementa un sistema CRUD utilizando MySQL para interactuar con una base de datos.

## Configuración de la base de datos
Antes de ejecutar la aplicación, asegúrate de configurar correctamente la conexión a la base de datos MySQL. Puedes modificar la configuración en el archivo application.properties ubicado en src/main/resources. Asegúrate de proporcionar las credenciales y el nombre de la base de datos correctos.

## Configuración de la base de datos
Antes de ejecutar la aplicación, asegúrate de configurar correctamente la conexión a la base de datos MySQL. Puedes modificar la configuración en el archivo application.properties ubicado en src/main/resources. Asegúrate de proporcionar las credenciales y el nombre de la base de datos correctos.

spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
--- modifica segun sea necesario ---
spring.datasource.url=jdbc:mysql://localhost:3306/spring_basic_app
spring.datasource.username=root
spring.datasource.password=todounamor
--- **************************** ---
spring.jpa.show-sql=true
#crea las tablas al ejecutar el codigo
spring.jpa.hibernate.ddl-auto=update


## Uso de la aplicación
La aplicación ofrece una interfaz básica para interactuar con la base de datos mediante las operaciones de CRUD (Crear, Leer, Actualizar y Eliminar) en una entidad.

- Crear: Puedes agregar nuevos registros a la base de datos proporcionando la información requerida en un formulario.
- Leer: Puedes ver todos los registros almacenados en la base de datos en una tabla.
- Actualizar: Puedes modificar los datos de un registro existente seleccionándolo desde la tabla y editando los campos relevantes.
- Eliminar: Puedes eliminar un registro específico seleccionándolo desde la tabla y confirmando la acción.
