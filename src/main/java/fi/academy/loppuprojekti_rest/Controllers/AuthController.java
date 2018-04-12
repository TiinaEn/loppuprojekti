package fi.academy.loppuprojekti_rest.Controllers;

import fi.academy.loppuprojekti_rest.Security.JwtTokenProvider;
import fi.academy.loppuprojekti_rest.Entities.Role;
import fi.academy.loppuprojekti_rest.Entities.RoleName;
import fi.academy.loppuprojekti_rest.Entities.User;
import fi.academy.loppuprojekti_rest.Exception.AppException;
import fi.academy.loppuprojekti_rest.Payload.ApiResponse;
import fi.academy.loppuprojekti_rest.Payload.JwtAuthenticationResponse;
import fi.academy.loppuprojekti_rest.Payload.LoginRequest;
import fi.academy.loppuprojekti_rest.Payload.SignUpRequest;
import fi.academy.loppuprojekti_rest.Repositories.RoleRepository;
import fi.academy.loppuprojekti_rest.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping("/travelapp")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepo userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if(userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        User user = new User(signUpRequest.getName(), signUpRequest.getUsername(),
                signUpRequest.getEmail(), signUpRequest.getPassword());

        user.setPassword(passwordEncoder.encode(user.getPassword()));

   //     String rolename = RoleName.ROLE_USER.name();
        Iterable<Role> roolit = roleRepository.findAll();
        Optional<Role> optUserRole = roleRepository.findByName(RoleName.ROLE_USER);
        Role userRole = optUserRole
                .orElseThrow(() -> new AppException("User Role not set."));

        user.setRoles(Collections.singleton(userRole));

        User result = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/{username}")
                .buildAndExpand(result.getUsername()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
    }
}
















/*
package fi.academy.loppuprojekti_rest.Controllers;

import fi.academy.loppuprojekti_rest.Configurations.UserService;
import fi.academy.loppuprojekti_rest.Entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/travelapp")
public class SecurityController {
    @Autowired
    private UserService userService;


    @PostMapping ("/register")
    public ResponseEntity register (@RequestBody User user) {
        User userExists = userService.findUserByUsername(user.getUsername());
        if(userExists != null) {
            return ResponseEntity.badRequest().body("User with the given username already exists.");
        } else {
            userService.saveUser(user);
            return ResponseEntity.accepted().body("Thank you for registering!");
        }

      //  return ResponseEntity.ok("Registration completed");
    }

}*/
