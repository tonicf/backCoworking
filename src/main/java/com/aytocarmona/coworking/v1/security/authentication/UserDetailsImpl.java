package com.aytocarmona.coworking.v1.security.authentication;

import com.aytocarmona.coworking.v1.dto.UserDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class UserDetailsImpl implements UserDetails {
    private final transient UserDto user;

    public UserDetailsImpl(UserDto user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Since we are not using authorities in this example, return an empty list.
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        // Retrieve the user's password.
        return user.getPassword();
    }

    /**
     * Returns the DNI (Documento Nacional de Identidad) of the user.
     *
     * @return the DNI of the user.
     */
    public String getDni() {
        return user.getDni();
    }

    /**
     * Returns the ID of the user.
     *
     * @return the ID of the user.
     */
    public Long getId() {
        return user.getId();
    }

    @Override
    public String getUsername() {
        // Return null since we're not using a username in this example.
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        // Return true since account expiration is not being checked in this example.
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // Return true since account locking is not being checked in this example.
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // Return true since credentials expiration is not being checked in this example.
        return true;
    }

    @Override
    public boolean isEnabled() {
        // Return true since the user is considered enabled in this example.
        return true;
    }
}

