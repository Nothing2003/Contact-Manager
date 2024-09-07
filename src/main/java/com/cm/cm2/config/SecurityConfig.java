package com.cm.cm2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.cm.cm2.helper.Message;
import com.cm.cm2.helper.MessageType;
import com.cm.cm2.services.imp.SCUDS;

import jakarta.servlet.http.HttpSession;

@Configuration
public class SecurityConfig {

    @Autowired
    public SCUDS scuds;
    @Autowired
    private OAuthAuthenicationScuccessHandler oAuthAuthenicationScuccessHandler;

    @Bean
    public AuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(scuds);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.authorizeHttpRequests(authorize -> {
            authorize.requestMatchers("/user/**").authenticated();
            authorize.anyRequest().permitAll();
        });
        httpSecurity.formLogin((formLogin) -> {
            formLogin.loginPage("/login");
            formLogin.loginProcessingUrl("/authenticate");
            formLogin.defaultSuccessUrl("/user/profile");
            formLogin.usernameParameter("email");
            formLogin.passwordParameter("password");
            formLogin.failureHandler((request, response, exception) -> {
                if (exception instanceof DisabledException) {
                    HttpSession session = request.getSession();
                    session.setAttribute("message",
                            Message.builder().contant("User is disabled. Please check your mailbox. ").type(MessageType.red).build());
                    response.sendRedirect("/login");
                } else {
                    response.sendRedirect("/login?error=true");

                }
            });

        });

        httpSecurity.logout(logoutCustomizer -> {
            logoutCustomizer.logoutUrl("/logout");
            logoutCustomizer.logoutSuccessUrl("/login?logout==true");
        });
        httpSecurity.oauth2Login(oauth2LoginCustomizer -> {
            oauth2LoginCustomizer.loginPage("/login");
            oauth2LoginCustomizer.successHandler(oAuthAuthenicationScuccessHandler);
        }
        );

        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
