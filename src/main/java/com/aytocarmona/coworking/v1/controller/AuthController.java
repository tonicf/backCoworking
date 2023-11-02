package com.aytocarmona.coworking.v1.controller;

import com.aytocarmona.coworking.v1.dto.UserDto;
import jakarta.annotation.security.PermitAll;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.version}")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    private final UserDetailsService userDetailsService;

    /**
     * Constructor for the AuthController class.
     *
     * @param userDetailsService The UserDetailsService implementation to be injected into the controller.
     */
    public AuthController(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    /**
     * This endpoint handles the login request (/login) to authenticate the user and obtain an access token.
     *
     * @param userDto UserDto object that contains the user credentials (username and password)
     */
    @PermitAll
    @PostMapping("/login")
    public void login(@RequestBody UserDto userDto) {
        userDetailsService.loadUserByUsername(userDto.getDni());
    }
}
