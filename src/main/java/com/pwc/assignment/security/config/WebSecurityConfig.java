package com.pwc.assignment.security.config;


import com.pwc.assignment.users.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UsersService usersService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    //this is to allow hitting the backend server from the ui port.
    //because the user interface port is on different port.
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // URls match for Access
                .antMatchers("/").permitAll()
                .antMatchers("/index").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/register").permitAll()
                .antMatchers("/create/account").permitAll()
                .antMatchers("/departments").permitAll()
                .antMatchers("/home/**").hasAnyAuthority("EMPLOYEE","MANAGER")
                .antMatchers("/user/delete/**").hasAnyAuthority("EMPLOYEE","MANAGER")
                .antMatchers("/user/**").hasAnyAuthority("EMPLOYEE","MANAGER")
                .antMatchers("/editUserDep/**").hasAnyAuthority("EMPLOYEE","MANAGER")
                .antMatchers("/viewUsers").hasAnyAuthority("MANAGER")
                .antMatchers("/departments/**").hasAnyAuthority("MANAGER")
                .antMatchers("/viewDep").hasAnyAuthority("MANAGER")
                .antMatchers("/projects/**").hasAnyAuthority("MANAGER","EMPLOYEE")
                .antMatchers("/viewProjs").hasAnyAuthority("MANAGER","EMPLOYEE")
                .antMatchers("/projects/createEdit/**").hasAnyAuthority("MANAGER","EMPLOYEE")
                .antMatchers("/projects/delete/**").hasAnyAuthority("MANAGER","EMPLOYEE")
                .antMatchers("/assignProject").hasAnyAuthority("MANAGER")
                .antMatchers("/viewAssignments").hasAnyAuthority("MANAGER","EMPLOYEE")
                .antMatchers("/user/editInfo").hasAnyAuthority("MANAGER","EMPLOYEE")
                .anyRequest().authenticated()
                .and()
                // form login
                .csrf().disable().formLogin()
                .loginPage("/login")
                .failureUrl("/login?error=true")
                .defaultSuccessUrl("/home")
                .usernameParameter("email")
                .passwordParameter("password")
                .and()
                // logout
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/").and()
                .exceptionHandling()
                .accessDeniedPage("/access-denied");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(usersService);
        return provider;
    }

}
