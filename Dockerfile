FROM openjdk:11-jre-slim
COPY target/*.jar app.jar
COPY wait-for-it.sh /usr/wait-for-it.sh
RUN chmod +x /usr/wait-for-it.sh
