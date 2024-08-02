package br.com.srh.Patrivago.exception;

import br.com.srh.Patrivago.constante.ErrorMessage;

public class EmailException extends RuntimeException {
    public EmailException(String s) {
        super(s);
    }
    public EmailException()
    {
        super(ErrorMessage.EMAIL_INVALIDO);
    }
}
