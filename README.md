# 📚 If-Books

**If-Books** é uma API RESTful para gerenciamento de livros e autores, desenvolvida como projeto acadêmico da disciplina _Introdução ao Desenvolvimento de Softwares Web_. Ela permite realizar operações CRUD em entidades como `Livro` e `Autor`.

## 🚀 Funcionalidades

- Listagem de livros e autores
- Cadastro, atualização e exclusão de livros
- Cadastro, atualização e exclusão de autores
- Relacionamento entre autores e seus respectivos livros
- Respostas em JSON

## 🛠️ Tecnologias Utilizadas

- Java
- Spring Boot
- Maven
- Spring Data JPA
- H2 Database
- Lombok

## 📦 Instalação

1. Clone o repositório:
   ```bash
   git clone https://github.com/ritomarcus/If-Books.git
   cd If-Books
   ```
2. Compile o projeto com Maven:
   ```bash
   mvn clean install
   ```
3. Execute a aplicação:
   ```bash
   mvn spring-boot:run
   ```

4. Acesse a API em: `http://localhost:8080`

## 🧪 Exemplos de Uso

### ➕ Criar um autor
```http
POST /autores
Content-Type: application/json

{
  "nome": "Machado de Assis"
}
```

### 📖 Listar livros
```http
GET /livros
```

### ✍️ Criar um livro
```http
POST /livros
Content-Type: application/json

{
  "titulo": "Dom Casmurro",
  "autorId": 1
}
```

## 📘 Endpoints Principais

| Método | Endpoint      | Descrição                |
|--------|---------------|--------------------------|
| GET    | /livros       | Lista todos os livros    |
| GET    | /autores      | Lista todos os autores   |
| POST   | /livros       | Cria um novo livro       |
| PUT    | /livros/{id}  | Atualiza um livro        |
| DELETE | /livros/{id}  | Remove um livro          |

## 🧑‍💻 Contribuições

Sinta-se à vontade para abrir issues e pull requests. Contribuições são bem-vindas para melhorias e novas funcionalidades!

## 📄 Licença

Este projeto está sob a licença MIT. Consulte o arquivo [LICENSE](LICENSE) para mais informações.

## 👤 Autor

Desenvolvido por [Marcus Rito](https://github.com/ritomarcus) durante o curso de Tecnologia em Sistemas para Internet.