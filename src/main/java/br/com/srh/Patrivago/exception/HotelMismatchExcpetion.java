package br.com.srh.Patrivago.exception;

import br.com.srh.Patrivago.constante.ErrorMessage;

public class HotelMismatchExcpetion extends RuntimeException {
     public HotelMismatchExcpetion()
    {
        super(ErrorMessage.HOTEL_INCORRETO);
    }
     public HotelMismatchExcpetion(String s)
    {
        super(s);
    }
}
