package dev.batistella.geros.constantes;

public abstract class Constantes {

    // DADOS GENERICOS PARA NOMES DE COLUNAS NO BANCO
    public static final String ID = "ID";
    public static final String NOME = "NOME";
    public static final String ESTADO = "ESTADO";
    public static final String DATA_CRIACAO = "DATA_CRIACAO";
    public static final String DATA_ATUALIZACAO = "DATA_ATUALIZACAO";

    // FOREIGN KEYS
    public static final String EMPRESA_FK = "EMPRESA_ID";
    public static final String CLIENTE_FK = "CLIENTE_ID";
    public static final String USUARIO_FK = "USUARIO_ID";


    // ENDPOINTS
    public static final String ENDPOINT_CLIENTE = "/cliente";
    public static final String ENDPOINT_ENDERECO = "/endereco";
    public static final String ENDPOINT_REGISTRO = "/registro";
    public static final String ENDPOINT_AUTENTICACAO = "/autentica";
    public static final String ENDPOINT_EQUIPAMENTO = "/equipamento";
    public static final String ENDPOINT_ORDEM_SERVICO = "/ordem-servico";

    // ENTIDADES
    public static final String TABELA_EMPRESA = "EMPRESAS";
    public static final String EMPRESA_CPF_CNPJ = "CPF_CNPJ";

    public static final String TABELA_PERMISSAO = "PERMISSOES";

    public static final String TABELA_USUARIO = "USUARIOS";
    public static final String USUARIO_CPF_CNPJ = "CPF_CNPJ";

    public static final String TABELA_LOGIN = "LOGIN";
    public static final String LOGIN_EMAIL = "EMAIL";
    public static final String LOGIN_SENHA = "SENHA";

    public static final String TABELA_CLIENTE = "CLIENTES";
    public static final String CLIENTE_EMAIL = "EMAIL";
    public static final String CLIENTE_TELEFONE = "TELEFONE";
    public static final String CLIENTE_CPF_CNPJ = "CPF_CNPJ";

    public static final String TABELA_ENDERECO = "ENDERECOS";
    public static final String ENDERECO_CEP = "CEP";
    public static final String ENDERECO_RUA = "RUA";
    public static final String ENDERECO_CIDADE = "CIDADE";
    public static final String ENDERECO_NUMERO = "NUMERO";
    public static final String ENDERECO_COMPLEMENTO = "COMPLEMENTO";
    public static final String ENDERECO_INFOS_COMPLEMENTARES = "INFOS_COMPLEMENTARES";

    public static final String TABELA_ORDEM_SERVICO = "ORDEM_SERVICO";
    public static final String ORDEM_SERVICO_ESTADO = "ESTADO";
    public static final String ORDEM_SERVICO_VALOR_ORCADO = "VALOR_ORCADO";
    public static final String ORDEM_SERVICO_VALOR_QUITADO = "VALOR_QUITADO";
    public static final String ORDEM_SERVICO_VALOR_CONTRATADO = "VALOR_CONTRATADO";

    public static final String TABELA_EQUIPAMENTO = "EQUIPAMENTO";

    public static final String EQUIPAMENTO_OBS = "OBS";
    public static final String EQUIPAMENTO_MARCA = "MARCA";
    public static final String EQUIPAMENTO_MODELO = "MODELO";
    public static final String EQUIPAMENTO_NUMERO_SERIE = "NUMERO_SERIE";

}
