package br.com.srh.Patrivago.exception;

import br.com.srh.Patrivago.constante.ErrorMessage;

public class CnpjException extends RuntimeException {
    public CnpjException()
    {super(ErrorMessage.CNPJ_INVALIDO);}
    public CnpjException(String s)
    {super(s);}
}
