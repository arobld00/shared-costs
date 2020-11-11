FROM openjdk:11
VOLUME /tmp
# RUN addgroup -S spring && adduser -S spring -G spring
# USER spring:spring
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]

# docker build -t api-docker-image .
# docker run -d -p 9090:8082 api-docker-image