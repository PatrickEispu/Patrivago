package br.com.srh.Patrivago.exception;

import br.com.srh.Patrivago.constante.ErrorMessage;

public class DataInvalidaException extends RuntimeException {
      public DataInvalidaException()
    {
        super(ErrorMessage.DATA_INVALIDA);
    }
     public DataInvalidaException(String s)
    {
        super(s);
    }
}
