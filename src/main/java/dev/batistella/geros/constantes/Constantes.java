package dev.batistella.geros.constantes;

public abstract class Constantes {

    // DADOS GENERICOS PARA NOMES DE COLUNAS NO BANCO
    public static final String ID = "ID";
    public static final String NOME = "NOME";

    // ENDPOINTS
    public static final String ENDPOINT_AUTENTICACAO = "/autentica";
    public static final String ENDPOINT_REGISTRO = "/registro";

    // ENTIDADES
    public static final String TABELA_EMPRESA = "EMPRESAS";
    public static final String EMPRESA_CPF_CNPJ = "CPF_CNPJ";

    public static final String TABELA_PERMISSAO = "PERMISSOES";


    public static final String TABELA_USUARIO = "USUARIOS";
    public static final String USUARIO_CPF_CNPJ = "CPF_CNPJ";
    public static final String USUARIO_EMPRESA_FK = "EMPRESA_ID";


    public static final String TABELA_LOGIN = "LOGIN";
    public static final String LOGIN_SENHA = "SENHA";
    public static final String LOGIN_EMAIL = "EMAIL";
    public static final String LOGIN_EMPRESA_FK = "EMPRESA_ID";
    public static final String LOGIN_USUARIO_FK = "USUARIO_ID";

}
