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
        Role role2 = new Role(null, "ROLE_USER");

        User user1 = new User(null,
                "Marcos Andrade",
                "marcos@email.com",
                passwordEncoder.encode("123"), List.of(role1,role2));
        User user3 = new User(null,
                "Lucas Dosvaldo",
                "lucas@email.com",
                passwordEncoder.encode("123"), List.of(role1));
        User user4 = new User(null,
                "admin",
                "admin@email.com",
                passwordEncoder.encode("123"), List.of(role1));
        User user2 = new User(null,
                "User",
                "user@email.com",
                passwordEncoder.encode("123"), List.of(role2));

        roleRepository.saveAll(List.of(role1,role2));
        userRepository.saveAll(List.of(user1,user2,user3,user4));

    }
}
