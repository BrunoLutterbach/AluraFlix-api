# <h1 align="center"> AluraFlix API - Challenge Back-End Alura </h1>
<p align="center">
<img src="http://img.shields.io/static/v1?label=STATUS&message=EM%20DESENVOLVIMENTO&color=GREEN&style=for-the-badge"/>
</p>

<p align="center">
<img src="https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white"/>
<img src="https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white"/>
<img src="https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white">
</p>
<hr>

#### Projeto
Construção de uma API para compartilhamento de vídeos

#

#### Semana 01 - CRUD de Vídeos e testes no Insomnia

- [X] Retornar lista de vídeos
- [X] Retornar um vídeo por ID
- [X] Cadastrar vídeo
- [X] Atualizar vídeo
- [X] Inativar vídeo
- [X] Testes no Insomnia

 #
 
#### Semana 02 - CRUD de Categorias e testes unidade e integração.
- [X] Retornar lista de categorias
- [X] Retornar uma categoria por ID
- [X] Cadastrar categoria
- [X] Atualizar categoria
- [X] Deletar Categoria
- [X] Relação entre vídeos e categorias
- [X] Retornar vídeos por categoria
- [ ] Testes de unidade
- [ ] Testes de integração
 
 #
 
#### Semana 03 e 04 - Paginação, autenticação e deploy.
- [X] Paginação
- [X] Autenticação
- [X] Deploy

----

#### URL Deploy
> https://api-aluraflix22.herokuapp.com/

#

### Rotas
#
### Autenticação
| Método      | Rota        | Descrição | JSON |
| ----------- | ----------- | ---------- | ----------  |
| POST         | /auth       | Retornar o Bearer token <br> necessário nas requisições |  <pre>{<br>"email": "user@email.com",<br>"senha": "123456"<br>}</pre>|

#

### 1 Vídeos
#### 1.1 Retornar Vídeos
| Método | Rota | Descrição | 
| --- | --- | --- |
|GET | /videos | Retornar todos os vídeos |

##### Ordenação
```
https://api-aluraflix22.herokuapp.com/videos?sort=id,desc
```
##### Filtrar por Titulo
```
https://api-aluraflix22.herokuapp.com/videos?titulo=Spring
```
##### Paginação
```
https://api-aluraflix22.herokuapp.com/videos?page=0&size=2
```
![image](https://user-images.githubusercontent.com/95001637/206585283-0cf36740-b499-4a19-a8f0-8dfeea21ee0a.png)

#

#### 1.2 Obter Vídeo por ID

| Método | Rota | Descrição | 
| --- | --- | --- | 
|GET | /videos/{id} | Retornar um video por id |

![image](https://user-images.githubusercontent.com/95001637/206589115-1954824b-bfe8-4910-9499-faf6a68e3045.png)

#

#### 1.3 Obter Vídeos por Categoria

| Método | Rota | Descrição |
| --- | --- | --- |
|GET | /categorias/{id}/videos | Retornar todos os vídeos cadastrados na categoria |

![image](https://user-images.githubusercontent.com/95001637/206589550-105764e6-db88-45f3-9fc2-af73ed731596.png)

#

#### 1.4 Cadastrar Vídeo

| Método | Rota | Descrição | JSON | 
| --- | --- | --- | --- | 
|POST | /videos | Cadastrar um video | <pre>{<br> "titulo": "Spring Boot 02",<br> "descricao": "Aprenda a criar sua primeira Api em Java com Spring Boot",<br> "url": "https://www.youtube.com", <br> "categoriaId": 1<br>}</pre> |

| Nome  | Descrição | 
| --- | --- | 
|titulo | Obrigatório | 
|descricao  | Obrigatório | 
|url | Obrigatório | 
|categoriaId | Opcional | 

![image](https://user-images.githubusercontent.com/95001637/206586829-21d2d4a8-0f34-4643-9ccf-6ee9fa788855.png)

#
#### 1.5 Atualizar Vídeo

| Método | Rota | Descrição | JSON | 
| --- | --- | --- | --- | 
|POST | /videos | Atualizar um video | <pre>{<br> "titulo": "Spring Boot 02 - Requisições PUT",<br> "categoriaId": 14 <br>}</pre> |

| Nome  | Descrição | 
| --- | --- | 
|titulo | Opcional | 
|descricao  | Opcional | 
|url | Opcional | 
|categoriaId | Opcional | 

![image](https://user-images.githubusercontent.com/95001637/206588400-ff5ea774-1acb-4c31-a6fe-a20436c3641f.png)

#

#### 1.6 Inativar Vídeo
| Método | Rota | Descrição |
| --- | --- | --- |
|DEL | /videos/{id} |Inativar um video por id |

![image](https://user-images.githubusercontent.com/95001637/206588656-6d3aeffb-b43b-48ad-9632-e1f43adbebf6.png)

<hr>

### 2 Categorias

#### 2.1 Retornar Categorias
| Método | Rota | Descrição | 
| --- | --- | --- |
|GET | /categorias | Retornar todas as categorias |

##### Ordenação
```
https://api-aluraflix22.herokuapp.com/categorias?sort=id,desc
```
##### Filtrar por Titulo
```
https://api-aluraflix22.herokuapp.com/categorias?titulo=livre
```
##### Paginação
```
https://api-aluraflix22.herokuapp.com/categorias?page=0&size=2
```
![image](https://user-images.githubusercontent.com/95001637/206590082-8b7fba7f-41c4-409f-87cb-8ca11ee2b47b.png)

#

#### 2.2 Obter Categoria por ID

| Método | Rota | Descrição | 
| --- | --- | --- | 
|GET | /categoria/{id} | Retornar uma categoria por id |

![image](https://user-images.githubusercontent.com/95001637/206590116-d9aef239-649c-41d1-930a-6c3f0bc5505c.png)

#

#### 2.3 Obter Vídeos por Categoria

| Método | Rota | Descrição |
| --- | --- | --- |
|GET | /categorias/{id}/videos | Retornar todos os vídeos cadastrados na categoria |

![image](https://user-images.githubusercontent.com/95001637/206589550-105764e6-db88-45f3-9fc2-af73ed731596.png)

#

#### 2.4 Cadastrar Categoria

| Método | Rota | Descrição | JSON | 
| --- | --- | --- | --- | 
|POST | /categorias | Cadastrar uma categoria | <pre>{<br> "titulo": "SpringBoot",<br> "cor": "Verde"  <br>}</pre> |

| Nome  | Descrição | 
| --- | --- | 
|titulo | Obrigatório | 
|cor  | Obrigatório | 

![image](https://user-images.githubusercontent.com/95001637/206590144-fab30141-e433-4ea2-b143-abc69ad9679f.png)

#
#### 2.5 Atualizar Categoria

| Método | Rota | Descrição | JSON | 
| --- | --- | --- | --- | 
|POST | /categorias | Atualizar uma Categoria | <pre>{<br> "cor": "Verde Claro" <br>}</pre> |

| Nome  | Descrição | 
| --- | --- | 
|titulo | Opcional | 
|cor  | Opcional |  

![image](https://user-images.githubusercontent.com/95001637/206590341-d39905db-bdfa-49b1-9c9c-eab3e8ac2f4f.png)

#

#### 2.6 Deletar Categoria
| Método | Rota | Descrição |
| --- | --- | --- |
|DEL | /categorias/{id} |Deletarcategoria por id |

![image](https://user-images.githubusercontent.com/95001637/206590429-7532a98a-9d2c-4a7c-b96d-a8962686252e.png)

<hr>

### Exception Handler

Validação ao cadastrar Vídeo/Categoria

![image](https://user-images.githubusercontent.com/95001637/206590882-dcaa8e85-a94d-4402-b0a0-10bb4450f702.png)
![image](https://user-images.githubusercontent.com/95001637/206591007-318cfad2-b4ed-4f55-9109-18c3132c7b42.png)
![image](https://user-images.githubusercontent.com/95001637/206591422-435de332-8c22-4df9-a566-b626a29951d3.png)
#
Validação ao não localizar ID de Vídeo/Categoria

![image](https://user-images.githubusercontent.com/95001637/206591140-ac9145b6-76e6-42d6-883e-ed5d90e50d66.png)
![image](https://user-images.githubusercontent.com/95001637/206591157-0f6667ac-a3dd-41b2-b456-9d1c16b4f490.png)







