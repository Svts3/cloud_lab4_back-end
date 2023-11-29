package ua.lviv.iot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.lviv.iot.model.Owner;
import ua.lviv.iot.service.OwnerService;

import java.util.Arrays;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {


    private OwnerService ownerService;

    @Autowired
    public CustomUserDetailsService(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Owner owner;
        try {
            owner = ownerService.findByEmail(username);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new User(owner.getEmail(), owner.getPassword(), List.of(new SimpleGrantedAuthority("USER")));

    }
}
