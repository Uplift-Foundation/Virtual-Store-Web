FROM maven:3.9.9-eclipse-temurin-17-alpine AS build
ARG PROFILE
RUN mkdir -p /workspace
WORKDIR /workspace
COPY pom.xml /workspace
COPY src /workspace/src
RUN --mount=type=cache,target=/root/.m2 mvn -B clean package --file pom.xml -P $PROFILE

FROM eclipse-temurin:17-jre-alpine
COPY --from=build /workspace/target/*jar app.jar
EXPOSE 80
ENTRYPOINT [ "java", "-jar", "app.jar" ]