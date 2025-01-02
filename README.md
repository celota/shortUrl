# shortUrl

Servicio que permite recortar URLs largas para obtener URLs mas compactas y faciles de compartir.


### Configuración

Es un proyecto Spring Boot (versión 3.1.1) compatible con Java 17.
Se requiere tener instalado Docker, alguna herramienta tipo Postman o Curl para invocar servicios y algun cliente para visualizar datos de MongoDB (MongoDBCompass).

Para levantar el proyecto:
 - 1. Ejecutar UrlShortenerApplication.java
 - 2. Al iniciar, spring-boot-docker-compose procesa el docker-compose.yml que está en la raiz del proyecto, para descargar la imagen de mongodb y luego generar el contenedor.
 - 3. Luego levanta la aplicacion y está disponible en el puerto 8080.


### Uso

Para recortar URLs solo hay que invocar al RestController shorten-url.
Persitira las URLs en una base mongoDB.
El servicio retornará la url pasada como parámetro, la nueva URL recortada y un flag que indica si está activa o no.

Ejemplo:

Request

{
    "url":"http://www.infobae.com"
}

Response

{
    "url": "http://www.infobae.com",
    "shortenUrl": "http://localhost:8080/AHfaN",
    "active": true
}

Para probar la url recortada solo hay que copiar y pegar en algun browser la nueva URL.

### Dockerfile

En la raiz del proyecto se encuentra el Dockerfile que contiene las especificaciones para generar la imagen docker correspondiente a la app.

### DockerCompose

En la carpeta docker se encuentra el docker-compose.yml que nos permite generar las imagenes correspondientes a mongoDB y a la aplicacion. 
Para el service urlsvc se setean 3 variables de entorno que son las que se van a tener en cuenta al momento de setear propiedades para mongoDB (host, url, y el nombre de la BD).
El application.yaml definirá sus valores para las propiedades descriptas en relación a si estan seteadas determinadas variables de entorno o no. Si estan seteadas las propiedades tendran un valor y si no lo estan tendran otro valor.









 