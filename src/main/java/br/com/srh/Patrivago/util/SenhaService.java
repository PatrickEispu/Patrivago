package br.com.srh.Patrivago.util;

import br.com.srh.Patrivago.constante.ErrorMessage;
import br.com.srh.Patrivago.exception.SenhaException;
import org.springframework.stereotype.Service;

@Service
public class SenhaService {

    public boolean isSenhaValid(String senha)
    {
        return senha.length()>5;
    }

    public void validarSenha(String senha) throws SenhaException {
        if (senha==null || senha.length()<6)
        {
            throw new SenhaException(ErrorMessage.SENHA_INVALIDA+senha);
        }
    }
}
