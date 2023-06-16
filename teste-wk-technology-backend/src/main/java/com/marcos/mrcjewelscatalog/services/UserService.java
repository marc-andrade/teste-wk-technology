package com.marcos.mrcjewelscatalog.services;

import com.marcos.mrcjewelscatalog.entities.Role;
import com.marcos.mrcjewelscatalog.entities.User;
import com.marcos.mrcjewelscatalog.entities.dto.RoleDTO;
import com.marcos.mrcjewelscatalog.entities.dto.UserDTO;
import com.marcos.mrcjewelscatalog.repositories.RoleRepository;
import com.marcos.mrcjewelscatalog.repositories.UserRepository;
import com.marcos.mrcjewelscatalog.services.exceptions.DatabaseException;
import com.marcos.mrcjewelscatalog.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserDTO findById(Long id){
        return new UserDTO(
                repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Entity not found"))) ;
    }

    @Transactional
    public UserDTO insert(UserDTO entity){
        try {
            User obj = new User();
            copyDtoToEntity(entity, obj);
            obj.setPassword(passwordEncoder.encode(entity.getPassword()));
            return new UserDTO(repository.save(obj));

        }catch (DataIntegrityViolationException e){
            throw new DatabaseException("Email já cadastrado");
        }

    }
    @Transactional
    public List<UserDTO> findAll() {
        List<User> list = repository.findAll();
        List<UserDTO> listDto = new ArrayList<>();
        list.forEach(x -> listDto.add(new UserDTO(x)));
        return listDto;
    }

    @Transactional
    public UserDTO update(Long id, UserDTO dto) {
        try {
            User oldUser = repository.getReferenceById(id);

            if(!dto.getPassword().equals(oldUser.getPassword())){
                dto.setPassword(passwordEncoder.encode(dto.getPassword()));
            }

            checkExistsEmail(dto);
            copyDtoToEntity(dto, oldUser);

            return new UserDTO(repository.save(oldUser));

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

    private void copyDtoToEntity(UserDTO entity, User obj) {
        obj.setFullName(entity.getName());
        obj.setEmail(entity.getEmail());
        obj.setPassword(entity.getPassword());

        if (entity.getRoles().isEmpty()){
            entity.getRoles().add(new RoleDTO(2L, "ROLE_USER"));
        }
        obj.getRoles().clear();

        entity.getRoles().forEach(dto ->{
            Role role = roleRepository.getReferenceById(dto.getId());
            obj.getRoles().add(role);
        });
    }

    private void checkExistsEmail(UserDTO dto){
        Optional<User> obj = repository.findByEmail(dto.getEmail());
        if (obj.isPresent() && !Objects.equals(obj.get().getId(), dto.getId())) {
            throw new DataIntegrityViolationException("E-mail já cadastrado no sistema!");
        }
    }

}
