# leccion-distri-segundoparcial

Proyecto PurchaseOrder desarrollado con Spring Boot + Maven, conectado a una base de datos MySQL que se ejecuta en un contenedor Docker externo.

Este repositorio incluye la configuración necesaria para ejecutar la aplicación usando Docker Compose.

Requisitos Previos

Antes de ejecutar el proyecto asegúrate de tener instalado:

Git

Docker Desktop (Windows)

Docker Compose

JDK 17 (opcional, solo si compilas fuera de Docker)

Verificar instalación: 

docker --version
docker compose version

Base de Datos MySQL (Requisito)

La aplicación se conecta a una base de datos MySQL ya existente que debe estar corriendo en Docker con la siguiente configuración:

Contenedor MySQL activo

Puerto expuesto: 3307

Base de datos: sisdb2025

Usuario: AppRoot

Contraseña: abcd 

Ejecutar esto 

docker run --name mysql-sisdb2025 \ -e MYSQL_ROOT_PASSWORD=abcd \-e
MYSQL_DATABASE=sisdb2025 \ -p 3307:3306 \ -d mysql:8.0

y crear el usuario AppRoot 
Docker Compose

El archivo docker-compose.yml incluido en el proyecto levanta únicamente la aplicación Spring Boot: 


Ejecución del Proyecto
1️⃣ Clonar el repositorio


git clone https://github.com/jsmena5/leccion-distri-segundoparcial.git
cd PurchaseOrder 


Genera el archivo JAR necesario:

mvn clean package 

Levantar la aplicación con Docker

docker compose up --build

Acceso a la Aplicación

http://localhost:8088

Para cerrar la aplicacion 

docker compose down 
