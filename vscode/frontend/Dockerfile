# Use uma imagem base oficial do Node.js
FROM node:18-alpine

# Defina o diretório de trabalho no container
WORKDIR /app

# Copie o package.json e o package-lock.json para instalar dependências
COPY package*.json ./

# Instale as dependências
RUN npm install

# Copie o restante dos arquivos para o container
COPY . .

# Construa a aplicação frontend
RUN npm run build

# Exponha a porta em que o Vite irá rodar
EXPOSE 5173

# Comando para iniciar o servidor Vite
CMD ["npm", "run", "dev"]
