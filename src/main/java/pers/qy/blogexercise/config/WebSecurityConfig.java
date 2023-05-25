package pers.qy.blogexercise.config;


import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig{
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

}
