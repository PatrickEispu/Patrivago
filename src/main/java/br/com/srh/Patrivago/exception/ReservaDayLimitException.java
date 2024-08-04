package br.com.srh.Patrivago.exception;

import br.com.srh.Patrivago.constante.ErrorMessage;

public class ReservaDayLimitException extends RuntimeException {
     public ReservaDayLimitException()
    {
        super(ErrorMessage.RESERVA_DIAS_LIMITE);
    }
     public ReservaDayLimitException(String s)
    {
        super(s);
    }
}
