package com.itacademy.model;

import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public enum Role implements GrantedAuthority {
    USER,
    MANAGER;


    @Override
    public String getAuthority() {
        return this.name();
    }

    public List<GrantedAuthority> getAuthorities() {
        return this.equals(MANAGER) ?
           List.of(USER, MANAGER) : List.of(USER);
    }
}
