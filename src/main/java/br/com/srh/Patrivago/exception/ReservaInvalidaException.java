package br.com.srh.Patrivago.exception;

import br.com.srh.Patrivago.constante.ErrorMessage;

public class ReservaInvalidaException extends RuntimeException {
     public ReservaInvalidaException(String s)
    {
        super(s);
    }
     public ReservaInvalidaException()
    {
        super(ErrorMessage.RESERVA_INVALIDA);
    }
}
