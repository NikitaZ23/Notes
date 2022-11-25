package com.example.notes.config;

//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("user")
//                .password("123")
//                .roles("ADMIN");
//    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests()
//                .antMatchers("/admin").hasRole("ADMIN")
//                .antMatchers("/users").hasRole("ADMIN")
//                .antMatchers("/users/**").hasRole("ADMIN")
//                .antMatchers("/user").hasAnyRole("ADMIN", "USER")
//                .antMatchers("/hello").permitAll()
//                .and().formLogin();
//    }
//}

