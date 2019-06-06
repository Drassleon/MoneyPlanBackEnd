package pe.edu.upc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;

import pe.edu.upc.moneyplan.service.impl.UserSecService;

@Configuration
@EnableConfigurationProperties
@ComponentScan(basePackages = "pe.edu.upc")
public class SecurityConfig /*extends WebSecurityConfigurerAdapter*/ {
	
	  
	  UserSecService userDetailsService=new UserSecService();
	  
	  /*
	  @Autowired
		public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
		}
	   */
	  /*
	  @Bean
	  public PasswordEncoder passwordEncoder() {
		  PasswordEncoder encoder = new BCryptPasswordEncoder();
			return encoder;
			}
	  @Bean
	  public DaoAuthenticationProvider authProvider() {
	      DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	      authProvider.setUserDetailsService(userDetailsService);
	      authProvider.setPasswordEncoder(passwordEncoder());
	      return authProvider;
	  }
	  */
	  /*
	  @Override
	  protected void configure(HttpSecurity http) throws Exception {
	    http
	      .csrf().disable();
	  }
	  */
}
