package de.sunbook.api.services;

import java.util.Set;
import java.util.HashSet;

import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Set<GrantedAuthority> grantedAuthority = new HashSet<>();
        grantedAuthority.add(new SimpleGrantedAuthority("ROLE_" + "admin"));
        return new User("foo", encoder.encode("foo"), grantedAuthority);
    }
}
