<h1 align="center">apivendas</h1>

<p align="center">
<img src="https://s3.amazonaws.com/kp-blog/wp-content/uploads/2018/09/27121939/vendas-online-estrategias-dicas-para-vender-mais-pela-internet.jpg" heigth="15%" width="20%">
</p>

<h4 align="center">API Rest em Java para sistemas de vendas.</h4>

# Motivação
### Fiz esse projeto para praticar a criação de API Rest com o Spring.

# Conteúdo
- [Ferramentas necessárias](#Ferramentas-necessárias)
- [Desenvolvimento](#Desenvolvimento)
- [Construção](#Construção)


## Ferramentas necessárias
## Para executar o projeto, será necessário instalar os seguintes programas:

* [Postgresql 12](https://www.postgresql.org/download/) *(se for utilizar o banco em um container não é necessário)*
* [Docker](https://www.docker.com/products/docker-desktop)
* [Docker-compose](https://docs.docker.com/compose/install/)
* [JDK 11](https://www.oracle.com/br/java/technologies/javase-jdk11-downloads.html)
* [Maven 3.6.3](http://mirror.nbtelecom.com.br/apache/maven/maven-3/3.6.3/binaries/apache-maven-3.6.3-bin.zip)
* [Spring Tool Suite](https://spring.io/tools)

## Desenvolvimento

1 - Clonar o projeto do GitHub num diretório de sua preferência:

```shell
$ cd "diretorio de sua preferencia"
$ git clone https://github.com/ivanilsonaraujojr/apivendas.git
```
2 - [Importe o projeto para o Spring Tool Suite](https://www.youtube.com/watch?v=q06ODXpJ7-o&ab_channel=SergeyKargopolov)

3 - Ter um banco de dados postgresql instalado em sua maquina(ou rodando em um container)
#### `Comando para rodar um container docker de banco de dados postgresql na porta 5432 em localhost:`
```shell
$ docker run -d --name dbvendas -e "POSTGRES_PASSWORD=postgres" -e "POSTGRES_USER=postgres" -e "POSTGRES_DB=apivendas" -p 5432:5432 -v "${PWD}/dbvendas:/var/lib/postgresql/data" postgres:12-alpine
```
4 - São necessárias as seguintes variaveis de ambientes para rodar a aplicação:
* ### POSTGRES_HOST (URL do banco de dados)
* ### POSTGRESQL_PORT_NUMBER (Porta do banco de dados)
* ### POSTGRES_DB (Nome do banco de dados)
* ### POSTGRES_USER (Nome do usuário do banco de dados)
* ### POSTGRES_PASSWORD (Senha do usuário do banco de dados)
* ### APIVENDAS_AUTH_SECRET (Secret utilizada para gerar os tokens JWT)

5 - Rodar a classe "ApiVendasApplication" e o Spring irá ligar um servidor localhost na porta 8080 e criar as tabelas e relacionamentos no banco de dados.

## Construção

1 - Para construir o projeto com o Maven, executar o comando abaixo na pasta raiz da aplicação:

```shell
$ mvn clean package
```

#### O comando irá baixar todas as dependências do projeto e criar um diretório *target* com os artefatos construídos, que incluem o arquivo jar do projeto. Além disso, serão executados os testes unitários, e se algum falhar, o Maven exibirá essa informação no console.

2 - Buildar a imagem docker:

```shell
$ docker build -t apivendas:latest .
```

3 - Subir banco de dados e aplicação com docker compose:
```shell
$ docker-compose up -d
```
4 - Aplicação ficará disponivel em:
  * Endereço api: http://localhost:8080/
  * Endereço banco de dados: localhost:5432

## Features pendentes:
 - `Adicionar documentação da api com Swagger`

> Por [Ivanilson Junior](https://www.linkedin.com/in/ivanilson-junior-052937186/)!