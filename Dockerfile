FROM openjdk:21-jdk-slim

EXPOSE 8080

WORKDIR /app

COPY build/libs/order-service-1.0.jar order-service.jar

CMD ["java", "-jar", "order-service.jar"]