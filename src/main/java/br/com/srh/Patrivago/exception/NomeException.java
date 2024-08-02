package br.com.srh.Patrivago.exception;


import br.com.srh.Patrivago.constante.ErrorMessage;

public class NomeException extends RuntimeException {

    public NomeException() {
        super(ErrorMessage.NOME_INVALIDO);
    }

    public NomeException(String s) {
        super(s);
    }
}
