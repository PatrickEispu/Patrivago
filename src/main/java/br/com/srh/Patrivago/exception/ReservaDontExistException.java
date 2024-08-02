package br.com.srh.Patrivago.exception;

import br.com.srh.Patrivago.constante.ErrorMessage;

public class ReservaDontExistException extends RuntimeException {
     public ReservaDontExistException()
    {
        super(ErrorMessage.RESERVA_INEXISTENTE);
    }
     public ReservaDontExistException(String s)
    {
        super(s);
    }
}
