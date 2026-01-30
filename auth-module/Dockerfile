FROM eclipse-temurin:21-jre

WORKDIR /app

COPY target/*.jar app.jar

RUN addgroup --system spring && adduser --system spring --ingroup spring
USER spring

EXPOSE 8081

ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75"

ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar app.jar"]
