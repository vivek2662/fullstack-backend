/**
 * 
 */
package venkat.cars.designer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import venkat.cars.designer.service.SignupService;

/**
 * @author VenkataraoArrepu
 *
 */

//@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private SignupService signupService;
	
	@Autowired
	protected void  configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(signupService).passwordEncoder(passwordEncoder());

	}
//	 @Bean
//	  public DaoAuthenticationProvider authenticationProvider() {
//	      DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//	       
//	      authProvider.setUserDetailsService(signupService);
//	      authProvider.setPasswordEncoder(passwordEncoder());
//	   
//	      return authProvider;
//	  }
//	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
//	 @Bean
//	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		http.csrf().disable();
//		return null;
//		
//	}
	
//	 @Bean
//	  public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
//	    return authConfig.getAuthenticationManager();
//	  }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
	}

	 @Bean
	 @Override
	 public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();	 
	 }
	

}
