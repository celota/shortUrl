#capa1 
FROM alpine:latest

#solo aplica para mecon
#ENV https_proxy=$http_proxy

#instalamos la jdk11
RUN apk add openjdk17

#definimos variable de entorno
ENV JAVA_HOME /usr/bin

#carpeta de trabajo 
WORKDIR /app

#copia del jar 
#COPY origen destino (/app/app.jar)
COPY build/libs/urlshortener-0.0.1-SNAPSHOT.jar app.jar

#puerto a exponer
EXPOSE 8080

#run
CMD ["java","-jar","app.jar"]
#CMD ["java","-Dspring.profiles.active=dev","-jar","app.jar"]

#run del contenedor
#ENTRYPOINT java "$APP_OPTIONS" -jar app.jar
