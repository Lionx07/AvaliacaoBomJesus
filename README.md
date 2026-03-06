# Sistema de Gestão Escolar - Processo Seletivo Bom Jesus
Este projeto é uma plataforma completa para gestão de Alunos e Professores, focada em uma arquitetura limpa e escalável. O sistema permite o cadastro de pessoas com múltiplos endereços e suporte a upload de fotos de perfil integradas diretamente na nuvem.

# Tecnologias Utilizadas
* Spring Data JPA: Persistência de dados.

* MapStruct: Mapeamento performático entre Entidades e DTOs.

* Jackson: Manipulação de JSON polimórfico com @JsonSubTypes.

* RestTemplate: Comunicação com serviços externos (ImgBB).

# Como Configurar
No arquivo src/main/resources/application.properties, configure sua chave da API do ImgBB:
```
spring.application.name=av-bom-jesus
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.url=jdbc:h2:mem:av-bom-jesusdb
spring.jpa.hibernate.ddl-auto=update
spring.datasource.username=sa
spring.datasource.password=

spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

spring.servlet.multipart.max-file-size=5MB
spring.servlet.multipart.max-request-size=5MB
imgbb.api.key=9b9802b732feb71a46bfc58aec6ddcc3
```

# Endpoints

Porta padrão
```
http://localhost:8080
```

| Endpoint | Descrição | Retorno | Body |
| ------------- | ------------- | ------------- | ------------- |
| GET /person | Retorna todos os Perons| List<PersonDTOResponse> | |
| GET /person/student | Retorna todos os Students | List<StudentDTOResponse> | |
| GET /person/professor | Retorna todos os Professors | List<ProfessorDTOResponse> | |
| GET /person/{id} | Retorna um Person | PersonDTOResponse  | |
| POST /person | Adciona um Person, se for um Student, adiciona uma imagem (File) | PersonDTOResponse | PersonDTORequest + File |
| PUT /person/{id} | Edita um Person | PersonDTOResponse | PersonDTORequest + id |
| DELETE /person/{id} | Deleta um Person |  | id |
| DELETE /person/soft-delete/{id} | Atera um Person para DISABLE |  | id |


Desenvolvido por Joao Victor Batis Santos para o projeto Av. Bom Jesus.
