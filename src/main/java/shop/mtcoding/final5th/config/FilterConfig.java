package shop.mtcoding.final5th.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import shop.mtcoding.final5th.config.jwt.JwtAuthenticationFilter;
import shop.mtcoding.final5th.config.jwt.JwtAuthorizationFilter;
import shop.mtcoding.final5th.domain.user.UserRepository;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class FilterConfig {

    private final UserRepository userRepository;

    // IoC 등록 (서버 실행시)
    @Bean
    public FilterRegistrationBean<JwtAuthenticationFilter> jwtAuthenticationFilterRegister() {
        log.debug("디버그 : 인증 필터 등록");
        FilterRegistrationBean<JwtAuthenticationFilter> bean = new FilterRegistrationBean<>(
                new JwtAuthenticationFilter(userRepository));
        bean.addUrlPatterns("/login");
        bean.setOrder(1);
        return bean;
    }

    @Profile("dev")
    @Bean
    public FilterRegistrationBean<JwtAuthorizationFilter> jwtAuthorizationFilterRegister() {
        log.debug("디버그 : 인가 필터 등록");
        FilterRegistrationBean<JwtAuthorizationFilter> bean = new FilterRegistrationBean<>(
                new JwtAuthorizationFilter());
        bean.addUrlPatterns("/s/*");
        bean.setOrder(2);
        return bean;
    }
}
