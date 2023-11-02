package com.aytocarmona.coworking.v1.security.service;

import com.aytocarmona.coworking.v1.dto.UserDto;
import com.aytocarmona.coworking.v1.model.User;
import com.aytocarmona.coworking.v1.repository.UserRepository;

import com.aytocarmona.coworking.v1.security.authentication.UserDetailsImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    /**
     * An instance of the UserRepository.
     */
    private final UserRepository userRepository;

    /**
     * Constructor for UserDetailsServiceImpl.
     *
     * @param userRepository The UserRepository implementation.
     */
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Loads the user details by the given username (DNI).
     *
     * @param dni The DNI of the user.
     * @return UserDetails object representing the user.
     * @throws UsernameNotFoundException if the user is not found.
     */
    @Override
    public UserDetails loadUserByUsername(String dni) throws UsernameNotFoundException {
        User user = userRepository.findOneByDni(dni).orElse(null);
        if (user != null) {
            UserDto userDto = new UserDto();
            userDto.setId(user.getId());
            userDto.setDni(user.getDni());
            userDto.setPassword(user.getPassword());

            return new UserDetailsImpl(userDto);
        } else {
            return null;
        }
    }
}
