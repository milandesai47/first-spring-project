FROM java:8
#WORKDIR C:\codebase\first-spring-project-master\first-spring-project-master
VOLUME /tmp
ARG DEPENDENCY=target/dependency
ADD ${WORKDIR}/target/first-spring-project-0.0.1-SNAPSHOT.jar first-spring-project-0.0.1-SNAPSHOT.jar
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","first-spring-project-0.0.1-SNAPSHOT.jar"]