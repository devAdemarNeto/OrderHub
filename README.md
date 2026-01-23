# OrderHub - Sistema de Gestão de Clientes e Pedidos

Sistema web para gestão de clientes e pedidos desenvolvido como parte da **Prova Técnica - Engenheiro de Software**. A aplicação foi desenvolvida utilizando **Spring Boot**, **Thymeleaf** e **Bootstrap 5**, seguindo boas práticas de desenvolvimento (SOLID, MVC) e arquitetura limpa.

## 📋 Sobre o Projeto

Este projeto foi desenvolvido para atender aos requisitos de uma prova técnica que avalia a capacidade de modelar, desenvolver e organizar código limpo, funcional e bem estruturado.

### Objetivo
Desenvolver uma aplicação web performática e responsiva para gestão de clientes e pedidos, onde é possível:
- ✅ **Clientes**: Cadastrar, Listar, Editar e Remover.
- ✅ **Pedidos**: Criar pedidos vinculados a clientes, Listar e Gerenciar.
- ✅ **Dashboard**: Visão geral e acesso rápido.
- ✅ **UX/UI**: Interface moderna (Dark Mode) e feedbacks visuais.

---

## 🛠️ Tecnologias Utilizadas

### Backend
- **Java 17** - Linguagem de programação moderna.
- **Spring Boot 3+** - Framework base para configuração e produtividade.
- **Spring Data JPA** - Abstração para persistência de dados.
- **H2 Database** - Banco de dados em memória para execução rápida e desenvolvimento.
- **Flyway** - Versionamento de banco de dados (Migrations).
- **Bean Validation** - Validação de dados robusta e declarativa.
- **Swagger / OpenAPI** - Documentação automática da API.

### Frontend
- **Thymeleaf** - Template Engine para renderização server-side.
- **Bootstrap 5** - Framework CSS para estilização e responsividade (Tema Dark Mode).
- **Bootstrap Icons** - Ícones vetoriais.

### Ferramentas
- **Maven** - Gerenciamento de dependências e build.
- **Lombok** - Redução de código boilerplate (Getters/Setters/Constructors).

---

## 📦 Como Executar o Projeto

### Pré-requisitos
*   **Java JDK 17** ou superior.
*   **Maven** (opcional, pois o projeto inclui o wrapper `mvnw`).

### Executando com Spring Boot Wrapper (Recomendado)

1.  Clone o projeto ou extraia o arquivo.
2.  Abra o terminal na raiz do projeto.
3.  Execute o comando:

**No Windows:**
```powershell
.\mvnw.cmd spring-boot:run
```

**No Linux/Mac:**
```bash
./mvnw spring-boot:run
```

A aplicação será iniciada e estará disponível em **http://localhost:8080**.

---

## 🔗 URLs Principais

| Recurso | URL | Descrição |
|---------|-----|-----------|
| **Dashboard** | `http://localhost:8080/` | Página inicial com atalhos |
| **Clientes** | `http://localhost:8080/clientes-view` | Gestão de Clientes |
| **Pedidos** | `http://localhost:8080/pedidos-view` | Gestão de Pedidos |
| **Swagger UI** | `http://localhost:8080/swagger-ui/index.html` | Documentação da API |
| **H2 Console** | `http://localhost:8080/h2-console` | Acesso direto ao banco |

> **Nota para H2 Console**:
> - **JDBC URL**: `jdbc:h2:mem:orderhub` (Verificar log de inicialização se diferente)
> - **User**: `sa`
> - **Password**: (vazio)

---

## 📁 Estrutura do Projeto

A arquitetura segue o padrão MVC (Model-View-Controller) com separação clara de responsabilidades:

```
src/main/java/dev/ademarneto/OrderHub/
├── controller/
│   ├── api/             # REST Controllers (Endpoints API)
│   └── web/             # Web Controllers (Thymeleaf Views)
├── service/             # Regras de Negócio
├── repository/          # Interface de Acesso a Dados (JPA)
├── model/               # Entidades JPA (Banco de Dados)
├── dto/                 # Data Transfer Objects (Dados de Entrada/Saída)
├── mapper/              # Conversores Entity <-> DTO
└── validation/          # Validadores Personalizados (Ex: CPF)
```

---

## 📝 Funcionalidades Detalhadas

### 👥 Módulo de Clientes
*   **Cadastro Simplificado**: Nome, CPF (com máscara automática), Email.
*   **Validações Inteligentes**:
    *   CPF válido (algoritmo matemática) e único no banco.
    *   Limpeza automática de formatação (pontos/traços) antes de salvar.
    *   Preenchimento automático da `dataCadastro`.
*   **Feedback**: Alertas de sucesso ou erro (ex: falha ao salvar).

### 📦 Módulo de Pedidos
*   **Vínculo com Cliente**: Seleção via Dropdown dinâmico que busca clientes reais do banco.
*   **Validações**:
    *   Campos obrigatórios.
    *   Valor mínimo R$ 0,01 (tratamento de decimal).
*   **Tratamento de Erros**: Captura de exceções robusta para evitar telas de erro técnico (500), guiando o usuário de volta ao formulário com explicação.

---

## 🔧 Configurações e Decisões Técnicas

1.  **H2 Database (In-Memory)**:
    *   Escolhido para facilitar a avaliação técnica sem necessidade de instalar um SGBD externo (como PostgreSQL/MySQL).
    *   Os dados são recriados a cada reinicialização (ideal para testes).

2.  **Validação de CPF**:
    *   Implementada manualmente (`ValidadorCpf.java`) para demonstrar conhecimento de lógica algorítmica e validação customizada, em vez de depender apenas de bibliotecas prontas.

3.  **DTOs e Mappers**:
    *   Uso estrito de **DTOs** para tráfego de dados entre Controller e View/API, isolando a entidade de persistência (`Model`).
    *   Mappers manuais para controle total da transformação.

4.  **Frontend com Thymeleaf**:
    *   Renderização no servidor (SSR), integrando perfeitamente com o ecossistema Spring Boot.
    *   **Bootstrap 5 Dark Mode**: Escolha estética para modernidade e conforto visual.

---

## 👤 Autor

Desenvolvido por **Ademar Neto** como parte da avaliação técnica.

*   Desenvolvido com ❤️ utilizando **Java** e **Spring Boot**.
