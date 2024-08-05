package br.com.srh.Patrivago.exception;

import br.com.srh.Patrivago.constante.ErrorMessage;

public class ReservaFinalizadaException extends RuntimeException {
     public ReservaFinalizadaException()
    {
        super(ErrorMessage.RESERVA_FINALIZADA);
    }
     public ReservaFinalizadaException(String s)
    {
        super(s);
    }
}
