package br.com.srh.Patrivago.usecase;

import br.com.srh.Patrivago.dao.hotel.EnderecoRepository;
import br.com.srh.Patrivago.dto.ContaRequest;
import br.com.srh.Patrivago.dto.EnderecoResponse;
import br.com.srh.Patrivago.dto.HotelRequest;
import br.com.srh.Patrivago.model.conta.ContaEmpresaEntity;
import br.com.srh.Patrivago.model.hotel.EnderecoEntity;
import br.com.srh.Patrivago.model.hotel.HotelEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {

    @Autowired
    EnderecoRepository enderecoRepository;

    public EnderecoResponse addEndereco(HotelRequest hotelRequest) {

        EnderecoEntity endereco = EnderecoEntity.builder()
                .cep(hotelRequest.getEndereco().getCep())
                .rua(hotelRequest.getEndereco().getRua())
                .numero(hotelRequest.getEndereco().getNumero())
                .uf(hotelRequest.getEndereco().getUf())
                .build();

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
}
