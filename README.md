# Hardware Control API

![java](https://camo.githubusercontent.com/57cec1c01287dfdc2a3fe64954936293c761b7fa9a7fc1b9de3916a295f15170/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f6a6176612d2532334544384230302e7376673f7374796c653d666f722d7468652d6261646765266c6f676f3d6f70656e6a646b266c6f676f436f6c6f723d7768697465)
![spring](https://camo.githubusercontent.com/49f645b5e439b0d748424412207eae5748b81d77563f866d8528f60c66b669e1/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f737072696e672d2532333644423333462e7376673f7374796c653d666f722d7468652d6261646765266c6f676f3d737072696e67266c6f676f436f6c6f723d7768697465)
![postgres](https://camo.githubusercontent.com/29e7fc6c62f61f432d3852fbfa4190ff07f397ca3bde27a8196bcd5beae3ff77/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f706f7374677265732d2532333331363139322e7376673f7374796c653d666f722d7468652d6261646765266c6f676f3d706f737467726573716c266c6f676f436f6c6f723d7768697465)
![jwt](https://camo.githubusercontent.com/4590c0af4aeb1b75233885f86e80c1da8cb2afd401173a40e41370f5cad5db20/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f4a57542d626c61636b3f7374796c653d666f722d7468652d6261646765266c6f676f3d4a534f4e253230776562253230746f6b656e73)  
## Índice

- <a href="#-Resumo">Sobre</a>
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
 
 
 

