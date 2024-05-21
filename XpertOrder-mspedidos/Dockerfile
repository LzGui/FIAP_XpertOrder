FROM openjdk:17-slim

WORKDIR /app

COPY . /app
RUN mvn clean package -DskipTests=true -Dmaven.test.skip=true

RUN cp /app/target/*.jar /app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "/app.jar"]
