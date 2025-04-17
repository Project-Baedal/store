FROM openjdk:21-jdk-slim
COPY build/libs/store-0.0.1-SNAPSHOT.jar store.jar
ENV TZ=Asia/Seoul
ENTRYPOINT ["java", "-jar", "store.jar"]