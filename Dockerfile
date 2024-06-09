FROM openjdk:20-jdk

EXPOSE 5555

ADD target/cloud_service-0.0.1-SNAPSHOT.jar cloud_app.jar

ENTRYPOINT ["java", "-jar", "cloud_app.jar"]