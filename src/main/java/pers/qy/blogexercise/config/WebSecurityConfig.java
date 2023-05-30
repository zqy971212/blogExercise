package pers.qy.blogexercise.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import pers.qy.blogexercise.model.entity.Account;
import pers.qy.blogexercise.service.AccountService;

import java.util.List;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig{
    private static UserDetailsManager manager = null;

    @Autowired
    AccountService accountService;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.formLogin(login -> login
                .loginPage("/admin/login")
                .defaultSuccessUrl("/admin/blog/all",true)
                .usernameParameter("userEmail") //リクエストパラメータのname属性を明示
                .passwordParameter("password")
                .failureUrl("/admin/login?error")
                .permitAll()
        ).logout(logout -> logout
                .logoutSuccessUrl("/admin/login")
        ).authorizeHttpRequests(authz -> authz
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .requestMatchers("/blog/**","/admin/register","/css/**", "/js/**","/blog-image/**","/images/**").permitAll()
                .anyRequest().authenticated()
        );
        return http.build();
    }

    public static void addUser(String userEmail, String password) {
        manager.createUser(User.withDefaultPasswordEncoder()
                .username(userEmail)
                .password(password)
                .roles("USER")
                .build());
    }


    @Bean
    public UserDetailsService userDetailsService() {
        List<UserDetails> users = accountService.getAllAccounts().stream().map(
                account -> User.withDefaultPasswordEncoder()
                        .username(account.getUserEmail())
                        .password(account.getPassword())
                        .roles("USER")
                        .build()
        ).toList();

        manager = new InMemoryUserDetailsManager(users);
        return manager;
    }
}
