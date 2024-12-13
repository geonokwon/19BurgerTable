FROM openjdk:17-jdk-slim
COPY build/libs/BurgerTable-0.0.1-SNAPSHOT.jar /app/burgertable.jar
WORKDIR /app
ENTRYPOINT ["java", "-jar", "burgertable.jar"]