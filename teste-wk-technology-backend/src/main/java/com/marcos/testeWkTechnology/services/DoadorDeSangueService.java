package com.marcos.testeWkTechnology.services;

import com.marcos.testeWkTechnology.entities.DoadorDeSangue;
import com.marcos.testeWkTechnology.entities.dto.DoadorDeSangueDTO;
import com.marcos.testeWkTechnology.entities.dto.IMCMedioPorFaixaIdadeDTO;
import com.marcos.testeWkTechnology.entities.dto.PercentualObesosGeneroDTO;
import com.marcos.testeWkTechnology.entities.dto.QuantidadePorEstadoDTO;
import com.marcos.testeWkTechnology.repositories.DoadorDeSangueRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DoadorDeSangueService {

    private final DoadorDeSangueRepository repository;
    private final ModelMapper modelMapper;

    public String adicionarDoadores(List<DoadorDeSangueDTO> listDTO) {
        List<DoadorDeSangue> doadores = listDTO.stream()
                .map(dto -> modelMapper.map(dto, DoadorDeSangue.class)).toList();
        repository.saveAll(doadores);
        return "Doadores adicionados com sucesso";
    }

    public List<QuantidadePorEstadoDTO> getQuantidadePorEstado() {
        return repository.findQuantidadePorEstado();
    }

    public List<IMCMedioPorFaixaIdadeDTO> getIMCMedioPorFaixaIdade() {

        List<DoadorDeSangue> doadores = repository.findAll();
        int faixaInicial = 0;
        int faixaFinal = 10;
        int faixaFinalMaxima = 120;

        List<IMCMedioPorFaixaIdadeDTO> imcMedioPorFaixaIdade = new ArrayList<>();

        while (faixaInicial < faixaFinalMaxima) {

            double somaIMC = 0;
            int contador = 0;

            for (DoadorDeSangue doador : doadores) {
                int idade = calcularIdade(doador.getDataNascimento());

                if (idade >= faixaInicial && idade < faixaFinal) {
                    double imc = calcularIMC(doador.getPeso(), doador.getAltura());
                    somaIMC += imc;
                    contador++;
                }
            }
            double imcMedio = contador > 0 ? somaIMC / contador : 0;

            String faixaIdade = String.format("%d a %d anos", faixaInicial, faixaFinal);

            IMCMedioPorFaixaIdadeDTO imcInfoDTO = new IMCMedioPorFaixaIdadeDTO();
            imcInfoDTO.setFaixaIdade(faixaIdade);
            imcInfoDTO.setImcMedio(imcMedio);

            imcMedioPorFaixaIdade.add(imcInfoDTO);

            if (faixaInicial == 0) faixaInicial++;

            faixaInicial += 10;
            faixaFinal += 10;
        }

        return imcMedioPorFaixaIdade;
    }

    private Integer calcularIdade(String dataNascimento) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataNasc = LocalDate.parse(dataNascimento, formatter);
        LocalDate dataAtual = LocalDate.now();
        return Period.between(dataNasc, dataAtual).getYears();
    }

    private double calcularIMC(int peso, double altura) {
        return peso / (altura * altura);
    }

    public List<PercentualObesosGeneroDTO> getPercentualObesosGenero() {

        int contador;
        double total;
        double percentual;

        List<DoadorDeSangue> homens = repository.findBySexo("Masculino");
        List<DoadorDeSangue> mulheres = repository.findBySexo("Feminino");

        PercentualObesosGeneroDTO percentualHomens = new PercentualObesosGeneroDTO();
        percentualHomens.setGenero("Homens");

        PercentualObesosGeneroDTO percentualMulheres = new PercentualObesosGeneroDTO();
        percentualMulheres.setGenero("Mulheres");

        contador = calcularQuantidadeObeso(homens);
        total = homens.size();
        percentual = calcularPorcentagemObeso(total, contador);

        percentualHomens.setPercentual(percentual);

        contador = calcularQuantidadeObeso(mulheres);
        total = mulheres.size();
        percentual = calcularPorcentagemObeso(total, contador);

        percentualMulheres.setPercentual(percentual);

        return new ArrayList<>(List.of(percentualHomens, percentualMulheres));
    }

    private Integer calcularQuantidadeObeso(List<DoadorDeSangue> doadores) {
        int contador = 0;
        for (DoadorDeSangue doador : doadores) {

            double imc = calcularIMC(doador.getPeso(), doador.getAltura());

            if (imc > 30) {
                contador++;
            }
        }
        return contador;
    }

    private Double calcularPorcentagemObeso(double total, int contador) {
        double percentual = total != 0.0 ? (contador * 100) / total : 0;
        return Math.round(percentual * 100.0) / 100.0;
    }

}
