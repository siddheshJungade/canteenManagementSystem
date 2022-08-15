FROM maven:3.8.6-jdk-11

COPY . /usr/app/

WORKDIR /usr/app

cmd ["rm","-rf", "target/CanteenManagementSystem-1.0-SNAPSHOT-jar-with-dependencies.jar"]

cmd ["mvn","package"]

ENTRYPOINT ["java","-jar","target/CanteenManagementSystem-1.0-SNAPSHOT-jar-with-dependencies.jar"]

