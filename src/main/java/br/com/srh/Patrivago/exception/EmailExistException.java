package br.com.srh.Patrivago.exception;

import br.com.srh.Patrivago.constante.ErrorMessage;

public class EmailExistException extends RuntimeException {
     public EmailExistException(String s)
    {
        super(s);
    }
     public EmailExistException()
    {

        super(ErrorMessage.EMAIL_EXISTENTE);
    }
}
