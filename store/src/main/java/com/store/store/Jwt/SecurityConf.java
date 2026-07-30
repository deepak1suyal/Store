package com.store.store.Jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties.Http;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.store.store.Controller.Customerror;

@Configuration

public class SecurityConf {

    @Autowired
    Customerror customerror;
@Autowired JwtAuthenticationFilter jwtAuthFilter;

@Autowired AuthenticationProvider authenticationProvider;
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpsecurity) throws Exception
    {
httpsecurity.csrf(csrf->csrf.disable());
httpsecurity.sessionManagement(sessionconfig->sessionconfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
httpsecurity.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
httpsecurity.authenticationProvider(authenticationProvider);
httpsecurity.authorizeHttpRequests(auth->{auth.requestMatchers("/api/Login", "/api/getallemp").permitAll().requestMatchers( "/api/addemp","/api/updateemp", "/api/deleteemp")
.hasAuthority("Admin").requestMatchers("/api/userinfo").hasAnyAuthority("User").anyRequest().authenticated();});
httpsecurity.exceptionHandling(ex->ex.authenticationEntryPoint(customerror));
return httpsecurity.build();
    }
}
