package edu.depaul.cdm.se452.group4.minuteTrak.config;


import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    // Enable CORS and disable CSRF
    http.cors().and().csrf().disable().httpBasic().disable();

    // Set session management to stateless
    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)//
        .and();

    // Set permissions on endpoints
    http.authorizeRequests()
        // Endpoints without auth
        .antMatchers("/**").permitAll() // ***TODO - Update when adding jwt authentication filter
        // Endpoints with auth
        .anyRequest().authenticated();

  }

}
