package com.status.configs;

import com.status.controller.ApiController;
import com.status.externalwebservice.SomeExternalServiceAuthenticator;
import com.status.security.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by anteastra on 21.10.2015.
 */
@Configuration
@EnableWebMvcSecurity
@EnableScheduling
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Value("${backend.admin.role}")
    private String backendAdminRole;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                csrf().disable().
                sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).
                and().
                authorizeRequests().
                antMatchers(actuatorEndpoints()).hasRole(backendAdminRole).
                //antMatchers("/index.html", "/home.html", "/login.html", "/", "/status").permitAll().
                        //antMatchers("/index.html", "/login.html", "/login", "/").permitAll().
                        antMatchers("/login.html", "/login").permitAll().
                and().
                formLogin().
                loginPage("/login.html").
                and().
                authorizeRequests().
                anyRequest().authenticated().
                and().
                //anonymous().disable().
                        exceptionHandling().authenticationEntryPoint(unauthorizedEntryPoint());

        http.addFilterBefore(new AuthenticationFilter(authenticationManager()), BasicAuthenticationFilter.class).
                addFilterBefore(new ManagementEndpointAuthenticationFilter(authenticationManager()), BasicAuthenticationFilter.class);
    }

    private String[] actuatorEndpoints() {
        return new String[]{ApiController.AUTOCONFIG_ENDPOINT, ApiController.BEANS_ENDPOINT, ApiController.CONFIGPROPS_ENDPOINT,
                ApiController.ENV_ENDPOINT, ApiController.MAPPINGS_ENDPOINT,
                ApiController.METRICS_ENDPOINT, ApiController.SHUTDOWN_ENDPOINT};
    }

    @Bean
    public AuthenticationEntryPoint unauthorizedEntryPoint() {
        return (request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(domainUsernamePasswordAuthenticationProvider()).
                authenticationProvider(backendAdminUsernamePasswordAuthenticationProvider()).
                authenticationProvider(tokenAuthenticationProvider());
    }

    @Bean
    public TokenService tokenService() {
        return new TokenService();
    }

    @Bean
    public ExternalServiceAuthenticator someExternalServiceAuthenticator() {
        return new SomeExternalServiceAuthenticator();
    }

    @Bean
    public AuthenticationProvider domainUsernamePasswordAuthenticationProvider() {
        return new DomainUsernamePasswordAuthenticationProvider(tokenService(), someExternalServiceAuthenticator());
    }

    @Bean
    public AuthenticationProvider backendAdminUsernamePasswordAuthenticationProvider() {
        return new BackendAdminUsernamePasswordAuthenticationProvider();
    }

    @Bean
    public AuthenticationProvider tokenAuthenticationProvider() {
        return new TokenAuthenticationProvider(tokenService());
    }
}