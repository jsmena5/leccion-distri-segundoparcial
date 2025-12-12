FROM openjdk:17.0.2
LABEL authors="H301 M1"

WORKDIR /app

COPY target/PurchaseOrder-0.0.1-SNAPSHOT.jar /app/app.jar

EXPOSE 8088

ENTRYPOINT ["java", "-jar", "app.jar"]