package com.marcos.mrcjewelscatalog.repositories;

import com.marcos.mrcjewelscatalog.entities.Category;
import com.marcos.mrcjewelscatalog.entities.Jewel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JewelRepository extends JpaRepository<Jewel, Long> {
}
