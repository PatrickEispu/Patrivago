package br.com.srh.Patrivago.usecase;

import br.com.srh.Patrivago.dao.hotel.EnderecoRepository;
import br.com.srh.Patrivago.dto.*;
import br.com.srh.Patrivago.exception.CepDontExistException;
import br.com.srh.Patrivago.exception.CepFormatoException;
import br.com.srh.Patrivago.model.hotel.EnderecoEntity;
import br.com.srh.Patrivago.util.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {

    @Autowired
    EnderecoRepository enderecoRepository;
    @Autowired
    ViaCepService viaCepService;

    public EnderecoResponse addEndereco(EnderecoEntity endereco, HotelRequest hotelRequest) {

//        EnderecoEntity endereco = EnderecoEntity.builder()
//                .cep(hotelRequest.getEndereco().getCep())
//                .rua(hotelRequest.getEndereco().getRua())
//                .numero(hotelRequest.getEndereco().getNumero())
//                .uf(hotelRequest.getEndereco().getUf())
//                .build();

        EnderecoEntity enderecoSalvo = enderecoRepository.saveEndereco(endereco, hotelRequest);
        return mapearEndereco(enderecoSalvo);
    }

    private EnderecoResponse mapearEndereco(EnderecoEntity enderecoSalvo) {
        return EnderecoResponse.builder()
                .cep(enderecoSalvo.getCep())
                .rua(enderecoSalvo.getRua())
                .numero(enderecoSalvo.getNumero())
                .uf(enderecoSalvo.getUf())
                .build();
    }


    public void cepFormatoCheck(String cep) {
        if(!viaCepService.isCepValid(cep))
        {
            throw new CepFormatoException();
        }
    }

    public EnderecoEntity viaCepConverteEndereco(String cep, String numero) {
        ViaCepResponse viaCepResponse = viaCepService.buscarEnderecoPorCep(cep);
        return EnderecoEntity.builder()
                .rua(viaCepResponse.getLogradouro())
                .cidade(viaCepResponse.getLocalidade())
                .uf(viaCepResponse.getUf())
                .numero(numero)
                .cep(cep)
                .build();
    }

    public void cepExist(String cep) {
        if (!viaCepService.cepExist(cep))
        {
            throw new CepDontExistException();
        }

    }
}
