package com.pwc.assignment.registration;


import com.pwc.assignment.users.Users;
import com.pwc.assignment.users.UsersRole;
import com.pwc.assignment.users.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class RegistrationService {


    private final UsersService usersService;
    private final EmailValidator emailValidator;

    public String register(RegistrationRequest request) {

        boolean isValidEmail = emailValidator.test(request.getEmail());

        if ( !isValidEmail ) {
            return "notValidEmail";
        }

        if( request.getFirstName().isEmpty() ||
                request.getLastName().isEmpty() ||
                request.getEmail().isEmpty() ||
                request.getPassword().isEmpty() ||
                request.getUserRole() == null ) {
            return "EnterAllOptions";
        }

        if ( request.getUserRole() == UsersRole.EMPLOYEE && request.getDepartmentsId() == null ) {
            return "EnterDepartment";
        }

        String token = usersService.signUpUser(
                new Users(
                        request.getFirstName(),
                        request.getLastName(),
                        request.getEmail(),
                        request.getPassword(),
                        request.getUserRole(),
                        request.getDepartmentsId()
                )
        );

        return token;
    }
}
