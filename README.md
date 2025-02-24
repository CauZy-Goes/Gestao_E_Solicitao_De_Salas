# 📋 Projeto Web - Gestão de Solicitações Para Uso De Espaços Físicos 🏫

Este projeto é um sistema web desenvolvido em **JSF e PrimeFaces** para **gerenciar solicitações de uso de espaços físicos** em uma instituição. Ele se comunica com uma **API REST** desenvolvida em **Spring Boot**, responsável por processar e armazenar as solicitações de reserva. O objetivo é permitir que professores agendem salas para aulas ou eventos e que gestores administrem as solicitações e os espaços disponíveis.

🔗 **API REST**: [Repositório da API](https://github.com/CauZy-Goes/Gestao_Solicitacoes_Espacos_API)

## ✨ Funcionalidades

- 📌 **Autenticação e Login**: Professores e gestores podem acessar o sistema com credenciais seguras.
- 📝 **Cadastro de Usuários**: Possibilidade de criação de contas para professores e gestores.
- 📅 **Solicitação de Espaços**: Professores podem solicitar salas informando data, horário e finalidade.
- ✅ **Gestão de Solicitações**: Gestores podem aprovar ou rejeitar solicitações pendentes.
- 🏷️ **Gerenciamento de Tipos de Sala**: Gestores podem classificar os tipos de salas (exemplo: Laboratório, Auditório, etc.).
- 🏢 **Cadastro e Administração de Salas**: Gestores podem adicionar, editar e remover salas disponíveis.

## 🚀 Tecnologias Utilizadas

- **Java 17** 🖥️
- **JSF (Jakarta Faces 4.0.1 - Mojarra)** 🎭
- **PrimeFaces 14.0.0 (Jakarta)** 🎨
- **CDI (Jakarta Enterprise CDI 4.0.1)** 🔄
- **JAX-RS Client (Jakarta RESTful Web Services 3.1.0)** 🌐
- **Jersey Client 3.1.9** 🔗
- **GlassFish 7.0.21** 🌊 (Servidor de Aplicação)
- **Maven** 📦 (Gerenciador de Dependências)
- **IntelliJ IDEA** 💡 (IDE de Desenvolvimento)

## 📸 Telas do Sistema

### 🔑 Tela de Login
📌 **Descrição**: Página de autenticação para professores e gestores.

![Imagem da Tela de Login](https://github.com/CauZy-Goes/Gestao_E_Solicitao_De_Salas/blob/main/Sistema_Salas_Imgs/Login.png)

---

### 📝 Tela de Cadastro
📌 **Descrição**: Formulário para criar contas de novos professores e gestores.

![Imagem da Tela de Cadastro](https://github.com/CauZy-Goes/Gestao_E_Solicitao_De_Salas/blob/main/Sistema_Salas_Imgs/cadastro.png)

---

### 🎓 Dashboard do Professor
📌 **Descrição**: Área onde professores podem visualizar, criar e acompanhar solicitações.

![Imagem do Dashboard do Professor](https://github.com/CauZy-Goes/Gestao_E_Solicitao_De_Salas/blob/main/Sistema_Salas_Imgs/dashboard_professor.png)

---

### 🏛️ Dashboard do Gestor
📌 **Descrição**: Área onde gestores podem aprovar/rejeitar solicitações e gerenciar salas.

![Imagem do Dashboard do Gestor](https://github.com/CauZy-Goes/Gestao_E_Solicitao_De_Salas/blob/main/Sistema_Salas_Imgs/dashboard-gestor.png)

---

### 🏷️ Tela do CRUD de Tipos de Sala
📌 **Descrição**: Interface para cadastrar, editar e excluir categorias de salas.

![Imagem do CRUD de Tipos de Sala](https://github.com/CauZy-Goes/Gestao_E_Solicitao_De_Salas/blob/main/Sistema_Salas_Imgs/crud_tipoSala.png)

---

### 🏢 Tela do CRUD de Salas
📌 **Descrição**: Interface para cadastrar, editar e excluir salas físicas.

![Imagem do CRUD de Salas](https://github.com/CauZy-Goes/Gestao_E_Solicitao_De_Salas/blob/main/Sistema_Salas_Imgs/crud_espacoFIsico.png)

---

## ⚙️ Como Executar o Projeto

1. **Clone o repositório**:
   ```bash
   git clone https://github.com/usuario/repo-gestao-solicitacoes-web.git
   cd repo-gestao-solicitacoes-web
   ```

2. **Importe o projeto no IntelliJ**

3. **Configure o servidor GlassFish**

4. **Compile e execute o projeto**
   ```bash
   mvn clean package
   ```

5. **Acesse no navegador**
   ```
   http://localhost:8080/Gestao_Solicitacao_De_Salas
   ```

## 🎯 Contribuição

Sinta-se à vontade para contribuir! Basta fazer um **fork** do repositório, criar uma nova **branch** com sua funcionalidade ou correção e abrir um **pull request**. 🚀

## 📝 Licença

Este projeto está sob a licença **MIT**. Veja o arquivo `LICENSE` para mais detalhes.

---

Feito por **Cauã Farias** 🚀

