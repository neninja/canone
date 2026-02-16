# Cânone

Gerenciamento Eletronico de Documentos

- [CONTRIBUTING](CONTRIBUTING.md) - Guia para contribuidores
- [HELP](HELP.md) - Guia para desenvolvedores

## Utilização

### Ambiente

- Disponibilize um banco PostgreSQL
- Configure JRE 21
- Baixe o artefato *jar*

### Execução

- Crie as variáveis de ambiente

```shell
DATASOURCE_URL=jdbc:postgresql://localhost:5432/mydatabase
DATASOURCE_URL=sa
DATASOURCE_URL=pwd
```

- Execute o pacote escolhendo o perfil ativo

```shell
java -Dspring.profiles.active=prod -jar target/canone-0.0.1-SNAPSHOT.jar
```

> Caso precise modificar alguma configuração de [application.properties](src/main/resources/application.properties) ou [application-prod.properties](src/main/resources/application-prod.properties), como porta, utilize `-Dserver.port=8081` na execução do pacote ou variável como `-DSERVER_PORT=8081`