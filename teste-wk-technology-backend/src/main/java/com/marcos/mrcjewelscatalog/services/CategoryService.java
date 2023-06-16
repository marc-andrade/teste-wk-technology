package com.marcos.mrcjewelscatalog.services;

import com.marcos.mrcjewelscatalog.entities.dto.CategoryDTO;
import com.marcos.mrcjewelscatalog.entities.Category;
import com.marcos.mrcjewelscatalog.repositories.CategoryRepository;
import com.marcos.mrcjewelscatalog.services.exceptions.DatabaseException;
import com.marcos.mrcjewelscatalog.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;

    @Transactional
    public CategoryDTO findById(Long id){
        return new CategoryDTO(
                repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Entity not found"))) ;
    }
    @Transactional
    public CategoryDTO insert(CategoryDTO entity){
        Category obj = new Category();
        copyDtoToEntity(entity,obj);
        return new CategoryDTO(repository.save(obj));
    }
    @Transactional
    public Page<CategoryDTO> findAllPaged(Pageable pageable) {
        Page<Category> list = repository.findAll(pageable);
        return list.map(CategoryDTO::new);
    }
    @Transactional
    public CategoryDTO update(Long id, CategoryDTO dto) {
        try {
            Category entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            return new CategoryDTO(repository.save(entity));

        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Id not found " + id);
        }
    }
    @Transactional
    public void delete(Long id) {

        try {
            repository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity violation");
        }
    }

    private void copyDtoToEntity(CategoryDTO entity, Category obj) {
        obj.setName(entity.getName());
    }
}
