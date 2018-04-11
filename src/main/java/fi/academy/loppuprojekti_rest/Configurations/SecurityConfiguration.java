package fi.academy.loppuprojekti_rest.Configurations;

import fi.academy.loppuprojekti_rest.Security.CustomUserDetailsService;
import fi.academy.loppuprojekti_rest.Security.JwtAuthenticationEntryPoint;
import fi.academy.loppuprojekti_rest.Security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public StrictHttpFirewall httpFirewall() {
        StrictHttpFirewall firewall = new StrictHttpFirewall();
        firewall.setAllowSemicolon(true);
        return firewall;
    }

   /* @Override
    public void configure(WebSecurity web) throws Exception {
        web.httpFirewall(StrictHttpFirewall);
    }*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http

            /*    .authorizeRequests()
                .antMatchers("/", "/registration").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();*/

            .cors()
                .and()
            .csrf()
                .disable()
            .exceptionHandling()
                .authenticationEntryPoint(unauthorizedHandler)
                .and()
            .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
            .authorizeRequests()
                .antMatchers("/",
                        "/favicon.ico",
                        "/**/*.png",
                        "/**/*.gif",
                        "/**/*.svg",
                        "/**/*.jpg",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js")
                .permitAll()
//                .antMatchers("/api/auth/**")
//                .permitAll()
                .antMatchers("/travelapp/login", "/travelapp/signup")
                .permitAll()
//                .antMatchers(HttpMethod.GET, "/api/polls/**", "/api/users/**")
//                .permitAll()
                .anyRequest()
                .authenticated();
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

             //   .antMatchers("/").permitAll()
/*                .antMatchers("/api/auth/**").permitAll()

                .antMatchers(HttpMethod.POST, "/travelapp/login").permitAll()
                .antMatchers(HttpMethod.GET, "/travelapp/login").permitAll()
                .antMatchers(HttpMethod.POST, "/travelapp/signup").permitAll()
                .antMatchers("/travelapp/admin/**").hasAuthority("ADMIN")
                .anyRequest().authenticated().and().csrf().disable()
                .formLogin()
                .loginPage("/travelapp/login").failureUrl("/travelapp/login?error=true")
//                .defaultSuccessUrl("/travelapp/home")
                .usernameParameter("username")
                .passwordParameter("password")
                .and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/travelapp/logout"))
                .logoutSuccessUrl("/").and().exceptionHandling()
                .accessDeniedPage("/access-denied");*/
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder());
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
