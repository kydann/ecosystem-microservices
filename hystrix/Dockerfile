FROM java:8-jre
MAINTAINER Equipo de Anzen Soluciones <mcoronel@anzen.com.mx>

ADD ./target/hystrix.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/hystrix.jar"]

EXPOSE 5000
