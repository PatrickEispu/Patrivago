package br.com.srh.Patrivago.exception;

import br.com.srh.Patrivago.constante.ErrorMessage;

public class CheckoutAtivadoException extends RuntimeException {
     public CheckoutAtivadoException()
    {
        super(ErrorMessage.CHECKOUT_JA_ATIVADO);
    }
     public CheckoutAtivadoException(String s)
    {
        super(s);
    }
}
