package com.example.notes.secure;

import com.example.notes.common.Role;
import com.example.notes.domain.User;
import com.example.notes.dto.requests.CreateUserRequest;
import com.example.notes.repository.UserRepository;
import com.example.notes.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsServiceImpl")
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository repository;

    private final UserService service;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(repository.count() == 0)
            repository.save(service.createUser(new CreateUserRequest("admin", "admin", "Admin", Role.Admin)));

        User user = repository.findByLogin(username).orElseThrow(() -> new UsernameNotFoundException("User doesn't exists"));
        return SecurityUser.fromUser(user);
    }
}
