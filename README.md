
# Geros - Gerenciador de OS

Api desenvolvida para um sistema de gerenciamento de Ordens de Serviço especificamente para manutenção e prestação de serviços


## Autores

- [@silvan-batistella](https://www.github.com/silvan-batistella)


## Tecnologias que serão utilizadas no Projeto

[MySQL](https://www.mysql.com/) |  [FlyWay](https://flywaydb.org/)

## Variáveis configuráveis

O projeto conta com uma variedade de variáveis personalizáveis, mas que se não for informado assumirá um valor default.

### SERVER_PORT_EXPOSE (DEFAULT: 8080)
Variável para alterar a porta padrão de exposição da API.

### DATABASE_HOST (DEFAULT: localhost)
Variável para alterar a url de conexão com banco de dados.

### DATABASE_PORT (DEFAULT: 3306)
Variável para alterar a porta de conexão com banco de dados.

### DATABASE (DEFAULT: geros)
Variável para alterar o banco de dados.

### DATABASE_CREATE_IF_NOT_EXISTS (DEFAULT: true)
Variável para determinar se a criação do banco, caso não exista, cria.

### DATABASE_USER (DEFAULT: root)
Variável para determinar o usuário que irá fazer a conexão com banco de dados.

### DATABASE_PASSWD (DEFAULT: root)
Variável para determinar a senha do usuário que irá fazer a conexão com banco de dados.

### DATABASE_DDL_AUTO (DEFAULT: none)
Variável para determinar o esquema de atualização do banco de dados.

### SHOW_SQL (DEFAULT: false)
Variável para determinar se exibe os SQL's executados no banco de dados.

### FORMAT_SQL (DEFAULT: true)
AINDA NÂO SEI O QUE FAZ

### JBDC_BATCH_SIZE (DEFAULT: 50)
AINDA NÂO SEI O QUE FAZ

### JWT_SECRET (DEFAULT: 123)
Determina qual será a chave para fazer a criptografia de hash para as senhas armazenadas no banco de dados

### JWT_EXPIRATION(DEFAULT: 21600000)
Duração da seção / token.
