FROM amazoncorretto:19-alpine3.15

MAINTAINER kelvosk

ADD ./build/libs/*.jar app.jar

EXPOSE 8080

ENV JAVA_HOME=/usr/lib/jvm/default-jvm
ENV PATH=$PATH:/usr/lib/jvm/default-jvm/bin

ENTRYPOINT ["java", "-jar", "app.jar"]