FROM eclipse-temurin:17

LABEL mentainer="fscsilva0@gmail.com"

WORKDIR /app

COPY target/movies-0.0.1-0.jar /app/movies.jar

ENTRYPOINT ["java", "-jar", "movies.jar"]