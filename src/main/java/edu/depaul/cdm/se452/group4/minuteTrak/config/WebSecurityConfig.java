package edu.depaul.cdm.se452.group4.minuteTrak.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.filter.CorsFilter;
import edu.depaul.cdm.se452.group4.minuteTrak.security.JwtAuthenticationFilter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private JwtAuthenticationFilter JwtAuthenticationFilter;

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
        .antMatchers("/auth/**", "/test/**").permitAll() //
        // Endpoints with auth
        .anyRequest().authenticated();

    // For each request, do CorsFilter, and then JwtAuthenticationFilter
    http.addFilterAfter(JwtAuthenticationFilter, CorsFilter.class);
  }

}
