package br.com.srh.Patrivago.exception;

import br.com.srh.Patrivago.constante.ErrorMessage;

public class HotelCantUpdateExcpetion extends RuntimeException {
     public HotelCantUpdateExcpetion()
    {
        super(ErrorMessage.HOTEL_UPDATE_NEGADO);
    }
     public HotelCantUpdateExcpetion(String s)
    {
        super(s);
    }
}
