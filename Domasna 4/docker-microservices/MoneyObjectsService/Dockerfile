FROM openjdk:13
ADD target/money-objects-service.jar money-objects-service.jar
ADD src/main/java/com/hvt/dians/moneyobjectsservice/model/bootstrap/db /var/lib/docker/volumes/myapp/_data
EXPOSE 9091
ENTRYPOINT ["java", "-jar", "money-objects-service.jar"]