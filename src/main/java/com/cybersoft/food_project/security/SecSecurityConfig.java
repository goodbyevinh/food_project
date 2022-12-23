package com.cybersoft.food_project.security;

import com.cybersoft.food_project.jwt.JwtTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
public class SecSecurityConfig {

    @Autowired
    JwtTokenFilter jwtTokenFilter;
    // Dùng để khởi tạo danh sách user cứng và danh sách user này sẽ được lưu trữ ở RAM(memory)
//    @Bean
//    public InMemoryUserDetailsManager userDetailsService() {
//        UserDetails user1 = User.withUserDetails("cybersoft")
//                .password(passwordEncoder().encode("123"))
//                .roles("USER")
//                .build();
//        UserDetails user2 = User.withUserDetails("admin")
//                .password(passwordEncoder().encode("123"))
//                .roles("ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(user1, user2);
//    }
    // Quy định các rule liên quan tới bảo mật và quyền truy cập

    @Bean
    public FilterRegistrationBean corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://127.0.0.1:5500");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(0);
        return bean;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // http builder configurations for authorize requests and form login (see below)
    /*
        *antMatchers: Định nghĩa link cần xác thực
        * authenticated : bắt buộc phải chứng thực(đăng nhập) link chỉ định ở antMatchers
        * permitALL: cho phép truy cập full quyền vào link chỉ định ở antMatchers
        * anyRequest: toàn  bộ request gọi vào API
    */
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/auth/signin").permitAll()
                .antMatchers("/auth/signup").permitAll()
                .antMatchers("/test/login").permitAll()
                .antMatchers("/refresh-token").permitAll()
                .antMatchers("/file/**").permitAll()
                .antMatchers("/category/*").permitAll()
                .antMatchers("/restaurant/**").permitAll()
                .antMatchers("/food/**").permitAll()
                .antMatchers("/category/**").permitAll()
                .antMatchers("/user/**").permitAll()
                .antMatchers("/images/*").permitAll()
                .antMatchers("/signin/test").authenticated()
                .anyRequest().authenticated();

        /*
        Thêm filter trước 1 filter nào đó
         */
        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();

    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    CustomAuthenticationProvider customAuthenticationProvider;
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(customAuthenticationProvider);
        return authenticationManagerBuilder.build();
    }
}
