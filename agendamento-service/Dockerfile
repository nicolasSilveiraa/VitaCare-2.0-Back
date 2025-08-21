#============================================
#   AGENDAMENTO-SERVICE
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

RUN mvn -B dependency:go-offline

COPY . .

RUN mvn -B clean package -DskipTests -pl agendamento-service -am

FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

COPY --from=build /app/agendamento-service/target/*.jar app.jar
EXPOSE 8085

ENTRYPOINT ["java", "-jar", "app.jar"]