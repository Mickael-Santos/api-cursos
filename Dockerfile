# Etapa de build
FROM ubuntu:latest AS build

# Definir o diretório de trabalho
WORKDIR /app

# Copiar o pom.xml
COPY ./api-cursos/pom.xml .

# Copiar o diretório src
COPY ./api-cursos/src ./src

# Atualizar e instalar dependências
RUN apt-get update && \
    apt-get install -y openjdk-17-jdk maven && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

# Executar o Maven para construir o projeto
RUN mvn clean install

# Etapa de execução
FROM openjdk:17-jdk-slim

# Expor a porta
EXPOSE 8080

# Copiar o artefato JAR gerado
COPY --from=build /app/target/api-cursos-0.0.1-SNAPSHOT.jar app.jar

# Definir o comando de entrada
ENTRYPOINT ["java", "-jar", "app.jar"]
