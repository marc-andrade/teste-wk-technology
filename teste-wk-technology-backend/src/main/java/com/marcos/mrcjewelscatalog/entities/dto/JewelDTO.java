package com.marcos.mrcjewelscatalog.entities.dto;

import com.marcos.mrcjewelscatalog.entities.Jewel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class JewelDTO {

    private Long id;
    private String name;
    private String imgUrl;
    private String description;
    private Double weight;
    private Double price;
    private String size;
    private CategoryDTO categoryDTO;

    public JewelDTO(Jewel entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.imgUrl = entity.getImgUrl();
        this.description = entity.getDescription();
        this.weight = entity.getWeight();
        this.price = entity.getPrice();
        this.size = entity.getSize();
        this.categoryDTO = new CategoryDTO(entity.getCategory());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JewelDTO roleDTO = (JewelDTO) o;
        return Objects.equals(id, roleDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
