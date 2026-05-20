# Usa Java 17 (compatível com seu projeto)
FROM eclipse-temurin:17-jdk-alpine

# Pasta dentro do container
WORKDIR /app

# Copia o jar gerado
COPY target/biblioteca.anandamoyi-0.0.1-SNAPSHOT.jar app.jar

# Porta da aplicação
EXPOSE 8080

# Comando para rodar
ENTRYPOINT ["java", "-jar", "app.jar"]