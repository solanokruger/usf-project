package uol.compass.project.usf.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    public SecurityFilterChain  filterChain(HttpSecurity httpSecurity){
        httpSecurity.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/doctor").permitAll();
    }

}
