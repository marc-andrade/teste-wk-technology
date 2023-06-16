package com.marcos.mrcjewelscatalog.entities.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class JewelInsertDTO {

    private Long id;
    @NotBlank(message = "campo obrigatório")
    private String name;
    private String imgUrl;
    private String description;
    private Double weight;
    private Double price;
    private String size;
    @NotNull
    private Long categoryId;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JewelInsertDTO roleDTO = (JewelInsertDTO) o;
        return Objects.equals(id, roleDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
