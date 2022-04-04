FROM maven:3.8.1-openjdk-17 AS BUILDER

COPY ./ ./
RUN mvn test
RUN mvn clean package

FROM openjdk:17-alpine AS RUNNER

COPY --from=BUILDER /target/*.jar /app.jar

CMD ["java", "-jar", "/app.jar"]

