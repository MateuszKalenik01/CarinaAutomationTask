
FROM openjdk:21-jdk-slim

WORKDIR /app

COPY . .

RUN apt-get update && \
    apt-get install -y maven && \
    apt-get clean

RUN mvn -v

CMD ["mvn", "clean", "test"]