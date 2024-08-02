package br.com.srh.Patrivago.exception;

import br.com.srh.Patrivago.constante.ErrorMessage;

public class SenhaException extends RuntimeException {
    public SenhaException(String s) {
        super(s);
    }
    public SenhaException()
    {
        super(ErrorMessage.SENHA_INVALIDA);
    }
}
