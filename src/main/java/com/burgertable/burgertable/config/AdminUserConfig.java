package com.burgertable.burgertable.config;

import com.burgertable.burgertable.domain.UserRole;
import com.burgertable.burgertable.entity.UserEntity;
import com.burgertable.burgertable.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class AdminUserConfig {

    @Bean
    public CommandLineRunner createAdminUser(UserRepository userRepository) {
        return args -> {
            if (userRepository.findByUserId("admin") == null) {
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                String password = encoder.encode("admin123"); // yourAdminPassword 암호화된 비밀번호를 설정
                UserEntity userAdmin = new UserEntity();
                userAdmin.setName("임민규");
                userAdmin.setUserId("admin");
                userAdmin.setUserPass(password);
                userAdmin.setUserPhone("010-1111-1111");
                userAdmin.setUserRole(UserRole.ROLE_ADMIN);
                userRepository.save(userAdmin);
            }
        };
    }
}
