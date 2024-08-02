package br.com.srh.Patrivago.exception;

import br.com.srh.Patrivago.constante.ErrorMessage;

public class CpfException extends RuntimeException {
    public CpfException(String s) {
        super(s);
    }

    public CpfException()
    {
        super(ErrorMessage.CPF_INVALIDO);
    }
}
