package br.com.srh.Patrivago.exception;

import br.com.srh.Patrivago.constante.ErrorMessage;

public class CpfExistException extends RuntimeException {
    public CpfExistException(String s)
    {
        super(s);
    }
    public CpfExistException() {
        super(ErrorMessage.CPF_EXISTENTE);
    }
}
