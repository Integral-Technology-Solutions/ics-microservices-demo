FROM java:8
VOLUME /tmp
ADD app.jar app.jar
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS="-Djava.security.egd=file:/dev/./urandom"
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar /app.jar" ]
