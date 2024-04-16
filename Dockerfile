FROM arm64v8/eclipse-temurin:21-jre
VOLUME /tmp
ARG JAR_FILE=target/*boot.jar
COPY ${JAR_FILE} poc.jar
ENTRYPOINT ["java","-jar","/poc.jar"]