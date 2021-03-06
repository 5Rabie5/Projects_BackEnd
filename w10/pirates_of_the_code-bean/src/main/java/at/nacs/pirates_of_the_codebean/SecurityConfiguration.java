package at.nacs.pirates_of_the_codebean;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .authorizeRequests()
        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
        .antMatchers("/").permitAll()
        .antMatchers("/diary").hasRole("CARINA")
        .anyRequest().fullyAuthenticated()
        .and()
        .formLogin().loginPage("/login").failureUrl("/login?error").permitAll()
        .and()
        .logout().permitAll();

  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    auth
        .inMemoryAuthentication()
        .withUser("jack")
        .password(encoder.encode("jack"))
        .roles("JACK")
        .and()
        .withUser("carina")
        .password(encoder.encode("carina"))
        .roles("CARINA")
    ;
  }
}