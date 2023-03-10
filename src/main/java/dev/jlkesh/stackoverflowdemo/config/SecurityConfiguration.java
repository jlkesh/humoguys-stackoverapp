package dev.jlkesh.stackoverflowdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true)
public class SecurityConfiguration {

    public static final String[] WHITE_LIST = {
            "/auth/login/",
            "/auth/register/",
            "/auth/logout/",
            "/css/**",
            "/js/**"
    };

    /*@Bean
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
    }*/

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
                .defaultSuccessUrl("/", false);

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

}
