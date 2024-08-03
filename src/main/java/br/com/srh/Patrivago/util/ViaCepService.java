package br.com.srh.Patrivago.util;


import br.com.srh.Patrivago.dto.ViaCepResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ViaCepService {

    @Autowired
    private RestTemplate restTemplate;


//    public EnderecoResponse buscarEnderecoPorCep(String cep) {
//        String url = "https://viacep.com.br/ws/" + cep + "/json/";
//        ResponseEntity<EnderecoResponse> response = restTemplate.getForEntity(url, EnderecoResponse.class);
//        return response.getBody();
//    }
    public ViaCepResponse buscarEnderecoPorCep (String cep)
    {
        String url = "https://viacep.com.br/ws/" + cep + "/json";
        return restTemplate.getForObject(url, ViaCepResponse.class,cep);
    }

    public boolean isCepValid(String cep) {
        // Remova quaisquer caracteres não numéricos do CEP
        String cleanedCep = cep.replaceAll("\\D", "");

        String regex = "^[0-9]{5}-[0-9]{3}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(cep);
      //  System.out.println(matcher.matches());
        return matcher.matches();

    }

    public boolean cepExist(String cep) {
        return buscarEnderecoPorCep(cep)!=null;
    }
    //TODO verificar como usar o via cep
}
