# # Build stage
# FROM maven:3.8.5-openjdk-17 AS build
# COPY . .
# RUN mvn clean package -DskipTests
# # Run stage
# FROM openjdk:17.0.1-jdk-slim
# COPY --from=build /target/demo-0.0.1-SNAPSHOT.jar demo.jar
# EXPOSE 8080
# ENTRYPOINT ["java", "-jar", "demo.jar"]

# FROM openjdk:8-jdk-alpine
# ARG JAR_FILE=target/*.jar
# COPY ${JAR_FILE} app.jar
# ENTRYPOINT ["java","-jar","/app.jar"]

# FROM alpine/java:21-jdk

# # Create user to run app as (instead of root)
# RUN addgroup -S app && adduser -S app -G app

# # Use user "app"
# USER app

# # copy the jar file into the docker image
# COPY target/*.jar app.jar

# # Run the jar file 
# ENTRYPOINT [ "java","-jar","/app.jar"]

FROM maven:3.8.2-jdk-11 AS build
COPY . .
RUN mvn clean package -DskipTests

#
# Package stage
#
FROM openjdk:11-jdk-slim
COPY --from=build /target/demo-0.0.1-SNAPSHOT.jar demo.jar
# ENV PORT=8080
EXPOSE 8080
ENTRYPOINT ["java","-jar","demo.jar"]