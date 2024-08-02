package br.com.srh.Patrivago.exception;

import br.com.srh.Patrivago.constante.ErrorMessage;

public class IncorretDateFormatException extends RuntimeException {
     public IncorretDateFormatException()
    {
        super(ErrorMessage.DATA_FORMATO_INCORRETO);
    }
     public IncorretDateFormatException(String s)
    {
        super(s);
    }
}
