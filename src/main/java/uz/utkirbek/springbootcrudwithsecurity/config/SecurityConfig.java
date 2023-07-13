package uz.utkirbek.springbootcrudwithsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Muallif: Utkirbek
 * Soat: 17:41:26
 * Kun: July 11, 2023, Tuesday
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth
                    .inMemoryAuthentication()
                    .withUser("director").password(passwordEncoder().encode("director")).roles("DIRECTOR").authorities("DELETE_PRODUCT","GET_PRODUCT","EDIT_PRODUCT")
                    .and()
                    .withUser("manager").password(passwordEncoder().encode("manager")).roles("MANAGER").authorities("GET_PRODUCT","ADD_PRODUCT")
                    .and()
                    .withUser("worker").password(passwordEncoder().encode("worker")).roles("WORKER").authorities("GET_PRODUCT");
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
