#============================================
#   PROFESSIONAL-SERVICE
#============================================
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app

COPY pom.xml .

COPY common/pom.xml ./common/
COPY convenio-client/pom.xml ./convenio-client/
COPY professional-client/pom.xml ./professional-client/
COPY paciente-client/pom.xml ./paciente-client/

COPY agendamento-service/pom.xml ./agendamento-service/
COPY api-gateway/pom.xml ./api-gateway/
COPY auth-service/pom.xml ./auth-service/
COPY config-server/pom.xml ./config-server/
COPY convenio-service/pom.xml ./convenio-service/
COPY paciente-comando-service/pom.xml ./paciente-comando-service/
COPY professional-service/pom.xml ./professional-service/
COPY emergencia-service/pom.xml ./emergencia-service/
COPY service-registry/pom.xml ./service-registry/

RUN mvn dependency:go-offline

COPY common/src ./common/src/
COPY convenio-client/src ./convenio-client/src/
COPY professional-client/src ./professional-client/src/
COPY paciente-client/src ./paciente-client/src/

COPY service-registry/src ./service-registry/src/
COPY config-server/src ./config-server/src/
COPY api-gateway/src ./api-gateway/src/
COPY auth-service/src ./auth-service/src/
COPY convenio-service/src ./convenio-service/src/
COPY paciente-comando-service/src ./paciente-comando-service/src/
COPY professional-service/src ./professional-service/src/
COPY agendamento-service/src ./agendamento-service/src/
COPY emergencia-service/src ./emergencia-service/src/

RUN mvn clean package -DskipTests -pl professional-service -am

FROM eclipse-temurin:17-jre-focal
WORKDIR /app

COPY --from=build /app/professional-service/target/*.jar app.jar
EXPOSE 8095

ENTRYPOINT ["java", "-jar", "app.jar"]