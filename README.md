# Patrivago

# Descrição
O Patrivago é uma aplicação desenvolvida para gerenciar reservas de hotéis. A aplicação fornece funcionalidades para criação, atualização e consulta de contas de clientes, empresas, reservas e hotéis.

Estrutura do Projeto
A estrutura do projeto é a seguinte:
Patrivago/

├── .git/                    # Diretório de controle de versão
├── .idea/                   # Arquivos de configuração do IntelliJ IDEA
├── .mvn/                    # Arquivos de configuração do Maven
├── src/                     # Código fonte da aplicação
│   ├── main/
│   │   ├── java/
│   │   │   └── br/
│   │   │       └── com/
│   │   │           └── srh/
│   │   │               └── Patrivago/
│   │   │                   ├── constante/
│   │   │                   ├── controller/
│   │   │                   ├── dao/
│   │   │                   ├── model/
│   │   │                   ├── usecase/
│   │   │                   └── util/
│   │   └── resources/       # Arquivos de configuração e recursos da aplicação
│   └── test/                # Testes unitários e de integração
├── target/                  # Arquivos gerados durante o build
├── .gitignore               # Arquivo de configuração do Git para ignorar arquivos/diretórios específicos
└── pom.xml                  # Arquivo de configuração do Maven

# Funcionalidades
Conta: Gerenciamento de contas de clientes e empresas.
Hotel: Gerenciamento de informações de hotéis.
Reserva: Gerenciamento de reservas em hotéis.
Utilidades: Serviços auxiliares como validação de CPF, CNPJ, email, etc.

# Pré-requisitos
Java 11 ou superior
Maven 3.6.3 ou superior

# Como executar
1. Clone o repositório:
   git clone https://github.com/seu-usuario/Patrivago.git
2. Navegue até o diretório do projeto
   cd Patrivago
3. Compile o projeto utilizando o Maven:
   mvn clean install
4. Execute a aplicação
   mvn spring-boot:run
  # Contribuição
1.Faça um fork do repositório
2. Crie uma branch para sua feature (git checkout -b feature/AmazingFeature)
3. Faça commit das suas alterações (git commit -m 'Add some AmazingFeature')
4. Faça push para a branch (git push origin feature/AmazingFeature)
5. Abra um Pull Request
# Licença
Distribuído sob a licença MIT. Veja LICENSE para mais informações.
   
