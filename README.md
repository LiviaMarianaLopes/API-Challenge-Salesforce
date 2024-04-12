<h1 align="center">API Challenge Java</h1>
<h2 align="center">API REST integrada com o Banco de Dados</h2>
<h4 align="center">
	🚧 Em Construção 🚀 🚧
</h4>
<p align="center">
 <a href="#objetivo">Objetivo</a> •
 <a href="#funcionalidades">Funcionalidades</a> • 
 <a href="#executar">Como Executar</a>
</p>

## 💻 Sobre o Projeto
O projeto foi desenvolvido com o propósito de criar uma API REST em Java, destinada a ser utilizada como backend no Challenge Salesforce. Esta é a terceira Sprint do Challenge da Salesforce, fornecido pela instituição FIAP.

## ⚙️ Funcionalidades
- [x] Requisições GET, POST, PUT e DELETE para a entidade USER
- [x] Conexão com o banco de dados

## 🚀 Como Executar
### Pré-requisitos

- Java JDK instalado na sua máquina
- Postman instalado para testar as requisições

### Execução do Projeto

1. Clone este repositório para o seu ambiente local.
2. Abra o projeto na sua IDE de preferência.
3. Certifique-se de que todas as dependências estão instaladas e atualizadas.



### Configuração do Banco de Dados

1. Abra a classe `DatabaseConfig` localizada em `src/main/java/fiap/ddd/Infrastructure`.
2. Insira suas credenciais do banco de dados nas variáveis `URL`, `USER` e `PASS`.

```java
private static final String URL = "jdbc:mysql://localhost:3306/nome-do-banco";
private static final String USER = "seu-usuario";
private static final String PASS = "sua-senha";
```
3. Execute a classe `DatabaseInitialization` localizada em `src/main/java/fiap/ddd/Infrastructure`.


### Testando as Requisições
1. Execute a classe `Main`.
2. Abra o Postman.
3. Importe a coleção de requisições fornecida neste repositório (collection.json).
Certifique-se de configurar as variáveis de ambiente no Postman com os valores adequados (URL base da API, por exemplo).
4. Execute as requisições e verifique as respostas para garantir que o projeto está funcionando corretamente.

## Agradecimento

Obrigado por conferir nosso projeto! Esperamos que ele seja útil para você. Se tiver alguma dúvida ou sugestão, não hesite em entrar em contato conosco.

---
