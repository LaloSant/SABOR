# SABOR

SABOR es una plataforma web de recetas que permite explorar y buscar recetas, consultar sus ingredientes y pasos de preparación, calificarlas y guardarlas como favoritas. Las personas usuarias también pueden crear y editar sus propias recetas. Incluye un flujo para solicitar imágenes para pasos de preparación y que un moderador revise esas peticiones.

El proyecto fue desarrollado por **flerchante** para las materias de **FIS, IS y GP** en el **Instituto Tecnológico de Toluca**.

## Equipo

- Eduardo Jair Bautista Santiesteban
- Alan Daniel Farfan Gomez
- Yal Sampayo Marin

## Tecnologías y herramientas

- **Java 25** y **Spring Boot** para la aplicación.
- **Spring MVC** y **Thymeleaf** para las rutas web y la generación de vistas HTML.
- **Spring Data JPA / Hibernate** para el mapeo y la persistencia de datos.
- **MySQL**, con configuración compatible con TiDB, como base de datos.
- **Spring Security** para autenticación, autorización y gestión de contraseñas con BCrypt.
- **Maven** para dependencias y construcción del proyecto.
- **Lombok** para reducir código repetitivo en las clases Java.
- **Containerfile**, compatible con Podman o Docker, para empaquetar y ejecutar la aplicación en un contenedor.
- **Vercel** para publicar la aplicación.

## Estructura del proyecto

```text
.
├── Containerfile
├── pom.xml
├── sql/                         # Scripts SQL de creación, consultas y borrado, para pruebas locales. En deploy se ocupo la creacion por JPA
└── src/
    ├── main/
    │   ├── java/com/flerchante/recetario/
    │   │   ├── config/          # Configuración de la aplicación
    │   │   ├── controller/      # Controladores y rutas web
    │   │   ├── model/           # Entidades y DTO
    │   │   ├── repository/      # Acceso a datos con JPA
    │   │   ├── security/        # Configuración de Spring Security
    │   │   └── service/         # Servicios y lógica de aplicación
    │   └── resources/
    │       ├── static/          # Imágenes y otros recursos estáticos
    │       └── templates/       # Vistas Thymeleaf
    └── test/                    # Pruebas
```

### Módulos principales

- **Recetas:** exploración, búsqueda por nombre, filtros por ingredientes, ordenamiento, detalle, creación, edición, eliminación y consulta de recetas propias.
- **Ingredientes y detalles:** catálogo de ingredientes y cantidades/costos asociados a cada receta.
- **Pasos:** instrucciones e imágenes de preparación.
- **Usuarios y roles:** registro, inicio de sesión, perfil, administración de datos y permisos.
- **Acciones de usuario:** favoritos y calificaciones.
- **Peticiones de imágenes:** solicitudes vinculadas a pasos, con estados para su revisión por moderación.
- **Patrocinadores:** información de patrocinadores y su relación con ingredientes.

### Interfaces de usuario

Las páginas se construyen con plantillas Thymeleaf en `src/main/resources/templates`. Entre las interfaces principales se encuentran:

- Inicio, inicio de sesión y registro.
- Descubrimiento y detalle de recetas, además de formularios para agregar y editar.
- Listado y detalle de ingredientes y patrocinadores.
- Perfil y configuración de cuenta.
- Panel de moderación de peticiones.

Los controladores entregan los datos a las plantillas y atienden las rutas HTTP. Las imágenes se sirven mediante rutas específicas de la aplicación.

## Arquitectura MVC

La aplicación organiza sus responsabilidades siguiendo el patrón **Modelo-Vista-Controlador (MVC)**:

1. **Modelo (`model/`, `repository/` y `service/`):** las entidades JPA representan la información del dominio; los repositorios consultan y guardan datos; y los servicios implementan operaciones y reglas de negocio.
2. **Vista (`src/main/resources/templates/`):** páginas HTML dinámicas renderizadas con Thymeleaf y recursos estáticos en `static/`.
3. **Controlador (`controller/`):** recibe las solicitudes web, coordina los servicios, prepara el modelo para la vista y decide qué respuesta o plantilla devolver.

La configuración de autenticación y autorización se encuentra en el módulo `security/`.

## Ejecución local

Se requiere Java 25, Maven y acceso a una base de datos compatible con MySQL. La conexión se configura con las variables de entorno `DB_URL`, `DB_USER` y `DB_PASSWORD`.

```bash
mvn clean package
```

Para ejecutar el JAR generado, define la configuración de base de datos y luego ejecuta:

```bash
java -jar target/sabor-0.0.1-SNAPSHOT.jar
```

La aplicación escucha en el puerto **5080**.

## Contenedor

El `Containerfile` usa una imagen de ejecución de Eclipse Temurin, copia el JAR construido a la imagen y expone el puerto 5080. Después de construir el proyecto, se puede crear y ejecutar una imagen local con Podman (o adaptar los comandos a Docker):

```bash
mvn clean package
podman build -t sabor .
podman run --name sabor -p 5080:5080 \
  -e DB_URL="jdbc:mysql://<host>:<puerto>/<base>?sslMode=VERIFY_IDENTITY" \
  -e DB_USER="<usuario>" \
  -e DB_PASSWORD="<contraseña>" \
  sabor:latest
```

Configura la URL, el usuario y la contraseña de la base de datos como variables de entorno en el entorno donde se ejecute el contenedor. No incluyas secretos en la imagen ni en el repositorio.

## Despliegue

La aplicación publicada está disponible en **[https://sabor-kappa.vercel.app](https://sabor-kappa.vercel.app)**. El despliegue en Vercel se realiza mediante el contenedor definido por `Containerfile`: primero se empaqueta la aplicación Spring Boot como JAR y luego se ejecuta en el contenedor, que expone el puerto 5080. La conexión a la base de datos debe configurarse en el entorno de despliegue mediante `DB_URL`, `DB_USER` y `DB_PASSWORD`.
