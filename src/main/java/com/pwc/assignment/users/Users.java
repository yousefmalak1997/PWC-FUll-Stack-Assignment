package com.pwc.assignment.users;

import com.fasterxml.jackson.annotation.JsonView;
import com.pwc.assignment.departments.Departments;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class Users implements UserDetails {


    @Id
    @SequenceGenerator(
            name = "users_sequence",
            sequenceName = "users_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "users_sequence"
    )
    private Long id;
    @Column(nullable = false, length = 45)
    private String firstName;
    @Column(nullable = false, length = 45)
    private String lastName;
    @Column(nullable = false, unique = true, length = 45)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UsersRole userRole;
    private Boolean locked = false;
    private Boolean enabled = true;
//    @ManyToOne
//    @JoinColumn(name="department_id", nullable=false)
//    private Departments departments;
//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "department_id", nullable = false)
//    private Departments departments;
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "department_id")
//    @NotNull()
    private Long departmentsId;

    public Users(String firstName, String lastName, String email, String password, UsersRole userRole,Long departments) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.userRole = userRole;
        this.departmentsId = departments;
    }

//    public Users(String firstName, String lastName, String email, String password, UsersRole userRole, Boolean locked, Boolean enabled, Departments departments) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.email = email;
//        this.password = password;
//        this.userRole = userRole;
//        this.locked = locked;
//        this.enabled = enabled;
//        this.departments = departments;
//    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority auth = new SimpleGrantedAuthority(userRole.name());
        return Collections.singletonList(auth);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
