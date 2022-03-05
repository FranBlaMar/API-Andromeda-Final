package com.example.demo.config;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.example.demo.service.UsuarioService;
import com.example.demo.security.FiltroJWT;

/**
 * Clase para configurar jwt
 * @author Usuario
 *
 */
@Configuration
@EnableWebSecurity
public class AutenticacionConfig extends WebSecurityConfigurerAdapter {

    @Autowired private FiltroJWT filtro;
    @Autowired private UsuarioService servicio;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .httpBasic().disable()
                .cors()
                .and()
                .authorizeHttpRequests()
                .antMatchers("/auth/**").permitAll()
                .antMatchers("/noticia/**").permitAll()
                .antMatchers("/wiki/**").permitAll()
                .antMatchers("/post/**").hasRole("USER")
                .antMatchers("/user/**").hasRole("USER")
                .and()
                .userDetailsService(servicio)
                .exceptionHandling()
                    .authenticationEntryPoint(
                            (request, response, authException) ->
                            
                                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized")
                    )
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(filtro, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}