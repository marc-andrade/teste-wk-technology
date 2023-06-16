package com.marcos.mrcjewelscatalog.auth;

import com.marcos.mrcjewelscatalog.config.JwtService;
import com.marcos.mrcjewelscatalog.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final UserRepository repository;
//  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

//  public AuthenticationResponse register(RegisterRequest request) {
//    var user = User.builder()
//        .name(request.getName())
//        .email(request.getEmail())
//        .password(passwordEncoder.encode(request.getPassword()))
//        .roles(Set.of(new Role(null, RoleEnum.USER)))
//        .build();
//    repository.save(user);
//    var jwtToken = jwtService.generateToken(user);
//    return AuthenticationResponse.builder()
//        .token(jwtToken)
//        .build();
//  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getEmail(),
            request.getPassword()
        )
    );
    var user = repository.findByEmail(request.getEmail())
        .orElseThrow();
    var jwtToken = jwtService.generateToken(user);
    return AuthenticationResponse.builder()
        .token(jwtToken)
        .build();
  }
}
