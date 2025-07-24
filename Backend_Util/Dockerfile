FROM maven:3.8.6-eclipse-temurin-17

RUN mkdir -p /opt/app

RUN mkdir -p /uploads

COPY ./App_api-0.0.1-SNAPSHOT.jar /opt/app

CMD ["java", "-jar", "/opt/app/App_api-0.0.1-SNAPSHOT.jar"]