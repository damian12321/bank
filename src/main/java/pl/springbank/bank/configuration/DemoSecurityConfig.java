
package pl.springbank.bank.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {


        auth.inMemoryAuthentication()
                .withUser("admin").password("{noop}admin").roles("ADMIN").and().
                withUser("customer").password("{noop}customer").roles("CUSTOMER");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {


        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/customer").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/account").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/transaction").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/customer/").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/account/").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/transaction/").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/customer/**").hasAnyRole("ADMIN,CUSTOMER")
                .antMatchers(HttpMethod.GET, "/api/account/**").hasAnyRole("ADMIN,CUSTOMER")
                .antMatchers(HttpMethod.GET, "/api/transaction/**").hasAnyRole("ADMIN,CUSTOMER")
                .antMatchers(HttpMethod.GET, "/api/transfer/**").hasAnyRole("ADMIN,CUSTOMER")
                .antMatchers(HttpMethod.POST, "/api/**").hasAnyRole("ADMIN,CUSTOMER")
                .antMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMIN")
                .and()
                .httpBasic()
                .and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);


    }

}