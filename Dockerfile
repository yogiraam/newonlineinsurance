FROM openjdk:8
COPY build/libs/OnlineInsurance-1.jar /app.jar
EXPOSE 8009
CMD java -jar /app.jar