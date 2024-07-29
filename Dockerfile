FROM alpine:latest
RUN apk add openjdk17-jre && addgroup -S spring-boot-group && adduser -S spring-boot -G spring-boot-group
WORKDIR /app
COPY target/*.jar /app/app.jar
USER spring-boot:spring-boot-group
ENTRYPOINT ["java", "-jar", "app.jar"]
