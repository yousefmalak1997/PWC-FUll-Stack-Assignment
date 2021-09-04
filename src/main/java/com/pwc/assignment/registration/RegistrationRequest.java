package com.pwc.assignment.registration;

import com.pwc.assignment.departments.Departments;
import com.pwc.assignment.users.UsersRole;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {

    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;
    private final UsersRole userRole;
    private final Long departmentsId;

}
