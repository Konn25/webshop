package com.webshop.Webshop.auth;

import com.webshop.Webshop.config.JwtService;
import com.webshop.Webshop.dao.UserDAO;
import com.webshop.Webshop.dto.UsersDTO;
import com.webshop.Webshop.jpa.Users;
import com.webshop.Webshop.jpa.UsersRepository;
import com.webshop.Webshop.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UsersService usersService;

    private final UsersRepository usersRepository;

    private final ModelMapper modelMapper;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    private final UserDAO userDAO;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = UsersDTO.builder()
                                .username(request.getUsername())
                                .email(request.getEmail())
                                .password(passwordEncoder.encode(request.getPassword()))
                                .country(request.getCountry())
                                .city(request.getCity())
                                .address(request.getAddress())
                                .mobile(request.getMobile())
                                .admin(false)
                                .build();

        usersService.createNewUsers(modelMapper.map(user, Users.class));

        UserDetails userDetails = userDAO.findUserByEmail(user.getEmail());

        var jwtToken = jwtService.generateToken(userDetails);

        return AuthenticationResponse.builder()
                                     .token(jwtToken)
                                     .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
            )
        );

        var user = usersRepository.findByEmail(request.getEmail()).orElseThrow();

        UserDetails userDetails = userDAO.findUserByEmail(user.getEmail());

        var jwtToken = jwtService.generateToken(userDetails);

        return AuthenticationResponse.builder()
                                     .token(jwtToken)
                                     .build();
    }
}
