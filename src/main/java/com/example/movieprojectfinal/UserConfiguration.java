
package com.example.movieprojectfinal;

import com.example.movieprojectfinal.Aop.TimeTraceAop;
import com.example.movieprojectfinal.repository.UserRepository;
import com.example.movieprojectfinal.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class UserConfiguration {
    private final UserRepository userRepository;

    public UserConfiguration(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public UserService userService(UserRepository userRepository) {
        return new UserService(userRepository);
    }

    @Bean
    public TimeTraceAop timeTraceAop() {
        return new TimeTraceAop();
    }
}
