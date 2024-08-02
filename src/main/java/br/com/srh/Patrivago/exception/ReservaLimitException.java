package br.com.srh.Patrivago.exception;

import br.com.srh.Patrivago.constante.ErrorMessage;

public class ReservaLimitException extends RuntimeException {
     public ReservaLimitException()
    {
        super(ErrorMessage.RESERVA_LIMIT);
    }
     public ReservaLimitException(String s)
    {
        super(s);
    }
}
