FROM openjdk:8
VOLUME ["/tmp"]
EXPOSE 8080
ADD ./target/ReservaRecursos.jar backend-aws.jar
ENTRYPOINT ["java", "-jar", "/backend-aws.jar"]