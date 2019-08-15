package com.gy.security.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity //启动过滤器链
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password("123")
                .roles("ADMIN")
                .and()
                .withUser("user")
                .password("123")
                .roles("USER");
        //"USER_ADD","USER_EDIT","USER_DELETE"
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/toAdd").hasRole("ADMIN")
                .antMatchers("/add").hasRole("ADMIN")
                .antMatchers("/toEdit").hasRole("ADMIN")
                .antMatchers("/edit").hasRole("ADMIN")
                .antMatchers("/delete").hasRole("ADMIN")
                .antMatchers("/userlogin").permitAll()
                .antMatchers("/**")
                .fullyAuthenticated()
                .and()
                .formLogin().loginPage("/userlogin").loginProcessingUrl("/")
                .and()
                .csrf()//跨站访问过多加上配置
                .disable();
    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        //解决静态资源被拦截的问题
        web.ignoring().antMatchers("/css/**");
    }
    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }
}
