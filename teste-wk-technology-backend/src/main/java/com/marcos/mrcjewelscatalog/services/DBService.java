package com.marcos.mrcjewelscatalog.services;

import com.marcos.mrcjewelscatalog.entities.Category;
import com.marcos.mrcjewelscatalog.entities.Jewel;
import com.marcos.mrcjewelscatalog.entities.Role;
import com.marcos.mrcjewelscatalog.entities.User;
import com.marcos.mrcjewelscatalog.repositories.CategoryRepository;
import com.marcos.mrcjewelscatalog.repositories.JewelRepository;
import com.marcos.mrcjewelscatalog.repositories.RoleRepository;
import com.marcos.mrcjewelscatalog.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DBService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final CategoryRepository categoryRepository;
    private final JewelRepository jewelRepository;
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

        Category category1 = new Category(null, "Colar");
        Category category2 = new Category(null, "Pulseira");
        Category category3 = new Category(null, "Anel");

        Jewel jwl1 = new Jewel(
                null,
                "Colar de Ouro Cravejado de Cristais",
                "https://images.pexels.com/photos/14999288/pexels-photo-14999288.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                1.3,
                "45 cm",
                1800.25,
                category1);
        Jewel jwl2 = new Jewel(
                null,
                "Anel 18k cravejado com diamantes e rubi",
                "https://images.pexels.com/photos/9838851/pexels-photo-9838851.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                3.4,
                "Sobre Medida",
                2709.9,
                category3);
        Jewel jwl3 = new Jewel(
                null,
                "Pulseira de Ouro",
                "https://images.pexels.com/photos/12124638/pexels-photo-12124638.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                0.7,
                "18 cm",
                400.25,
                category2);
        Jewel jwl4 = new Jewel(
                null,
                "Anel de Formatura",
                "https://images.pexels.com/photos/9838851/pexels-photo-9838851.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
                "Anel de formatura, customziado para seu curso.",
                4.4,
                "Sobre Medida",
                2600.25,
                category3);
        Jewel jwl5 = new Jewel(
                null,
                "Aliança de casamento",
                "https://images.pexels.com/photos/6479595/pexels-photo-6479595.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
                "Linda aliança de casamento com um inigualável diamante",
                3.8,
                "Sobre Medida",
                2600.25,
                category3);

        roleRepository.saveAll(List.of(role1,role2));
        userRepository.saveAll(List.of(user1,user2,user3,user4));


        categoryRepository.saveAll(List.of(category1,category2,category3));
        jewelRepository.saveAll(List.of(jwl1,jwl2,jwl3,jwl4,jwl5));
    }
}
