package br.com.srh.Patrivago.exception;

import br.com.srh.Patrivago.constante.ErrorMessage;

public class CheckinException extends RuntimeException {
     public CheckinException()
    {
        super(ErrorMessage.CHECKIN_INVALIDO);
    }
     public CheckinException(String s)
    {
        super(s);
    }
}
