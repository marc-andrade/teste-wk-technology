package com.marcos.mrcjewelscatalog.repositories;

import com.marcos.mrcjewelscatalog.entities.Category;
import com.marcos.mrcjewelscatalog.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
