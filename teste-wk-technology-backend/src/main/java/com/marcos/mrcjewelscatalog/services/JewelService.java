package com.marcos.mrcjewelscatalog.services;

import com.marcos.mrcjewelscatalog.entities.dto.JewelDTO;
import com.marcos.mrcjewelscatalog.entities.Jewel;
import com.marcos.mrcjewelscatalog.entities.dto.JewelInsertDTO;
import com.marcos.mrcjewelscatalog.repositories.CategoryRepository;
import com.marcos.mrcjewelscatalog.repositories.JewelRepository;
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
public class JewelService {

    private final JewelRepository repository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public JewelDTO findById(Long id){
        return new JewelDTO(
                repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Entity not found"))) ;
    }
    @Transactional
    public JewelDTO insert(JewelInsertDTO entity){

            Jewel obj = new Jewel();
            copyInsertDtoToEntity(entity,obj);
            return new JewelDTO(repository.save(obj));
    }
    @Transactional
    public Page<JewelDTO> findAllPaged(Pageable pageable) {
        Page<Jewel> list = repository.findAll(pageable);
        return list.map(JewelDTO::new);
    }
    @Transactional
    public JewelDTO update(Long id, JewelInsertDTO dto) {
        try {
            Jewel entity = repository.getReferenceById(id);
            copyInsertDtoToEntity(dto,entity);
            return new JewelDTO(repository.save(entity));

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

    private void copyInsertDtoToEntity(JewelInsertDTO entity, Jewel obj) {
        obj.setName(entity.getName());
        obj.setImgUrl(entity.getImgUrl());
        obj.setDescription(entity.getDescription());
        obj.setWeight(entity.getWeight());
        obj.setPrice(entity.getPrice());
        obj.setSize(entity.getSize());
        obj.setCategory(categoryRepository.getReferenceById(entity.getCategoryId()));
    }
}
