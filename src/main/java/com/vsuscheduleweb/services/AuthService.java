package com.vsuscheduleweb.services;


import com.vsuscheduleweb.DTO.AppUserDto;
import com.vsuscheduleweb.DTO.AuthReqDto;
import com.vsuscheduleweb.DTO.AuthResponse;
import com.vsuscheduleweb.Exceptions.ObjectIsPresentException;
import com.vsuscheduleweb.Exceptions.ResponseNotFoundException;
import com.vsuscheduleweb.entity.AppUser;
import com.vsuscheduleweb.repositories.AppUserRepository;
import com.vsuscheduleweb.security.roles.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AppUserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(AppUserDto userDto){
        if(repository.findByLogin(userDto.getLogin()).isPresent()){
            throw new ObjectIsPresentException("user with name " + userDto.getLogin() + " is present.");
        }
        if(repository.findByEmail(userDto.getEmail()).isPresent()){
            throw new ObjectIsPresentException("user with email " + userDto.getEmail() + " is present.");
        }else {
            var user = new AppUser()
                    .setEmail(userDto.getEmail())
                    .setName(userDto.getName())
                    .setLogin(userDto.getLogin())
                    .setRoles(Set.of(Role.USER))
                    .setId(UUID.randomUUID())
                    .setPassword(passwordEncoder.encode(userDto.getPassword()))
                    .setName(userDto.getName())
                    .setLastname(userDto.getLastname());
            repository.save(user);
            var jwtToken = jwtService.generateToken(user);
            return new AuthResponse().setToken(jwtToken);
        }

    }

    public AuthResponse auth(AuthReqDto req){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        req.getLogin(),
                        req.getPassword()
                )
        );
        Optional<AppUser> opt_user = repository.findByLogin(req.getLogin());
        if(!opt_user.isPresent()){
            throw new ResponseNotFoundException("user with name " + req.getLogin() + " not found.");
        }else {
            var jwtToken = jwtService.generateToken(opt_user.get());
            return new AuthResponse().setToken(jwtToken);
        }

    }
}
