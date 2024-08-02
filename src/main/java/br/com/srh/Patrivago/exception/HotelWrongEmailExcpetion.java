package br.com.srh.Patrivago.exception;

import br.com.srh.Patrivago.constante.ErrorMessage;

public class HotelWrongEmailExcpetion extends RuntimeException {
     public HotelWrongEmailExcpetion()
    {
        super(ErrorMessage.HOTEL_EMAIL_INCORRETO);
    }
     public HotelWrongEmailExcpetion(String s)
    {
        super(s);
    }
}
