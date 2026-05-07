FROM node:24-alpine

WORKDIR /app

# Copia e instala dependências primeiro
COPY package*.json .
RUN npm install

# Copia o resto do código
COPY . .

EXPOSE 5173

CMD ["npm", "run", "dev", "--", "--host"]