package com.example.microservicedemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.savedrequest.NullRequestCache;
import org.springframework.security.web.session.HttpSessionEventPublisher;

@Configuration
@EnableWebSecurity
public class BasicSecurityConfiguration extends WebSecurityConfigurerAdapter {
    public static final String API_PREFIX = "/api";

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder encoder =
                PasswordEncoderFactories.createDelegatingPasswordEncoder();

        auth
                .inMemoryAuthentication()
                .withUser("user")
                .password(encoder.encode("password"))
                .roles("USER")
                .and()
                .withUser("admin")
                .password(encoder.encode("admin"))
                .roles("USER", "ADMIN");
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity
//                .authorizeRequests()
//                .antMatchers("/actuator").anonymous()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .httpBasic();
//        httpSecurity
//                .authorizeRequests()
//                .antMatchers("/login*").permitAll()
//                .antMatchers("/**").permitAll()
//                .antMatchers("/graphql", "/graphql/**").permitAll()
//                .antMatchers("/graphiql", "/vendor", "/vendor/**", "/actuator", "/actuator/**").permitAll()
//                .anyRequest().authenticated();

//        httpSecurity.authorizeRequests().anyRequest().anonymous()
//        httpSecurity.authorizeRequests().antMatchers("/").permitAll();

        httpSecurity
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/graphql", "/h2-console").permitAll()
                .and()
                .requestCache()
                .requestCache(new NullRequestCache())
                .and()
                .headers()
                .frameOptions().sameOrigin() // needed for H2 web console
                .and()
                .sessionManagement()
                .maximumSessions(1)
                .maxSessionsPreventsLogin(true)
                .sessionRegistry(sessionRegistry());
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }
}
