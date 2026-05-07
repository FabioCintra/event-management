# Etapa 1: compilar o projeto
FROM eclipse-temurin:21-jdk-alpine AS build

WORKDIR /app

# Copia os arquivos do Maven
COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn

# Baixa as dependências primeiro (aproveita cache do Docker)
RUN ./mvnw dependency:go-offline -B

# Copia o código e compila
COPY src ./src
RUN ./mvnw package -DskipTests

# Etapa 2: imagem final menor, só pra rodar
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]