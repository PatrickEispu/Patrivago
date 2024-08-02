package br.com.srh.Patrivago.exception;

import br.com.srh.Patrivago.constante.ErrorMessage;

public class CnpjExistException extends RuntimeException {
    public CnpjExistException()
    {super(ErrorMessage.CNPJ_EXISTENTE);}
   public CnpjExistException(String s)
    {super(s);}
}
