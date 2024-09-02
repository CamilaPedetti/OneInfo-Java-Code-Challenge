# Dockerfile 
FROM openjdk:17-jdk-alpine

WORKDIR /app


ARG APP_VERSION
ENV APP_VERSION $APP_VERSION
RUN echo release version: $APP_VERSION

# Entrypoint
COPY  ./target/OneInfo-Java-Code-Challenge-0.0.1-SNAPSHOT.jar /app/app.jar


ENTRYPOINT ["java", "-jar", "app.jar"]


# Ports
EXPOSE 8099


