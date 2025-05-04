package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import com.example.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import javax.sql.DataSource;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

    @Autowired
    private DataSource dataSource;

    @Bean
    public JdbcUserDetailsManager users(@Autowired DataSource dataSource) {
        String usersByUsernameQuery = "select username,password,true from users where username = ?";
        String authoritiesByUsernameQuery = "select username, 'ROLE_USER' from users where username = ?";

        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        jdbcUserDetailsManager.setUsersByUsernameQuery(usersByUsernameQuery);
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(authoritiesByUsernameQuery);
        return jdbcUserDetailsManager;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //@Bean
    //public InMemoryUserDetailsManager userDetailsService() {
    //    UserDetails user = org.springframework.security.core.userdetails.User.withDefaultPasswordEncoder()
    //            .username("user")
    //            .password("password")
    //            .roles("USER")
    //            .build();
    //    return new InMemoryUserDetailsManager(user);
    //}

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(users(this.dataSource));
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authenticationProvider(authenticationProvider())
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/login", "/showRegister", "/register", "/blogger/**").permitAll()
                        .requestMatchers("/static/**", "/favicon.ico", "/**").permitAll() // Allow access to static resources
                        .requestMatchers("/continue", "/").hasRole("USER")
                        .requestMatchers("/posts", "/post/*").permitAll())
                .formLogin((form) -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/", true)
                        .permitAll()
                )
                .logout((logout) -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll());
        return http.build();
    }
}
