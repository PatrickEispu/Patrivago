package br.com.srh.Patrivago.exception;

import br.com.srh.Patrivago.constante.ErrorMessage;

public class HotelDontExistException extends RuntimeException {
     public HotelDontExistException()
    {
        super(ErrorMessage.HOTEL_NAO_ENCONTRADO);
    }
     public HotelDontExistException(String s)
    {
        super(s);
    }

}
