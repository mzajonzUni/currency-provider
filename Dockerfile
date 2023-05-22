FROM maven:<wersjaMavena-dystrybucjaJavyZWersja> AS MAVEN_BUILD
COPY ./pom.xml pom.xml
COPY ./src src/
RUN mvn clean package

FROM <dystrybujaJavy>:<versjaJavy>
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
COPY start.sh .
ENTRYPOINT ["bin/bash","start.sh"]