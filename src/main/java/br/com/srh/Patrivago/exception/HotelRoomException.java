package br.com.srh.Patrivago.exception;

import br.com.srh.Patrivago.constante.ErrorMessage;

public class HotelRoomException extends RuntimeException {
     public HotelRoomException()
    {
        super(ErrorMessage.HOTEL_QUARTO_INVALIDO);
    }
     public HotelRoomException(String s)
    {
        super(s);
    }
}
