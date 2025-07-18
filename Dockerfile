# Estágio 1: Build da Aplicação com Maven
# Usamos uma imagem que já vem com JDK e Maven
FROM maven:3.9.6-eclipse-temurin-17-focal AS build

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia primeiro o pom.xml para aproveitar o cache do Docker
COPY pom.xml .

# Baixa as dependências
RUN mvn dependency:go-offline

# Copia o resto do código fonte
COPY src ./src

# Compila o projeto e gera o .jar (pulando os testes para agilizar o build)
RUN mvn clean package -DskipTests

# ---

# Estágio 2: Imagem Final para Execução
# Usamos uma imagem leve, apenas com o Java Runtime (JRE)
FROM openjdk:21

# Define o diretório de trabalho
WORKDIR /app

# Copia o .jar que foi gerado no Estágio 1 para a imagem final
COPY --from=build /app/target/api-0.0.1-SNAPSHOT.jar app.jar

# Define o ponto de entrada para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]