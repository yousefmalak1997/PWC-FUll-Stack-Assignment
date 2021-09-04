package com.pwc.assignment.users;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class UsersRequest {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private UsersRole userRole;
    private Long departmentsId;
}
