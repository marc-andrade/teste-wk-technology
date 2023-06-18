package com.marcos.testeWkTechnology.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DoadorDeSangueDTO {

    private Long id;
    @JsonProperty("nome")
    private String nome;
    @JsonProperty("cpf")
    private String cpf;
    @JsonProperty("rg")
    private String rg;
    @JsonProperty("data_nasc")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private String dataNascimento;
    @JsonProperty("sexo")
    private String sexo;
    @JsonProperty("mae")
    private String mae;
    @JsonProperty("pai")
    private String pai;
    @JsonProperty("email")
    private String email;
    @JsonProperty("cep")
    private String cep;
    @JsonProperty("endereco")
    private String endereco;
    @JsonProperty("numero")
    private int numero;
    @JsonProperty("bairro")
    private String bairro;
    @JsonProperty("cidade")
    private String cidade;
    @JsonProperty("estado")
    private String estado;
    @JsonProperty("telefone_fixo")
    private String telefoneFixo;
    @JsonProperty("celular")
    private String celular;
    @JsonProperty("altura")
    private double altura;
    @JsonProperty("peso")
    private int peso;
    @JsonProperty("tipo_sanguineo")
    private String tipoSanguineo;

}
