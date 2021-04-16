FROM adoptopenjdk:11-jre-hotspot

ENV TZ=America/Sao_Paulo
EXPOSE 8080
WORKDIR /app

COPY ./.docker-build/backend .

CMD ["java", "-jar", "/app/garbus-backend.war"]
