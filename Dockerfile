FROM openjdk:11
VOLUME /tmp
EXPOSE 8863
ADD ./target/ms-credit-charge-0.0.1-SNAPSHOT.jar ms-credit-charge.jar
ENTRYPOINT ["java","-jar","/ms-credit-charge.jar"]
