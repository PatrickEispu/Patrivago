package br.com.srh.Patrivago.exception;

import br.com.srh.Patrivago.constante.ErrorMessage;

public class CpfDontExistException extends RuntimeException {
   public CpfDontExistException()
    {super(ErrorMessage.CPF_INEXISTENTE);}
    public CpfDontExistException(String s)
    {super(s);}
}
