package com.marcos.testeWkTechnology.services;

import com.marcos.testeWkTechnology.entities.Role;
import com.marcos.testeWkTechnology.entities.User;
import com.marcos.testeWkTechnology.repositories.RoleRepository;
import com.marcos.testeWkTechnology.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DBService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public void instanciaDB(){

        Role role1 = new Role(null,"ROLE_ADMIN");

        User user4 = new User(null,
                "admin",
                "admin@email.com",
                passwordEncoder.encode("123"), List.of(role1));

        roleRepository.saveAll(List.of(role1));
        userRepository.saveAll(List.of(user4));

    }
}
