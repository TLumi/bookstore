package fi.Lumilahti_palvelinohjelmointi.Bookstore;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import fi.Lumilahti_palvelinohjelmointi.Bookstore.web.UserDetailServiceImpl;



@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity


public class WebSecurityConfig  extends WebSecurityConfigurerAdapter{
	@Autowired
	private UserDetailServiceImpl userDetailsService;
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests().antMatchers("/css/**").permitAll() // pääsy kaikille
		.and()
		.authorizeRequests().anyRequest().authenticated()  //pääsy kirjautuneena
		.and()
	.formLogin()	
		.loginPage("/login")
		.defaultSuccessUrl("/booklist")  //minne ohjautuu sisäänkirjautuessa
		.permitAll()	//kirjautumissivu sallittu kaikille
		.and()
	.logout()
		.permitAll();
	}


	 @Autowired
	    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	    }
	}

	
	
	
