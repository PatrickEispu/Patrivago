package br.com.srh.Patrivago.constante;


public class ErrorMessage {

    public static final String NOME_INVALIDO = "NOME inserido inválido.";
    public static final String CPF_INVALIDO = "CPF inserido inválido.";
    public static final String CPF_EXISTENTE = "CPF informado já cadastrado.";
    public static final String CPF_INEXISTENTE = "CPF informado não está cadastrado.";
    public static final String CNPJ_INVALIDO = "CNPJ inserido inválido.";
    public static final String CNPJ_EXISTENTE = "CNPJ informado já cadastrado.";
    public static final String CNPJ_INEXISTENTE = "CNPJ informado não está cadastrado.";
    public static final String EMAIL_INVALIDO = "EMAIL inserido inválido.";
    public static final String EMAIL_EXISTENTE = "EMAIL inserido já cadastrado.";
    public static final String EMAIL_INEXISTENTE = "EMAIL inserido não existe.";
    public static final String HOTEL_EMAIL_INCORRETO = "EMAIL incorreto para este hotel.";
    public static final String SENHA_INVALIDA = "A senha deve contar pelo menos 6(seis) caracteres.";
    public static final String RESERVA_VAZIA = "Os Campos de CHECKIN e/ou CHECKOUT não foram preenchidos.";
    public static final String RESERVA_INVALIDA = "RESERVA solicitada inválida.";
    public static final String RESERVA_INEXISTENTE = "RESERVA não existe.";
    public static final String HOTEL_OCUPADO = "O hotel está com lotação máxima.";
    public static final String HOTEL_NAO_ENCONTRADO = "Nenhum Hotel foi encontrado.";
    public static final String HOTEL_UPDATE_NEGADO = "Update NEGADO! Não é possível alterar informações dos quartos enquanto houver reservas em andamento.";
    public static final String HOTEL_INCORRETO ="Este hotel não está vinculado a esta conta." ;
    public static final String CHECKIN_INVALIDO = "O check-in não pode ser feito nesta data.";
    public static final String CHECKIN_JA_ATIVADO = "Esta reserva já está com o checkin ativado.";
    public static final String CHECKOUT_INVALIDO = "O check-out não pode ser feito nesta data.";
    public static final String CHECKOUT_JA_ATIVADO = "Esta reserva já está com o checkout ativado.";
    public static final String DATA_INVALIDA = "Data informada não é valida (checkin/checkout não podem ser anteriores a data atual).";
    public static final String DATA_FORMATO_INCORRETO = "DATA com formato incorreto. Seguir padrao(dd/MM/yyyy).";
    public static final String RESERVA_LIMIT = "A reserva excede o limite permitido (É permitido fazer uma reserva de no máximo 20 dias.)";
    public static final String CAMPO_VAZIO = "A requisição possui um campo vazio.";
    public static final String CEP_INVALIDO = "CEP informado é inválido.";
    public static final String CEP_INEXISTENTE = "Nenhum endereço encontrado com o CEP fornecido.";
}




