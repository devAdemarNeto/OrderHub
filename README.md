# OrderHub - Sistema de Gestão de Clientes e Pedidos

O **OrderHub** é um sistema web desenvolvido para otimizar o gerenciamento de clientes e pedidos. Construído com **Spring Boot**, **Thymeleaf** e **Bootstrap 5**, o projeto segue princípios de arquitetura limpa, boas práticas de desenvolvimento (SOLID, MVC) e foca na melhor experiência para o usuário final.

## 📋 Sobre o Projeto:

Este projeto consiste em uma aplicação web corporativa full-stack, projetada para ser escalável e de fácil manutenção. Ele centraliza o cadastro de informações vitais do negócio, permitindo controle total sobre a base de clientes e o fluxo de pedidos.

### Principais Objetivos
- **Gestão Eficiente**: Centralizar dados de clientes e pedidos em uma interface intuitiva.
- **Produtividade**: Fluxos de cadastro rápidos com validações automáticas.
- **Confiabilidade**: Garantia de integridade de dados (CPF único, valores monetários corretos).
- **Modernidade**: Interface responsiva com tema Dark Mode, amigável para uso prolongado.

---

## 🛠️ Tecnologias Utilizadas

### Backend
- **Java 17** - Linguagem de programação moderna e performática.
- **Spring Boot 3+** - Framework líder de mercado para desenvolvimento ágil.
- **Spring Data JPA** - Camada de persistência simplificada e poderosa.
- **H2 Database** - Banco de dados em memória (configurável para bancos externos).
- **Flyway** - Controle de versão do esquema do banco de dados.
- **Bean Validation** - Validação de regras de negócio declarativa.
- **Swagger / OpenAPI** - Documentação viva e interativa da API.

### Frontend
- **Thymeleaf** - Renderização de páginas no servidor (Server-Side Rendering).
- **Bootstrap 5** - Framework de UI para design responsivo e componentes estéticos.
- **Bootstrap Icons** - Biblioteca de ícones vetoriais integrados.

### Ferramentas e Produtividade
- **Maven** - Gerenciamento robusto de dependências e ciclo de vida de build.
- **Lombok** - Redução de código repetitivo, focando no que importa.

---

## 📦 Funcionalidades

### 👥 Módulo de Clientes
*   **Cadastro Completo**: Registro de Nome, CPF e E-mail.
*   **Inteligência de Dados**:
    *   Validação avançada de CPF (formato e dígitos verificadores).
    *   Verificação automática de duplicidade no banco.
    *   Sanitização de dados (remoção de formatação) antes da persistência.
*   **CRUD Total**: Listagem, Edição e Remoção segura de clientes.

### 📦 Módulo de Pedidos
*   **Integração**: Vínculo direto com a base de Clientes através de seleção dinâmica.
*   **Controle Financeiro**: Campos monetários com precisão decimal correta (`BigDecimal`).
*   **Experiência de Uso**:
    *   Dropdown com busca de clientes.
    *   Prevenção de erros com feedbacks visuais claros.
    *   Tratamento de exceções amigável (evita telas de erro técnico).

### 📊 Dashboard
*   Visão geral e acesso rápido às principais funcionalidades do sistema.

---

## 🚀 Como Executar o Projeto

Simples e direto, sem necessidade de instalações complexas.

### Pré-requisitos
*   **Java JDK 17** instalado.

### Passo a Passo

1.  Clone este repositório.
2.  Abra o terminal na pasta do projeto.
3.  Execute o comando:

**No Windows:**
```powershell
.\mvnw.cmd spring-boot:run
```

**No Linux/Mac:**
```bash
./mvnw spring-boot:run
```

Acesse a aplicação em: **http://localhost:8080**

---

## 🔗 Links Úteis

| Acesso | URL |
|--------|-----|
| **Aplicação (Home)** | `http://localhost:8080/` |
| **Lista de Clientes** | `http://localhost:8080/clientes-view` |
| **Lista de Pedidos** | `http://localhost:8080/pedidos-view` |
| **Documentação API** | `http://localhost:8080/swagger-ui/index.html` |
| **Banco de Dados** | `http://localhost:8080/h2-console` |

> **Nota para o H2 Console**:
> - **Driver Class**: `org.h2.Driver`
> - **JDBC URL**: `jdbc:h2:mem:orderhub`
> - **User Name**: `sa`
> - **Password**: (deixe em branco)

---

## 📁 Arquitetura do Sistema

O sistema segue uma arquitetura em camadas bem definida, garantindo desacoplamento e facilidade de teste:

```
src/main/java/dev/ademarneto/OrderHub/
├── controller/          # Pontos de entrada (Web e API)
│   ├── web/             # Controllers MVC (retornam Views HTML)
│   └── api/             # Controllers REST (retornam JSON)
├── service/             # Regras de Negócio e Validações
├── repository/          # Interface com o Banco de Dados
├── model/               # Entidades de Domínio (JPA)
├── dto/                 # Objetos de Transferência de Dados (Segurança/Desacoplamento)
└── mapper/              # Conversão entre Modelos e DTOs
```

---

## 👤 Autor

Desenvolvido por **Ademar Neto**.

Projeto criado com foco em qualidade de código, performance e melhores práticas de Engenharia de Software.
