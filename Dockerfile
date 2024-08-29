# Use uma imagem base de Java
FROM openjdk:17-jdk-slim

# Define o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copia o arquivo JAR gerado para o contêiner
ADD target/Aula001-0.0.1-SNAPSHOT.jar /app/Aula001-0.0.1-SNAPSHOT.jar

# Expõe a porta que a aplicação usa
EXPOSE 8080

# Comando para executar o aplicativo
ENTRYPOINT ["java", "-jar", "/app/Aula001-0.0.1-SNAPSHOT.jar"]
