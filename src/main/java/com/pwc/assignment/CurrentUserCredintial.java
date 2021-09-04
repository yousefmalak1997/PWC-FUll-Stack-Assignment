package com.pwc.assignment;


import com.pwc.assignment.users.Users;
import com.pwc.assignment.users.UsersRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CurrentUserCredintial {

    private final UsersRepository usersRepository;

    public CurrentUserCredintial(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public Long getCurrentUserCredUserId() {

        //here we get the current user id by his email from the credential
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserEmail = authentication.getName();
        Optional<Users> byEmail = usersRepository.getByEmail(currentUserEmail);

        if( byEmail.isPresent() ) {
            Long id = byEmail.get().getId();
            return id;
        }

        return null;
    }

    public List<? extends GrantedAuthority> getCurrentUserCredUserRole() {

        //getting current user roles from the authentication.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<? extends GrantedAuthority> authorities = (List<? extends GrantedAuthority>) authentication.getAuthorities();
        return authorities;
    }
}
