package com.example.gfg.libraryapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityCustomConfig extends WebSecurityConfigurerAdapter   {

    @Autowired
    UserService userService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //auth.inMemoryAuthentication()
        auth.userDetailsService(userService);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                csrf().disable()
                .httpBasic().and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/student/create").permitAll()
                .antMatchers("/student/**").hasAuthority("student")
                .antMatchers("/transaction/**").hasAuthority("student")
                .antMatchers(HttpMethod.GET,"/book/**").hasAnyAuthority("student","admin")
                .antMatchers("/book/**").hasAuthority("admin")
                .antMatchers("/**").permitAll()
                .and().formLogin();
    }

    @Bean
    public PasswordEncoder getPE(){
        return new BCryptPasswordEncoder();
    }
}
