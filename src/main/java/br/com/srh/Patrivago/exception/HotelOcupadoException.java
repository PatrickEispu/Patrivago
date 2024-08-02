package br.com.srh.Patrivago.exception;

import br.com.srh.Patrivago.constante.ErrorMessage;

public class HotelOcupadoException extends RuntimeException {
     public HotelOcupadoException()
    {
        super(ErrorMessage.HOTEL_OCUPADO);
    }
     public HotelOcupadoException(String s)
    {
        super(s);
    }
}
