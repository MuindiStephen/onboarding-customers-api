package com.stevemd.onboarding.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/** @SecurityConfig
 * Configure security settings for a web application
 * Now, we define security configurations for our HTTP requests.
 */
    @Configuration
    @EnableWebSecurity
    @EnableMethodSecurity
    @Deprecated
    public class SecurityConfig extends WebSecurityConfigurerAdapter {
       // Securely storing passwords
        @Bean
        public BCryptPasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Bean
        public AuthenticationManager authenticationManager(
                AuthenticationConfiguration configuration) throws Exception {
            return configuration.getAuthenticationManager();
        }

        // To configure security rules
        @Override
        protected void configure(HttpSecurity httpSecurity) throws Exception {
            httpSecurity
                    .csrf().disable()  // disables cross site request forgery
                    .authorizeRequests()
                    .antMatchers("/register","/login").permitAll()
                    .antMatchers("/api/**").authenticated()
                    .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        }
    }



