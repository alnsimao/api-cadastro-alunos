# 📚 Cadastro de Alunos - API REST

Este projeto é uma **API REST** desenvolvida em **Java com Spring Boot**, com foco em aprendizado e evolução contínua.  
O objetivo é praticar conceitos de **CRUD, autenticação, segurança com criptografia, containerização e deploy em nuvem**.  

⚡ **Importante:** Este projeto está em constante atualização conforme vou evoluindo como desenvolvedor.

---

## 🚀 Tecnologias utilizadas
- **Java 17+**
- **Spring Boot**
- **Spring Data JPA / Hibernate**
- **PostgreSQL / MySQL**
- **BCrypt (hash de senha)**
- **Docker**
- **Git & GitHub**
- **Render (Deploy em nuvem)**

---

## ⚙️ Funcionalidades atuais
- ✅ Cadastro de alunos com senha criptografada (`POST /alunos`)  
- ✅ Login com validação de senha (`POST /login`)  
- ✅ Troca de senha com verificação da senha atual (`POST /trocarsenha`)  
- ✅ Listagem de todos os alunos (`GET /alunos`)  
- ✅ Obter aluno por ID (`GET /alunos/{id}`)  
- ✅ Alteração de email (`PATCH /alunos/{id}`)  
- ✅ Atualização de dados (`PUT /alunos/{id}`)  
- ✅ Exclusão de aluno (`DELETE /alunos/{id}`)  

---

## 📦 Como rodar o projeto

### 🔹 Localmente (Maven)
```bash
git clone https://github.com/alnsimao/api-cadastro-alunos.git
cd cadastro-alunos
./mvnw spring-boot:run
