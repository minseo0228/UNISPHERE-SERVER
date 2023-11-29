
FROM amazoncorretto:11

ENV TZ=Asia/Seoul

COPY build/libs/unisphere-0.0.1-SNAPSHOT.jar unisphere.jar

CMD java -jar -Dspring.profiles.active=prod ${JAVA_OPTS} unisphere.jar
