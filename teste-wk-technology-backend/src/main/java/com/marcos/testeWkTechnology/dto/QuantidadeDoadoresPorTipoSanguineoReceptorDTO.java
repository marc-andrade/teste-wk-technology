package com.marcos.testeWkTechnology.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuantidadeDoadoresPorTipoSanguineoReceptorDTO {

    private String tipoSanguineoReceptor;
    private Long quantidadeDoadores;
}
