package com.marcos.testeWkTechnology.repositories;

import com.marcos.testeWkTechnology.entities.DoadorDeSangue;
import com.marcos.testeWkTechnology.entities.dto.QuantidadePorEstadoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DoadorDeSangueRepository extends JpaRepository<DoadorDeSangue, Long> {

    @Query("SELECT new com.marcos.testeWkTechnology.entities.dto.QuantidadePorEstadoDTO(d.estado, COUNT(d))" +
            " FROM DoadorDeSangue d GROUP BY d.estado")
    List<QuantidadePorEstadoDTO> findQuantidadePorEstado();
}
