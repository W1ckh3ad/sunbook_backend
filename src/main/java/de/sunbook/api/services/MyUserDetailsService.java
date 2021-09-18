package de.sunbook.api.services;

import java.util.Set;
import java.sql.SQLException;
import java.util.HashSet;

import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService = new UserService();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            var user = userService.findUserByName(username);
            Set<GrantedAuthority> grantedAuthority = new HashSet<>();
            grantedAuthority.add(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
            return new User(user.getEmail(), user.getPassword(), grantedAuthority);
        } catch (SQLException e) {
            throw new UsernameNotFoundException("User not Found");
        } catch (UsernameNotFoundException e) {
            throw e;
        }
    }
}
