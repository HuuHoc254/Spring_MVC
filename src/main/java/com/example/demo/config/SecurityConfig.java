package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)  throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> {
                        auth.requestMatchers("/admin/**").hasAnyRole("ADMIN");
                        auth.requestMatchers("/login*").permitAll();
                        auth.anyRequest().authenticated(); }
                )
                .formLogin(formLogin -> formLogin
					                		.loginPage("/login")
					                		.usernameParameter("accountName")
					                		.passwordParameter("password")
					                		.defaultSuccessUrl("/product")
         		).logout(logout -> logout
                        .logoutUrl("/logout") // URL để thực hiện logout
                        .logoutSuccessUrl("/login?logout") // URL sau khi logout thành công
                        .invalidateHttpSession(true) // Xóa session sau khi logout
                ).exceptionHandling(exceptionHandlingConfigurer -> 
                exceptionHandlingConfigurer.accessDeniedPage("/login/403")
            );
        return http.build();
    }
}