package br.com.srh.Patrivago.exception;

import br.com.srh.Patrivago.constante.ErrorMessage;

public class CheckoutException extends RuntimeException {
     public CheckoutException()
    {
        super(ErrorMessage.CHECKOUT_INVALIDO);
    }
     public CheckoutException(String s)
    {
        super(s);
    }
}
