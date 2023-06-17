package com.marcos.testeWkTechnology.services;

import com.marcos.testeWkTechnology.entities.DoadorDeSangue;
import com.marcos.testeWkTechnology.entities.dto.DoadorDeSangueDTO;
import com.marcos.testeWkTechnology.entities.dto.IMCMedioPorFaixaIdadeDTO;
import com.marcos.testeWkTechnology.entities.dto.QuantidadePorEstadoDTO;
import com.marcos.testeWkTechnology.repositories.DoadorDeSangueRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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



        doadores.forEach(doadorDeSangue -> {

        });
        return null;
    }
}
