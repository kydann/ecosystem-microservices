FROM java:8-jre
MAINTAINER Equipo de Anzen Soluciones <mcoronel@anzen.com.mx>

ADD ./target/basic.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/basic.jar"]

EXPOSE 5000
