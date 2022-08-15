FROM openjdk:11
COPY target/time-speaker-1.0.0.jar time-speaker.jar
ENTRYPOINT ["java","-jar","time-speaker.jar"]
