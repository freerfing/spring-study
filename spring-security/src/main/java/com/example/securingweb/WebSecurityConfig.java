package com.example.securingweb;

import com.example.securingweb.config.MyUserDetailsServiceImpl;
import com.example.securingweb.config.filter.KaptchaVerifyFilter;
import com.example.securingweb.kaptcha.config.KaptchaWebAuthenticationDetailSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 详细说明
 *
 * @Author: 周卫鹏
 * @Creator: 周卫鹏
 * @CreateDate: 2021年05月15日
 * @Description: 类简洁说明
 * @UpdateUser: 周卫鹏
 * @UpdateDate: 2021年05月15日
 * @UpdateRemark: 修改备注
 * @Version: [v1.0]
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationProvider provider;

    @Autowired
    private KaptchaWebAuthenticationDetailSource source;

    @Autowired
    private MyUserDetailsServiceImpl userDetailsService;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/home", "/kaptcha.jpg").permitAll()
                .antMatchers("/api/user/getUser").hasAuthority("GET_USER")
                .antMatchers("/api/user/addUser").hasAuthority("ADD_USER")
                .anyRequest().authenticated()
                .and().formLogin().authenticationDetailsSource(source).loginPage("/login").permitAll()
                .and().logout().permitAll();
        http.sessionManagement().sessionFixation().none();
        // http.addFilterBefore(new KaptchaVerifyFilter(), UsernamePasswordAuthenticationFilter.class);
//        http.authorizeRequests((requests) -> {
//            ((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl) requests.anyRequest()).authenticated();
//        });
//        http.formLogin();
//        http.httpBasic();
    }

    /*
    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user);
    }
    */

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        //auth.userDetailsService(userDetailsService)
            auth.authenticationProvider(provider);
        //this.disableLocalConfigureAuthenticationBldr = true;
        //auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder());
//        auth.userDetailsService(userDetailsService).and()
//                .inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
//                .withUser("guanyu").password(new BCryptPasswordEncoder().encode("password")).authorities("GET_USER")
//                .and()
//                .withUser("zhangfei").password(new BCryptPasswordEncoder().encode("password")).authorities("ADD_USER")
//                .and()
//                .withUser("zhaoyun").password(new BCryptPasswordEncoder().encode("password")).authorities("GET_USER", "ADD_USER");
    }
}
