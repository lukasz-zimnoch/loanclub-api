FROM openjdk:8-jre-slim
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE
ADD ${JAR_FILE} app.jar
ENV JAVA_OPTS="-XX:+UseG1GC -XX:+UseStringDeduplication -XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap -XX:MaxRAMFraction=1"
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar"]
