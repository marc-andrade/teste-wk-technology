package com.marcos.testeWkTechnology.resource;

import com.marcos.testeWkTechnology.dto.*;
import com.marcos.testeWkTechnology.services.DoadorDeSangueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doadores")
@RequiredArgsConstructor
public class DoadorDeSangueResource {

    private final DoadorDeSangueService service;

    @PostMapping("/adicionar-varios-doadores")
    public ResponseEntity<String> adicionarDoadores(@RequestBody List<DoadorDeSangueDTO> doadores){
        return ResponseEntity.ok().body(service.adicionarDoadores(doadores));
    }

    @GetMapping("/quantidade-por-estado")
    public ResponseEntity<List<QuantidadePorEstadoDTO>> getQuantidadePorEstado(){
        return ResponseEntity.ok().body(service.getQuantidadePorEstado());
    }

    @GetMapping("/imc-medio-por-faixa-idade")
    public ResponseEntity<List<IMCMedioPorFaixaIdadeDTO>> getIMCMedioPorFaixaIdade(){
        return ResponseEntity.ok().body(service.getIMCMedioPorFaixaIdade());
    }

    @GetMapping("/percentual-obesos-genero")
    public ResponseEntity<List<PercentualObesosGeneroDTO>> getPercentualObesosGenero(){
        return ResponseEntity.ok().body(service.getPercentualObesosGenero());
    }
    @GetMapping("/media-idade-por-tipo-sanguineo")
    public ResponseEntity<List<MediaIdadePorTipoSanguineoDTO>> getMediaIdadePorTipoSaguineo(){
        return ResponseEntity.ok().body(service.getMediaIdadePorTipoSaguineo());
    }

    @GetMapping("/quantidade-possiveis-doadores")
    public ResponseEntity<List<QuantidadeDoadoresPorTipoSanguineoReceptorDTO>> getQuantidadePossiveisDoadores(){
        return ResponseEntity.ok().body(service.getQuantidadePossiveisDoadores());
    }
}
