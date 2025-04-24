package br.com.adocao.api.security;


import br.com.adocao.api.entity.UserModel;
import br.com.adocao.api.exceptions.EventNotFoundException;
import br.com.adocao.api.repository.IUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;


@Component
public class CustomUserDetailsService implements UserDetailsService {
    
    private final IUserRepository repository;

    public CustomUserDetailsService(IUserRepository repository){
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserModel user = this.repository.findByEmail(username).orElseThrow(() -> new EventNotFoundException(""));
    return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }
}