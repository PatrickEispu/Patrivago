package br.com.srh.Patrivago.exception;

import br.com.srh.Patrivago.constante.ErrorMessage;

public class EmailDontExist extends RuntimeException {
     public EmailDontExist()
    {
        super(ErrorMessage.EMAIL_INEXISTENTE);
    }
     public EmailDontExist(String s)
    {
        super(s);
    }
}
