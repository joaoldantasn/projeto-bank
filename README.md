# Ebank

## Descrição
Ebank é um sistema bancário desenvolvido em Java utilizando Spring Boot. O objetivo é proporcionar uma solução de banco digital com funcionalidades de gerenciamento de contas e transações financeiras.

## Tecnologias Utilizadas
- Java 17+
- Spring Boot
- MySQL
- H2 Database
- Spring Security (JWT)
- Validation
- Swagger
- Jacoco

## Configuração do Ambiente
### Pré-requisitos
- Java 17+
- Maven 3.8+

## Diagrama Entidade Relacionamento
![der](https://github.com/user-attachments/assets/fb4186b5-bf28-40dc-b1f3-a7e3c326cd3e)

## Configurações de Banco de Dados
- O projeto utiliza um banco de dados H2 em memória, com as configurações especificadas no arquivo `application.properties-test`.
- E também MySQL, com as configurações especificadas no arquivo `application.properties-dev`.

## Autenticação e Segurança
- JWT é utilizado para autenticação no sistema, configurado no `SecurityFilterChain` e gerenciado pelo `TokenService`.
- **Níveis de Acesso:**
  - **Admin:** Pode criar novos usuários e gerenciar o sistema.
  - **User:** Acesso restrito às suas próprias contas e dados.

## Estrutura do Projeto
- **src/main/java**: Código-fonte.
- **src/main/resources**: Arquivos de configuração.
- **src/test/java**: Testes.

## Premissas acordadas
- Para combater qualquer tipo de problemas de acesso as rotas, definimos limitações de uso:
  - 	Tirando o Login, todo endpoint acessado na aplicação exige um token de autenticação;
  - 	Apenas um usuário do tipo ADMIN pode criar um novo usuário;
  - 	Apenar um usuário do tipo ADMIN tem acesso aos endpoints de Agencia.

## Endpoints da API
![endpoint1](https://github.com/user-attachments/assets/08e3e5d7-b071-438b-9fd9-af35661157f2)
![endpoint2](https://github.com/user-attachments/assets/310fcb3f-f538-46bc-ab51-aeb5eb3069bc)
![endpoint3](https://github.com/user-attachments/assets/7882f9f3-ae1f-4fcf-ab7d-42515f4426e5)

