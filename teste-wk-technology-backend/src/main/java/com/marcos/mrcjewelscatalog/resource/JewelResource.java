package com.marcos.mrcjewelscatalog.resource;

import com.marcos.mrcjewelscatalog.entities.dto.JewelDTO;
import com.marcos.mrcjewelscatalog.entities.dto.JewelInsertDTO;
import com.marcos.mrcjewelscatalog.services.JewelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/jewels")
@RequiredArgsConstructor
public class JewelResource {

    private final JewelService service;

    @GetMapping
    public ResponseEntity<Page<JewelDTO>> findAllPaged(Pageable pageable){
        return ResponseEntity.ok().body(service.findAllPaged(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<JewelDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<JewelDTO> insert(@RequestBody @Valid JewelInsertDTO dto){
        JewelDTO newDto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(newDto.getId()).toUri();
        return ResponseEntity.created(uri).body(newDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<JewelDTO> update(@PathVariable Long id, @RequestBody @Valid JewelInsertDTO dto){
        return ResponseEntity.ok().body(service.update(id,dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
