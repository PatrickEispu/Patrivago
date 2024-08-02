package br.com.srh.Patrivago.exception;

import br.com.srh.Patrivago.constante.ErrorMessage;

public class CheckinAtivadoException extends RuntimeException {
     public CheckinAtivadoException()
    {
        super(ErrorMessage.CHECKIN_JA_ATIVADO);
    }
     public CheckinAtivadoException(String s)
    {
        super(s);
    }
}
