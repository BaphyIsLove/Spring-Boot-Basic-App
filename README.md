# Spring Boot Basic App

Este es un proyecto de una aplicación básica desarrollada en Spring Boot con Java. Es un ejercicio sencillo que implementa un sistema CRUD utilizando MySQL para interactuar con una base de datos.

## Configuración de la base de datos
Antes de ejecutar la aplicación, asegúrate de configurar correctamente la conexión a la base de datos MySQL. Puedes modificar la configuración en el archivo application.properties ubicado en src/main/resources. Asegúrate de proporcionar las credenciales y el nombre de la base de datos correctos.

``` 
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/nombre_de_la_base_de_datos
spring.datasource.username=nombre_de_usuario
spring.datasource.password=contraseña
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update
```

## Uso de la aplicación
La aplicación ofrece una interfaz básica para interactuar con la base de datos mediante las operaciones de CRUD (Crear, Leer, Actualizar y Eliminar) en una entidad.

- Crear: Puedes agregar nuevos registros a la base de datos proporcionando la información requerida en un formulario.
- Leer: Puedes ver todos los registros almacenados en la base de datos en una tabla.
- Actualizar: Puedes modificar los datos de un registro existente seleccionándolo desde la tabla y editando los campos relevantes.
- Eliminar: Puedes eliminar un registro específico seleccionándolo desde la tabla y confirmando la acción.

Permisos segun el rol del usuario

ADMIN 

- puede crear "USER", "SUPERVISOR" o "ADMIN"
- es el unico que puede eliminar registros (no se puede eliminar a si mismo)
- puede modificar los datos de otros usuarios
- puede cambiar la contraseña de cualquier usuario

SUPERVISOR

- puede crear nuevos "USER"
- puede modificar solo sus datos
- puede cambiar su contraseña

USER

- puede modificar sus datos
- puede cambiar su contraseña