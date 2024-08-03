package br.com.srh.Patrivago.exceptionHandler;

import br.com.srh.Patrivago.constante.ErrorMessage;
import br.com.srh.Patrivago.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CheckinAtivadoException.class)
    private ResponseEntity<String> checkinAtivadoHandler(CheckinAtivadoException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorMessage.CHECKIN_JA_ATIVADO);
    }
    @ExceptionHandler(CheckinException.class)
    private ResponseEntity<String> checkinHandler(CheckinException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorMessage.CHECKIN_INVALIDO);
    }
    @ExceptionHandler(CheckoutAtivadoException.class)
    private ResponseEntity<String> checkouAtivadoHandler(CheckoutAtivadoException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorMessage.CHECKOUT_JA_ATIVADO);
    }
    @ExceptionHandler(CheckoutException.class)
    private ResponseEntity<String> checkouHandler(CheckoutException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorMessage.CHECKOUT_INVALIDO);
    }
    @ExceptionHandler(CnpjDontExistException.class)
    private ResponseEntity<String> cnpjDontExistHandler(CnpjDontExistException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorMessage.CNPJ_INEXISTENTE);
    }
    @ExceptionHandler(CnpjException.class)
    private ResponseEntity<String> cnpjHandler(CnpjException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorMessage.CNPJ_INVALIDO);
    }
    @ExceptionHandler(CnpjExistException.class)
    private ResponseEntity<String> cnpjExistHandler(CnpjExistException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorMessage.CNPJ_EXISTENTE);
    }
    @ExceptionHandler(CpfDontExistException.class)
    private ResponseEntity<String> cpfDontExistHandler(CpfDontExistException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorMessage.CPF_INEXISTENTE);
    }
    @ExceptionHandler(CpfException.class)
    private ResponseEntity<String> cpfHandler(CpfException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorMessage.CPF_INVALIDO);
    }
    @ExceptionHandler(CpfExistException.class)
    private ResponseEntity<String> cpfExistHandler(CpfExistException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorMessage.CPF_EXISTENTE);
    }
    @ExceptionHandler(DataInvalidaException.class)
    private ResponseEntity<String> dataInvalidaHandler(DataInvalidaException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorMessage.DATA_INVALIDA);
    }
    @ExceptionHandler(EmailDontExist.class)
    private ResponseEntity<String> emailDontExistHandler(EmailDontExist exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorMessage.EMAIL_INEXISTENTE);
    }
    @ExceptionHandler(EmailException.class)
    private ResponseEntity<String> emailHandler(EmailException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorMessage.EMAIL_INVALIDO);
    }
    @ExceptionHandler(EmailExistException.class)
    private ResponseEntity<String> emailExistHandler(EmailExistException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorMessage.EMAIL_EXISTENTE);
    }
    @ExceptionHandler(HotelDontExistException.class)
    private ResponseEntity<String> hotelDontExistHandler(HotelDontExistException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorMessage.HOTEL_NAO_ENCONTRADO);
    }
    @ExceptionHandler(HotelOcupadoException.class)
    private ResponseEntity<String> hotelOcupadoHandler(HotelOcupadoException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorMessage.HOTEL_OCUPADO);
    }
    @ExceptionHandler(HotelWrongEmailExcpetion.class)
    private ResponseEntity<String> hotelWrongEmailHandler(HotelWrongEmailExcpetion exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorMessage.HOTEL_EMAIL_INCORRETO);
    }
    @ExceptionHandler(IncorretDateFormatException.class)
    private ResponseEntity<String> incorretDataFormatHandler(IncorretDateFormatException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorMessage.DATA_FORMATO_INCORRETO);
    }

    @ExceptionHandler(NomeException.class)
    private ResponseEntity<String> nomeInvalidoHandler(NomeException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorMessage.NOME_INVALIDO);
    }
    @ExceptionHandler(ReservaDontExistException.class)
    private ResponseEntity<String> reservaDontExistHandler(ReservaDontExistException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorMessage.RESERVA_INEXISTENTE);
    }
    @ExceptionHandler(ReservaInvalidaException.class)
    private ResponseEntity<String> reservaInvalidaHandler(ReservaInvalidaException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorMessage.RESERVA_INVALIDA);
    }
    @ExceptionHandler(ReservaLimitException.class)
    private ResponseEntity<String> reservaLimitHandler(ReservaLimitException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorMessage.RESERVA_LIMIT);
    }
    @ExceptionHandler(SenhaException.class)
    private ResponseEntity<String> senhaLimitHandler(SenhaException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorMessage.SENHA_INVALIDA);
    }
    @ExceptionHandler(NullPointerException.class)
    private ResponseEntity<String> nullPointerHandler(NullPointerException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorMessage.CAMPO_VAZIO);
    }
    @ExceptionHandler(CepFormatoException.class)
    private ResponseEntity<String> cepFormatoHandler(CepFormatoException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorMessage.CEP_INVALIDO);
    }
    @ExceptionHandler(CepDontExistException.class)
    private ResponseEntity<String> cepDontExistHandler(CepDontExistException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorMessage.CEP_INEXISTENTE);
    }
    @ExceptionHandler(HotelCantUpdateExcpetion.class)
    private ResponseEntity<String> hotelCantUpdateHandler(HotelCantUpdateExcpetion exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorMessage.HOTEL_UPDATE_NEGADO);
    }
    @ExceptionHandler(HotelMismatchExcpetion.class)
    private ResponseEntity<String> hotelMismatchHandler(HotelMismatchExcpetion exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorMessage.HOTEL_INCORRETO);
    }
}
