package br.com.srh.Patrivago.exception;

import br.com.srh.Patrivago.constante.ErrorMessage;

public class CepDontExistException extends RuntimeException {
    public CepDontExistException()
    {
        super(ErrorMessage.CEP_INEXISTENTE);
    }

    public CepDontExistException(String s)
    {
        super(s);
    }

}
