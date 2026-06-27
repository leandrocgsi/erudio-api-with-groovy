## Erudio API with SpringSecurity, JPA, Groovy and many other technologies

[![Build Status](https://travis-ci.org/leandrocgsi/erudio-api-with-groovy.svg?branch=master)](https://travis-ci.org/leandrocgsi/erudio-api-with-groovy)
[![Build Status](https://circleci.com/gh/leandrocgsi/erudio-api-with-groovy.svg?&style=shield)](https://circleci.com/gh/leandrocgsi/erudio-api-with-groovy/)

Construindo uma API do zero juntando tutoriais e tecnologias soltas? Esse caminho costuma custar caro:

- **Horas perdidas** tentando integrar Spring Security, JPA e Groovy sem uma base sólida
- **Erros difíceis de depurar** por falta de contexto e fundamentos bem estabelecidos
- **Sem clareza** sobre boas práticas, arquitetura e como levar tudo isso para produção

Conheça a [Formação Spring Boot 2026: do Zero ao Deploy na AWS e GCP com Java, Docker e Kubernetes](https://pub.erudio.com.br/formacao-spring-boot-2026-do-zero-ao-continuous-deployment-na-aws-e-gcp-com-java-docker-e-kubernetes?utm_source=github&utm_medium=organic&utm_campaign=readme&utm_content=repo) e construa APIs profissionais do zero ao deploy na nuvem, com estrutura, didática e foco no mercado.

[![Image](https://raw.githubusercontent.com/leandrocgsi/blog-images/refs/heads/main/formacoes_github/07-rest-java.png "Formação Spring Boot 2026: do Zero ao Deploy na AWS e GCP com Java, Docker e Kubernetes")](https://pub.erudio.com.br/formacao-spring-boot-2026-do-zero-ao-continuous-deployment-na-aws-e-gcp-com-java-docker-e-kubernetes?utm_source=github&utm_medium=organic&utm_campaign=readme&utm_content=repo)

*This project has been implemented based in* --> [Spring MVC 4 Quickstart Maven Archetype](https://github.com/kolorobot/spring-mvc-quickstart-archetype)

## Summary

The project is a Maven archetype for Spring MVC 4 web application.

## Generated project characteristics

* No-xml Spring MVC 4 web application (except named queries)
* JPA (Hibernate/HSQLDB/Spring Data JPA)
* MongoDB (Spring Data Mongo)
* JUnit/Mockito
* Spring Security
* REST
* Swagger
* Groovy

## How to get this project

You can execute following command or as you prefer [download zip here](https://github.com/leandrocgsi/erudio-api-with-groovy/archive/master.zip) and import in your favourite IDE.

```sh
git clone https://github.com/leandrocgsi/erudio-api-with-groovy.git
```

## Run the project

```bash
mvn test tomcat7:run
```

## Test on the browser

```
http://localhost:8080/api/v1/{{endPointName}}
```

Note: No additional services are required in order to start the application. Mongo DB configuration is in place but it is not used in the code.

## Switching to PostgreSQL

Add dependency to PostgreSQL driver in POM:

```xml
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <version>9.4.1207</version>
</dependency>
```

Change `persistence.properties`:

```
dataSource.driverClassName=org.postgresql.Driver
dataSource.url=jdbc:postgresql:postgres
dataSource.username=postgres
dataSource.password=postgres
hibernate.dialect=org.hibernate.dialect.PostgreSQL9Dialect
hibernate.hbm2ddl.auto=create
```

# Cursos Relacionados

[![Image](https://raw.githubusercontent.com/leandrocgsi/blog-images/refs/heads/main/formacoes_github/01-hm-claude-code-java.png?raw=true "Formação Claude Code para DEVs Java e Spring Boot: Produtividade 10x, Agentes de IA, MCPs e Aplicações do Mundo Real")](https://pub.erudio.com.br/formacao-dev10x-claude-code-para-devs-java-e-spring-boot?utm_source=github&utm_medium=organic&utm_campaign=readme&utm_content=profile)
[![Image](https://raw.githubusercontent.com/leandrocgsi/blog-images/refs/heads/main/formacoes_github/31-spring-ai-java.png "Formação Spring AI 2026: Inteligência Artificial com Java, Spring Boot, ChatGPT, DeepSeek, Claude e MCP")](https://pub.erudio.com.br/formacao-spring-ai-2026-inteligencia-artificial-com-java-spring-boot-chatgpt-deepseek-claude-e-mcp?utm_source=github&utm_medium=organic&utm_campaign=readme&utm_content=repo)
[![Image](https://raw.githubusercontent.com/leandrocgsi/blog-images/refs/heads/main/formacoes_github/14-microservices-java.png "Formação Microsserviços 2026: do Zero ao Deploy na Google Cloud com Spring Boot, Kubernetes e Docker")](https://pub.erudio.com.br/formacao-microsservicos-2026-do-zero-ao-deploy-na-google-cloud-com-spring-boot-kubernetes-e-docker?utm_source=github&utm_medium=organic&utm_campaign=readme&utm_content=repo)
[![Image](https://raw.githubusercontent.com/leandrocgsi/blog-images/refs/heads/main/formacoes_github/10-docker-to-aws.png "Formação Docker e Kubernetes 2026: do Zero ao Deploy Profissional na AWS, Azure e GCP com GitHub Actions")](https://pub.erudio.com.br/docker-e-kubernetes-2026-do-zero-ao-deploy-profissional-na-aws-azure-e-gcp-com-github-actions?utm_source=github&utm_medium=organic&utm_campaign=readme&utm_content=repo)
[![Image](https://raw.githubusercontent.com/leandrocgsi/blog-images/refs/heads/main/formacoes_github/09-docker.png "Formação Docker e Kubernetes 2026: do Zero à Inteligência Artificial com MCP Catalog e Docker Model Runner")](https://pub.erudio.com.br/meus-cursos?utm_source=github&utm_medium=organic&utm_campaign=readme&utm_content=repo)
[![Image](https://raw.githubusercontent.com/leandrocgsi/blog-images/refs/heads/main/formacoes_github/24-tests-java.png "Formação Java Testing 2026: Testes Automatizados com JUnit, Mockito, Spring Boot, TDD e TestContainers")](https://pub.erudio.com.br/formacao-java-testing-2026-testes-automatizados-com-junit-mockito-spring-boot-tdd-e-testcontainers?utm_source=github&utm_medium=organic&utm_campaign=readme&utm_content=repo)
[![Image](https://raw.githubusercontent.com/leandrocgsi/blog-images/refs/heads/main/formacoes_github/29-cdpl-java-gcp.png "Formação Continuous Deployment: do Zero ao Deploy com Java, Spring Boot, Docker, Kubernetes, Google Cloud (GCP) e GitHub Actions")](https://pub.erudio.com.br/formacao-continuous-deployment-java-kubernetes-gcp-github-actions?utm_source=github&utm_medium=organic&utm_campaign=readme&utm_content=repo)
[![Image](https://raw.githubusercontent.com/leandrocgsi/blog-images/refs/heads/main/formacoes_github/27-cicd-java-aws.png "Formação Continuous Integration e Delivery: do Zero ao Deploy com Java, Spring Boot, Docker, Amazon AWS e GitHub Actions")](https://pub.erudio.com.br/formacao-continuous-integration-delivery-java-aws-github-actions?utm_source=github&utm_medium=organic&utm_campaign=readme&utm_content=repo)
[![Image](https://raw.githubusercontent.com/leandrocgsi/blog-images/refs/heads/main/formacoes_github/28-cicd-java-azure.png "Formação Continuous Integration e Delivery: do Zero ao Deploy com Java, Spring Boot, Docker, Microsoft Azure e GitHub Actions")](https://pub.erudio.com.br/formacao-continuous-integration-delivery-java-azure-github-actions?utm_source=github&utm_medium=organic&utm_campaign=readme&utm_content=repo)
[![Image](https://raw.githubusercontent.com/leandrocgsi/blog-images/refs/heads/main/formacoes_github/13-career.png "Carreira em TI do Zero ao Exterior: Currículo, Entrevistas, Negociação e Crescimento Profissional")](https://pub.erudio.com.br/carreira-em-ti-do-zero-ao-exterior-curriculo-entrevistas-negociacao-e-crescimento-profissional?utm_source=github&utm_medium=organic&utm_campaign=readme&utm_content=repo)

# Outros Cursos Erudio

[![Image](https://raw.githubusercontent.com/leandrocgsi/blog-images/refs/heads/main/formacoes_github/01-rest-asp-net.png "Formação ASP.NET 2026: do Zero ao Deploy na Azure e GCP com .NET 10, Docker e Kubernetes")](https://pub.erudio.com.br/asp-net-2026-do-0-a-azure-e-gcp-com-asp-net-10-docker-e-kubernetes?utm_source=github&utm_medium=organic&utm_campaign=readme&utm_content=repo)
[![Image](https://raw.githubusercontent.com/leandrocgsi/blog-images/refs/heads/main/formacoes_github/33-dotnet-ai.png "Inteligência Artificial com .NET AI e .NET 10: Aplicações Inteligentes com ChatGPT, OpenAI, DeepSeek e Ollama")](https://pub.erudio.com.br/inteligencia-artificial-com-dotnet-ai-e-dotnet-10-apps-inteligentes-com-chatgpt-openai-deepseek-e-ollama?utm_source=github&utm_medium=organic&utm_campaign=readme&utm_content=repo)
[![Image](https://raw.githubusercontent.com/leandrocgsi/blog-images/refs/heads/main/formacoes_github/15-microservices-asp-net.png "Formação Microsserviços 2026: do 0 ao Deploy na Google Cloud com ASP.NET, .NET 10, Kubernetes e Docker")](https://pub.erudio.com.br/meus-cursos?utm_source=github&utm_medium=organic&utm_campaign=readme&utm_content=repo)
[![Image](https://raw.githubusercontent.com/leandrocgsi/blog-images/refs/heads/main/formacoes_github/32-code-ai-kspring.png "Formação Spring AI 2026: Inteligência Artificial com Kotlin, Spring Boot, ChatGPT, DeepSeek, Claude e MCP")](https://pub.erudio.com.br/formacao-spring-ai-2026-inteligencia-artificial-com-kotlin-spring-boot-chatgpt-deepseek-claude-e-mcp?utm_source=github&utm_medium=organic&utm_campaign=readme&utm_content=repo)
[![Image](https://raw.githubusercontent.com/leandrocgsi/blog-images/refs/heads/main/formacoes_github/22-ms-kotlin.png "Formação Microsserviços com Spring Cloud, Spring Boot, Kotlin, Docker e Zookeeper")](https://pub.erudio.com.br/formacao-microsservicos-com-spring-cloud-spring-boot-kotlin-docker-e-zookeeper?utm_source=github&utm_medium=organic&utm_campaign=readme&utm_content=repo)
[![Image](https://raw.githubusercontent.com/leandrocgsi/blog-images/refs/heads/main/formacoes_github/18-rest-spring-kotlin.png "Formação Spring Boot com Kotlin: REST APIs Profissionais do Zero ao Deploy na AWS com Docker e Kubernetes")](https://pub.erudio.com.br/formacao-spring-boot-com-kotlin-rest-apis-profissionais-do-zero-ao-deploy-na-aws-com-docker-e-kubernetes?utm_source=github&utm_medium=organic&utm_campaign=readme&utm_content=repo)
[![Image](https://raw.githubusercontent.com/leandrocgsi/blog-images/refs/heads/main/formacoes_github/20-kotlin.png "Kotlin para DEV's Java: Domine Lambdas, Generics, Reflections, Programação Funcional e Spring Boot")](https://pub.erudio.com.br/kotlin-para-desenvolvedores-java-domine-sintaxe-lambdas-generics-reflections-programacao-funcional-e-spring-boot?utm_source=github&utm_medium=organic&utm_campaign=readme&utm_content=repo)
[![Image](https://raw.githubusercontent.com/leandrocgsi/blog-images/refs/heads/main/formacoes_github/30-jasper.png "Crie Relatórios Profissionais com JasperReports, Java, Spring Boot e JasperSoft Studio")](https://pub.erudio.com.br/crie-relatorios-profissionais-com-jasperreports-java-spring-boot-e-jaspersoft-studio?utm_source=github&utm_medium=organic&utm_campaign=readme&utm_content=repo)

## [Como Configurar Ambiente de DEV Spring Boot no Windows: Java, Maven, IntelliJ e MySQL Guia Completo](https://www.youtube.com/watch?v=M-0HxNoUeNE)

[![Como Configurar Ambiente de DEV Spring Boot no Windows: Java, Maven, IntelliJ e MySQL Guia Completo](https://img.youtube.com/vi/M-0HxNoUeNE/maxresdefault.jpg)](https://www.youtube.com/watch?v=M-0HxNoUeNE)

## [Como Configurar Ambiente de DEV Spring Boot no Linux: Java, Maven, IntelliJ e MySQL Guia Completo](https://www.youtube.com/watch?v=Wk5645fHrVU)

[![Como Configurar Ambiente de DEV Spring Boot no Linux: Java, Maven, IntelliJ e MySQL Guia Completo](https://img.youtube.com/vi/Wk5645fHrVU/maxresdefault.jpg)](https://www.youtube.com/watch?v=Wk5645fHrVU)
