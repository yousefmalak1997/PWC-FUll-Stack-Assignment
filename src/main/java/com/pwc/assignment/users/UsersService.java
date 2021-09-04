package com.pwc.assignment.users;

import com.pwc.assignment.CurrentUserCredintial;
import com.pwc.assignment.registration.EmailValidator;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;


import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class UsersService implements UserDetailsService {

    private final static String userNotFoundMsg = "user with %s email was not found";
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UsersRepository usersRepository;
    private final CurrentUserCredintial currentUserCredintial;
    private final EmailValidator emailValidator;

    public List<Users> getUsers() {
        return usersRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return usersRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(String.format(userNotFoundMsg,email)));
    }

    public String signUpUser(Users user) {

        Boolean emailExists = usersRepository.findByEmail(user.getEmail())
                .isPresent();

        if ( emailExists ) {
            return "emailAlreadyUsed";
        }

        // this is to encode the password.
        String encodedPass = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPass);

        usersRepository.save(user);

        return "User Created";
    }

    // deleting user
    public ModelAndView deleteUser(Long id){

        boolean success = false;

        List<String> currentUserRole = currentUserCredintial
                                        .getCurrentUserCredUserRole()
                                        .stream()
                                        .map(GrantedAuthority::getAuthority)
                                        .collect(Collectors.toList());

        if ( currentUserRole.contains(UsersRole.EMPLOYEE.getEnumName()) ) {

            Long current_user_id = currentUserCredintial.getCurrentUserCredUserId();

            if ( current_user_id == id ) {
                usersRepository.deleteById(id);
                success = true;
            }

        } else if ( currentUserRole.contains(UsersRole.MANAGER.getEnumName()) ) {
            usersRepository.deleteById(id);
            success = true;
        }

        if ( success ) {
            SecurityContextHolder.clearContext();
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("index");
            return modelAndView;

        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        return modelAndView;
    }

    public String editUserDep(UsersRequest request) {

        Users users = usersRepository.getById(request.getId());

        if ( users.getUserRole() == UsersRole.EMPLOYEE && request.getDepartmentsId() == null ) {
            return "selectDep";
        }

        users.setDepartmentsId(request.getDepartmentsId());
        usersRepository.save(users);
        return "Success";
    }

    public String editUserInfo(UsersRequest request) {
        Users currentUserInfo = usersRepository.getById(currentUserCredintial.getCurrentUserCredUserId());

        if ( request.getEmail().isEmpty()     ||
            request.getFirstName().isEmpty()  ||
            request.getLastName().isEmpty()   ) {
            return "fillReqFields";
        }

        boolean isValidEmail = emailValidator.test(request.getEmail());

        if ( !isValidEmail ) {
            return "notValidEmail";
        }

        Boolean emailExists = usersRepository.findByEmail(request.getEmail())
                .isPresent();

        if ( emailExists && !request.getEmail().contains(currentUserInfo.getEmail()) ) {
            return "emailAlreadyUsed";
        }

        currentUserInfo.setEmail(request.getEmail());
        currentUserInfo.setFirstName(request.getFirstName());
        currentUserInfo.setLastName(request.getLastName());
        usersRepository.save(currentUserInfo);
        return "Success";
    }

    public Users getUserInfo() {
        return usersRepository.getById(currentUserCredintial.getCurrentUserCredUserId());
    }
}
