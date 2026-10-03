FROM eclipse-temurin:24-jdk

WORKDIR /app

COPY src ./src
COPY lib ./lib

RUN mkdir out && javac -cp "lib/mysql-connector-j-26.7.0.jar" -d out src/model/*.java src/service/*.java src/api/*.java src/ui/*.java

EXPOSE 8081

CMD ["java", "-cp", "out:lib/mysql-connector-j-26.7.0.jar", "api.PlacementServer"]