package br.com.srh.Patrivago.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class ContaEmpresaResponse extends ContaResponse {
 private String cnpj;
 private Long idContaEmpresa;
}
