package dev.jlkesh.stackoverflowdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.thymeleaf.extras.springsecurity6.dialect.SpringSecurityDialect;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    public static final String[] WHITE_LIST = {
            "/auth/login/",
            "/auth/logout/",
    };

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("123")
                .roles("USER")
                .build();

        UserDetails manager = User.withDefaultPasswordEncoder()
                .username("manager")
                .password("123")
                .roles("MANAGER")
                .build();

        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("123")
                .roles("MANAGER", "ADMIN")
                .build();


        return new InMemoryUserDetailsManager(user, manager, admin);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests()
                .requestMatchers(WHITE_LIST)
                .permitAll()
                .anyRequest()
                .authenticated();

        http.formLogin()
                .loginPage("/auth/login/")
                .usernameParameter("uname")
                .passwordParameter("pswd")
                .loginProcessingUrl("/auth/login/")
                .defaultSuccessUrl("/", false)
                .failureHandler((request, response, exception) -> response.sendError(401, exception.getMessage()));

        http.rememberMe()
                .key("SQWEFG$@TFQEWGRY#%HTG&J#*$^&EUTHGFBVCNBHDJUKRR&E^UW$Y%T$EGRTHERJ^U&Er")
                .rememberMeParameter("remember-me")
                .tokenValiditySeconds(10 * 86400);

        http.logout()
                .logoutUrl("/auth/logout/")
                .logoutSuccessUrl("/auth/login/")
                .clearAuthentication(true)
                .deleteCookies("JSESSIONID", "remember-me");

        return http.build();
    }

    @Bean
    public SpringSecurityDialect securityDialect() {
        return new SpringSecurityDialect();
    }
}
