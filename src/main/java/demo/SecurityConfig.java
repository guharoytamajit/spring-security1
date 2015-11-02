package demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, jsr250Enabled = true)
// @EnableWebSecurity ie. overwriting spring boot security
public class SecurityConfig {

	@Autowired
	@Qualifier("myUserDetailsService")
	UserDetailsService userDetailsService;

	// @formatter:off
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)
			throws Exception {
		// <authentication-manager>
		// <authentication-provider user-service-ref="myUserDetailsService" >
		// <password-encoder hash="bcrypt" />
		// </authentication-provider>
		// </authentication-manager>
		auth
		.userDetailsService(userDetailsService)
		.passwordEncoder(passwordEncoder())
		;
	}

	@Configuration
	@Order(1) 
	public static class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
	protected void configure(HttpSecurity http) throws Exception {
	http
	 .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
	.antMatcher("/api/**") 
	.authorizeRequests()
	.anyRequest().access("isAuthenticated()")
	.and()
	.httpBasic()
	.and().csrf().disable();
	}
	}
	@Configuration 
	public static class FormLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	http
	.authorizeRequests()
	.anyRequest().authenticated()
	.and()
	.formLogin()

	;
	}
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		
		System.out.println("password for password:: "+encoder.encode("password"));
		return encoder;
	}
	// @formatter:on
}
