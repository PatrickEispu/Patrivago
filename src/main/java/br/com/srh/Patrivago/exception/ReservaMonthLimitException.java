package br.com.srh.Patrivago.exception;

import br.com.srh.Patrivago.constante.ErrorMessage;

public class ReservaMonthLimitException extends RuntimeException {

     public ReservaMonthLimitException()
    {
        super(ErrorMessage.RESERVA_MESES_LIMITE);
    }
     public ReservaMonthLimitException(String s)
    {
        super(s);
    }
}
