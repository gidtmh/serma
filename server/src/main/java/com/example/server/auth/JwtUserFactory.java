package com.example.server.auth;

import com.example.server.model.Employee;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@NoArgsConstructor
public final class JwtUserFactory {

    public static UserDetails create(Employee employee) {
        return JwtUser.builder()
            .id(employee.getId())
            .username(employee.getName())
            .password(employee.getPassword())
            .authorities(mapRoleToGrantedAuthorities(employee.getRole().getRole().name()))
            .enabled(true)
            .build();
    }

    private static List<GrantedAuthority> mapRoleToGrantedAuthorities(String userRole) {
        return new ArrayList<>(Collections.singletonList(new SimpleGrantedAuthority(userRole)));
    }
}
