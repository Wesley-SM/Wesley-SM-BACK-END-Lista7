# Lista 07 - API de Gerenciamento (HTTP Methods)

Este projeto foi desenvolvido como parte do curso de Engenharia de Computação (Satc). A API demonstra o uso de verbos HTTP (GET, POST, PUT, PATCH, DELETE) e manipulação de listas em memória utilizando Java e Spring Boot.

## 🚀 Como rodar o projeto
1. Clone o repositório ou abra no IntelliJ IDEA.
2. Certifique-se de ter o JDK 17+ instalado.
3. Execute a classe `Lista07HttpApplication`.
4. A API estará disponível em `http://localhost:8080`.

---

## 🛠️ Endpoints da API

### 1. Time de Futebol (Exercício 1)
* **GET** `/time/principal`: Lista os 11 jogadores titulares.
* **GET** `/time/reservas`: Lista os jogadores reservas.
* **PUT** `/time/jogador/{posicao}`: Substitui um titular por um novo jogador enviado no corpo da requisição.

### 2. Biblioteca (Exercício 2)
* **GET** `/biblioteca/livros`: Lista livros disponíveis.
* **GET** `/biblioteca/emprestados`: Lista empréstimos ativos.
* **POST** `/biblioteca/emprestados`: Realiza um empréstimo (Requer `livroId` e `usuarioId`).
* **DELETE** `/biblioteca/emprestados/{id}`: Devolve um livro.

### 3. Rede Social (Exercício 3)
* **GET** `/usuarios`: Lista usuários cadastrados.
* **POST** `/usuarios/{id}/tweets`: Cria um novo tweet para o usuário.
* **PATCH** `/usuarios/{id}/tweets/{tweetId}`: Edita a mensagem de um tweet (marca como editado).
* **DELETE** `/usuarios/{id}/tweets/{tweetId}`: Remove um tweet.

### 4. Cinema (Exercício 4)
* **GET** `/filmes`: Lista todos os filmes.
* **POST** `/filmes`: Cadastra um novo filme.
* **POST** `/filmes/{id}/ingressos`: Compra um ingresso (Valida capacidade).
* **DELETE** `/filmes/{id}/ingressos/{ingressoId}`: Devolve o ingresso e libera o assento.

---

## 📝 Observações
* Os dados são armazenados em **memória**. Ao reiniciar a aplicação, as listas retornam ao estado inicial.
* IDs são gerados automaticamente via `UUID`.