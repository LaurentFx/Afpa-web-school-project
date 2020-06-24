package com.afpa.cda.security.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(userDetailsService);
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public JwtAuthenticationTokenFilter authenticationTokenFilterBean() {
		return new JwtAuthenticationTokenFilter();
	}

	@SuppressWarnings("deprecation")
	@Bean
	public static NoOpPasswordEncoder passwordEncoder() {
		return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		String[] swaggerUrls = {"/v2/api-docs","/swagger-resources", "/swagger-resources/**",
				"/configuration/ui","/configuration/security", "/swagger-ui.html", "/webjars/**","/error"};

		String[] respUrls = { "/animation/purpose","/manifestation/add","role/*","/salle/add","/salle/update/*",
				"/salle/delete/*","/typesalle/show/*","typesalle/add","/typesalle/update/*","/typesalle/delete/*",
				"/users/list","/users/show/*","/users/add","/users/update/*","/users/delete/*"};

		String[] respAdminUrls = { "/animation/delete/*","/invitation/list","/invitation/update/*",
				"/manifestation/availability","/manifestation/update/","/manifestation/delete/*","/salle/capacity/*"};

		String[] adminUrls = {"/invitation/manifestation/*","/invitation/add","/invitation/delete/",
				"/invitation/deleteAll/*","/users/invites/*"};

		String[] vipUrls = {"/invitation/show/*","/invitation/user/*","/invitation/new/*","/invitation/answer/*"} ;

		String[] animUrls = {"/animation/add","/animation/update/*"};

		String[] clientUrls = {"/article/*","/panier/*","/reservation/*","/users/show/*"};

		String[] allUrls = {"/animation/show/*","/manifestation/show/*","/salle/show/*",
				"/typesalle/list","/users/role/*","/users/current"};

		String[] publicUrls =  {"/public/*","/users/new"};

		http.csrf().disable();
		http.headers().frameOptions().disable();
		//        http.headers().frameOptions().sameOrigin();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
		.authorizeRequests()
		.antMatchers(HttpMethod.OPTIONS).permitAll()

		.antMatchers(respUrls).hasAnyAuthority(new String[]{"RESP"})
		.antMatchers(respAdminUrls).hasAnyAuthority(new String[]{"RESP","ADMIN"})
		.antMatchers(adminUrls).hasAnyAuthority(new String[]{"ADMIN"})
		.antMatchers(vipUrls).hasAnyAuthority(new String[]{"VIP"})
		.antMatchers(animUrls).hasAnyAuthority(new String[]{"ANIM"})	
		.antMatchers(clientUrls).hasAnyAuthority(new String[]{"CLIENT"})
		.antMatchers(allUrls).authenticated()
		.antMatchers(publicUrls).permitAll()

		.antMatchers(swaggerUrls).permitAll()
		//	.antMatchers("/h2-console/**").permitAll()
		.antMatchers("/**").permitAll()
		.antMatchers("/*").permitAll()
		.anyRequest().permitAll();

		http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);

	}

}