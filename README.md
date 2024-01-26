# Hardware Control API

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)
![IntelliJ IDEA](https://img.shields.io/badge/IntelliJIDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)
![Apache Tomcat](https://img.shields.io/badge/apache%20tomcat-%23F8DC75.svg?style=for-the-badge&logo=apache-tomcat&logoColor=black)

## Índice

- <a href="#-Resumo">Sobre</a>
- <a href="#Licença">Licença</a>
- <a href="#-Instalacao">Instalação</a>
- <a href="#-Uso">Uso</a>
- <a href="#-PrincipalFunc">Funcionalidades Principais</a>
- <a href="#-EndPoints">Endpoints</a>
- <a href="#-Auth">Autenticação</a>
- <a href="#-DB">Banco de Dados</a>
- <a href="#-Diagrama">Diagrama</a>
- <a href="#-Desenvolvedores">Desenvolvedores</a>



## Sobre

Esta é uma API Restful desenvolvida como suporte ao  uso de hardwares com acesso a requisições HTTP do tipo Json, ela tem por finalidade abstrair do hardware as regras de negócio transferindo-as para a api que é mais modular.
Por motivos acadêmicos a parte de autenticação da api foi abstraida e deixada para implementações futuras. Embora a api possa ser usada separadamente, ela foi pensada como um serviço.

## Licença


 [![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

## Instalação
 1. Clone o repositório

        https://github.com/Allamymp/hardwareControlAPI.git

 2. Tenha o Docker instalado na sua máquina : (https://docs.docker.com/manuals/)


## Uso

1. Inicie a aplicação via docker-compose no arquivo Compose.yaml
2. A api estará disponível na porta 8080 e o banco de dados Postgre na porta 5432

## Funcionalidades Principais 

- CRUD client
- CRUD hardware
- CR Event
- Envio de requisições Json


## API Endpoints

A API disponibiliza os seguintes EndPoints:

    Tipo PUT:
    
        - /api/client/update - atualiza um Client
        
          Exemplo:
        
   ![image](https://github.com/Allamymp/hardwareControlAPI/assets/61341833/2b5c1583-102e-4cf3-8595-c3c77c595566)

        - /api/hardware/update - atualiza um Hardware

          Exemplo
   
   ![image](https://github.com/Allamymp/hardwareControlAPI/assets/61341833/7921ec0c-d4e6-4546-906a-2713061c2c10)

    
    Tipo GET:
        - /api/client/all - retorna todos os Client
        - /api/client/{id} - retorna um Client pelo id
        - /api/hardware/all - retorna todos os Hardware
        - /api/hardware/{id} - retorna um Hardware pelo id
        - /api/event/all - retorna todos os Event
        - /api/event/{id} - retorna um Event por id.
    
    Tipo POST:
        - /api/client/create - cria um Client

          Exemplo

   ![image](https://github.com/Allamymp/hardwareControlAPI/assets/61341833/634f25e2-ed20-4e78-8ffe-a538e6a20f21)

        - /api/hardware/create - cria um Hardware

           Exemplo

   ![image](https://github.com/Allamymp/hardwareControlAPI/assets/61341833/4324ffb8-a4e4-4acf-8fb6-e31827de78b8)

        - /api/event/create - cria um Event

           Exemplo

   ![image](https://github.com/Allamymp/hardwareControlAPI/assets/61341833/fe63bd18-7277-4cd1-9109-1a16eced607e)

    
     Tipo DELETE:
        - /api/client/{id} - deleta um Client por id
        - /api/hardware/{id} - deleta um Hardware por id


## Banco de Dados

O projeto utiliza [PostgresSQL](https://www.postgresql.org/) como banco de dados e o [H2](https://www.h2database.com/html/main.html) como bando de teste. Ambos são gerados dentro do contêiner não sendo necessária a instalação manual.

## Diagrama

![uml_diagram](https://github.com/Allamymp/hardwareControlAPI/assets/61341833/bb109cbc-5266-45cc-a31d-57cad883ae7a)


## Desenvolvedores

Desenvolvedores que contribuiram com esta aplicação:

- Allamy Monteiro Pereira: [@Allamymp](https://github.com/Allamymp)
 
 
 

