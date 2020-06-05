 package com.cts.training.netflixzuulapigatewayserver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	
        auth.inMemoryAuthentication()
                .withUser("user")
                .password("{noop}user123")
                .roles("USER")
                .and()
                .withUser("test")
                .password("{noop}test123")
                .roles("USER")
                .and()
                .withUser("admin")
                .password("{noop}admin123")
                .roles("ADMIN");
    }    
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
      		.authorizeRequests()
      		.antMatchers("/healthcare-service/**").hasAnyRole("USER,ADMIN")
      		.antMatchers("/patient-service/**").hasRole("ADMIN")
      		.antMatchers("/physician-service/**").hasRole("ADMIN")
            .antMatchers("/").permitAll()
            .and().httpBasic();
    }
    
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowCredentials(true);
        corsConfig.addAllowedOrigin("*");
        corsConfig.addAllowedHeader("*");
        corsConfig.addAllowedMethod(HttpMethod.GET);
        corsConfig.addAllowedMethod(HttpMethod.POST);
        corsConfig.addAllowedMethod(HttpMethod.PUT);
        corsConfig.addAllowedMethod(HttpMethod.DELETE);
        source.registerCorsConfiguration("/**", corsConfig);
        return new CorsFilter(source);
    }
}