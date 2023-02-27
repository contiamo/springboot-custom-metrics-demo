FROM openjdk:11 as builder

# install maven
RUN apt-get update && apt-get install -y maven

WORKDIR /app
COPY . .

RUN mvn clean package


FROM openjdk:11

WORKDIR /app

COPY --from=builder /app/target/*.jar /app/
COPY application.properties /app/

EXPOSE 8085

ENTRYPOINT [ "java", "-jar", "/app/demo-0.0.1-SNAPSHOT.jar" ]