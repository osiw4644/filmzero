package com.zeromovie.filmzero.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    com.zeromovie.filmzero.service.MemberService memberService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.formLogin()
                .loginPage("/member/login")            // 사용자 정의 로그인 페이지
                .defaultSuccessUrl("/main")            // 로그인 성공시
                .usernameParameter("email")                // 아이디 파라미터명 설정
                //.successHandler(new MyLoginSuccessHandler())
                .failureUrl("/member/login/error") // 로그인 실패 후 이동 페이지
                .and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                .logoutSuccessUrl("/main");    // 로그아웃 성공 후 핸들러

        http.authorizeRequests()
                .mvcMatchers("/", "/member/**", "/item/**", "/image/**","/images/**", "/login/**", "/main/**", //인가 api
                        "/movie/**","/event/**","/notice/**")   //주소에 이런식으로 표현되는 경로에 대해서는 모든 유저에 대하여 권한을 주겠다.
                .permitAll().mvcMatchers("/admin/**").hasRole("ADMIN")  //주소에 이런식으로 표현되는 경로에 대해서는 관리자에 대하여만 권한을 주겠다.
                .anyRequest().authenticated();


        http.exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint());// 인증되지 않은 사용자가 접근할 경우 수행되는 작업 인증api
    }


    @Override
    public void configure(WebSecurity web) throws Exception{
        web.ignoring().antMatchers("/css/**", "/js/**", "/image/**","/image/main/collection/**");
        // static 디렉토리 하위 파일에 대해 인증 없이 접근 가능하게 설정
    }
    @Bean
    public PasswordEncoder passwordEncoder() {  // 라이브러리 의존성 주입한것뿐임 만든것 아님
        return new BCryptPasswordEncoder();
    }

    @Override   //비밀번호 인코딩
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(memberService).passwordEncoder(passwordEncoder());
    }
}

