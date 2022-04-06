FROM openjdk:17-alpine
ADD target/shop-report-service.jar shop-report-service.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "/shop-report-service.jar"]