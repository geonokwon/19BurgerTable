package com.burgertable.burgertable.config;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Slf4j
@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    //BCrypt 암호화 설정
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //MIME type 에러
    //정적리소스는 시큐리트 접근권한에서 제외한다.
    @Bean
    public WebSecurityCustomizer configure() {
        return web -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        //접근 권한에 대한 설정 부분
        http.authorizeHttpRequests((auth) -> auth
                .requestMatchers("/", "/login/**").permitAll()
                .requestMatchers("/sales/**").hasAnyRole("ADMIN", "USER") //여기에 로그인 된사람만 할수있는 페이지 추가
                .anyRequest().authenticated()
        );

        //권한이 없는 페이지에 접근할 때 설정
        http.exceptionHandling((auth) -> auth
                .accessDeniedHandler((request, response, accessDeniedException) -> {
                    //사용자가 권한이 없을 때 main 페이지로 리다이렉트
                    response.sendRedirect("/");
                })
        );

        //로그인 페이지 관련 설정
        http.formLogin((auth) -> auth.loginPage("/login")
                .loginProcessingUrl("/login/loginCheck") //로그인 체크 시 시큐리티가 자동으로 사용자 인증을 처리하는 경로
                .usernameParameter("userId") //userName 으로 인식하기 때문에 id 의 파라미터 값을 userId 로 변경
                .passwordParameter("userPassword") //password 으로 인식하기 때문에 password 의 파라미터 값을 userPassword 로 변경
                .defaultSuccessUrl("/")
                .permitAll()
        );



        //로그아웃 설정
        http.logout((auth) -> auth
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID", "remember-me")
                .clearAuthentication(true)
                .permitAll()
        );

        //csrf 토큰 관련 설정(지금은 disable 로 비활성화)
        http.csrf((auth) -> auth.disable());

        return http.build();
    }
}
