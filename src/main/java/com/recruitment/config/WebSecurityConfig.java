package com.recruitment.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

/*    @Bean
    @SuppressWarnings("deprecation") // For demo purpose
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() throws Exception {
        return new InMemoryUserDetailsManager(
                User.withDefaultPasswordEncoder()
                        .username("test-user")
                        .password(System.getenv("SPRING_SECURITY_USER_PASSWORD"))
                        .roles("ADMIN", "USER")
                        .build());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(new CorsFilter(), ChannelProcessingFilter.class);
        http
                .authorizeRequests()
                .antMatchers("/login")
                .permitAll()
                .anyRequest()
                .fullyAuthenticated()
                .and()
                .httpBasic()
                .and().csrf().disable();
    }*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().

                authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/**").permitAll().anyRequest().authenticated()
                .and().httpBasic();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("javainuse").password("{noop}password").roles("USER");
    }

}



