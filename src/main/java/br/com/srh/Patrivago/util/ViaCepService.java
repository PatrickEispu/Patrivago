package br.com.srh.Patrivago.util;


import br.com.srh.Patrivago.dto.EnderecoResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ViaCepService {
    private RestTemplate restTemplate;

    public EnderecoResponse buscarEnderecoPorCep(String cep) {
        String url = "https://viacep.com.br/ws/" + cep + "/json/";
        ResponseEntity<EnderecoResponse> response = restTemplate.getForEntity(url, EnderecoResponse.class);
        return response.getBody();
    }
 //TODO verificar como usar o via cep
}
