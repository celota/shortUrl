# shortUrl

Servicio que permite recortar URLs largas para obtener URLs mas compactas y faciles de compartir.


### Descripcion
Basicamente consta un controller que hace todas las llamadas a los servicios correspondientes.
La capa de servicios será la encargada de realizar todas las operaciones mediante el uso de un repositorio especifico.
Se define una entidad para persistencia de datos y ciertos registros contenedores de informacion.
Tambien se definen excepciones de tipo runtime que seran utilizadas en caso de que se busque informacion inexistente.

### Configuración

Es un proyecto Spring Boot (versión 3.1.1) compatible con Java 17.
Se requiere tener instalado Docker, alguna herramienta tipo Postman o Curl para invocar servicios y algun cliente para visualizar datos de MongoDB (MongoDBCompass).

Para levantar el proyecto:
 - 1. Ejecutar UrlShortenerApplication.java
 - 2. Al iniciar, spring-boot-docker-compose procesa el docker-compose.yml que está en la raiz del proyecto, para descargar la imagen de mongodb y luego generar el contenedor.
 - 3. Luego levanta la aplicacion y está disponible en el puerto 8080.


### Uso

Para recortar URLs solo hay que invocar al RestController shorten-url.
El mismo, persitira las URLs en una base mongoDB y tambien retornará la url pasada como parámetro, la nueva URL recortada y un flag que indica si está activa o no.

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

En la carpeta docker se encuentra el docker-compose.yml que nos permite generar las contenedores correspondientes a mongoDB y a la aplicacion. 

El application.yaml definirá sus valores para ciertas propiedades (host, url, y el nombre de la MongoBD) en relación a si estan seteadas determinadas variables de entorno o no.

Para el service urlsvc se setean 3 variables de entorno que son las que se van a tener en cuenta al momento de setear propiedades para mongoDB (host, url, y el nombre de la BD).

```
	host: ${MONGO_HOST:localhost}
	database: ${MONGO_DATABASE:demo}
	uri: ${MONGO_URL:mongodb://localhost:27017/sa}
```








 
