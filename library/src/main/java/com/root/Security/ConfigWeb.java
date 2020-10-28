package com.root.Security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.root.Security.Jwt.AuthEntryPointJwt;
import com.root.Security.Jwt.AuthTokenFilter;
import com.root.Security.UserDetail.UserDetailService;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
//@EnableWebSecurity
public class ConfigWeb extends WebSecurityConfigurerAdapter {


	@Autowired
	UserDetailService userDetailsService;

	@Autowired
	private AuthEntryPointJwt unauthorizedHandler;

	@Bean
	public AuthTokenFilter authenticationJwtTokenFilter() {
		return new AuthTokenFilter();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManagerBean();
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {


		http.cors().and().csrf().disable()
				.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()

				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

				.authorizeRequests()
					.antMatchers("/test").permitAll()
					.antMatchers("/api/user/**").permitAll()
					.antMatchers("/api/cv/**").permitAll()
//					.antMatchers("/api/random").hasRole("USER")
					.anyRequest().authenticated();

		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	}

}
//
//
//.authorizeRequests()
//.antMatchers("/api/auth/login").permitAll()               
//.antMatchers("/api/auth/random/**").hasRole("USER")                                      
//.antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')")            
//.anyRequest().authenticated()//http.csrf().disable().authorizeRequests()
////.antMatchers("/").permitAll()
//.antMatchers("/").permitAll() // Có nghĩa là request "/" ko cần phải đc xác thực
//.antMatchers(HttpMethod.POST, "/api/login").permitAll() // Request dạng POST tới "/login" luôn được phép truy cập dù là đã authenticated hay chưa
//.anyRequest().authenticated() // Các request còn lại đều cần được authenticated
//.and()
//// Add các filter vào ứng dụng của chúng ta, thứ mà sẽ hứng các request để xử lý trước khi tới các xử lý trong controllers.
//// Về thứ tự của các filter, các bạn tham khảo thêm tại http://docs.spring.io/spring-security/site/docs/3.0.x/reference/security-filter-chain.html mục 7.3 Filter Ordering
//.addFilterBefore(new JWTLoginFilter("/login", authenticationManager()), UsernamePasswordAuthenticationFilter.class) 
//.addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
////}