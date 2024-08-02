package br.com.srh.Patrivago.exception;

import br.com.srh.Patrivago.constante.ErrorMessage;

public class CnpjDontExistException extends RuntimeException {
   public CnpjDontExistException()
   {
       super(ErrorMessage.CNPJ_INEXISTENTE);
   }
}
