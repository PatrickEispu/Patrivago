package br.com.srh.Patrivago.util;

import br.com.srh.Patrivago.constante.ErrorMessage;
import br.com.srh.Patrivago.exception.NomeException;
import org.springframework.stereotype.Service;

@Service
public class NomeService {

    public void validarNome(String name) throws NomeException {
        if (name == null || !isValidName(name))
        {
            throw new NomeException(ErrorMessage.NOME_INVALIDO + name);
        }
    }

    public boolean isValidName(String name) {
        // Expressão regular para validar o nome (apenas letras e espaços)
        String pattern = "^[a-zA-Z\\s]+$";
        return name.matches(pattern);
    }
}
