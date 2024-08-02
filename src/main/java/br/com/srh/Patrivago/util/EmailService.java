package br.com.srh.Patrivago.util;


import br.com.srh.Patrivago.constante.ErrorMessage;
import br.com.srh.Patrivago.exception.EmailException;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class EmailService {
    public boolean isValidEmail(String email) {
        // Expressão regular para validação de e-mail
        String pattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(email);

        return matcher.matches();
    }

    public void validarEmail(String email) throws EmailException {
        if (email==null || !isValidEmail(email))
        {
            throw new EmailException(ErrorMessage.EMAIL_INVALIDO + email);
        }

    }
}
