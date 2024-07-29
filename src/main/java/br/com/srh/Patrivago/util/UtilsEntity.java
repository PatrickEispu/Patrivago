package br.com.srh.Patrivago.util;

import br.com.srh.Patrivago.enuns.TipoContaEnum;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UtilsEntity {

    public static TipoContaEnum cpfCnpjCheck(String cpfCnpj) {
        String cleanedNumber = cpfCnpj.replaceAll("\\D", "");

        if (cleanedNumber.length() == 11) {
            return TipoContaEnum.CLIENTE;
        } else {
            return TipoContaEnum.EMPRESA;
        }

    }



}
