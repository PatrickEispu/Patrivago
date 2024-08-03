package br.com.srh.Patrivago.exception;

import br.com.srh.Patrivago.constante.ErrorMessage;

public class CepFormatoException extends RuntimeException {
     public CepFormatoException()
    {
        super(ErrorMessage.CEP_INVALIDO);
    }
     public CepFormatoException(String s)
    {
        super(s);
    }
}
