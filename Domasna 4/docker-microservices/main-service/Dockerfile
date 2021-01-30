FROM openjdk:13
ADD target/main-service.jar main-service.jar
EXPOSE 9092
ENTRYPOINT ["java", "-jar", "main-service.jar"]