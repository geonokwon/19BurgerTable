package com.burgertable.burgertable.member;

import com.burgertable.burgertable.domain.UserRole;
import com.burgertable.burgertable.entity.UserEntity;
import com.burgertable.burgertable.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Slf4j
@SpringBootTest
public class Join {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Test
    //회원가입 프로세스
    public void joinProcess(){

        UserEntity userEntity = new UserEntity();

        userEntity.setUserId("admin");
        //단방향 해쉬 암호화 spring Security
        userEntity.setUserPass(bCryptPasswordEncoder.encode("admin123"));
        userEntity.setName("임민규");
        userEntity.setUserPhone("000-0000-0000");
        userEntity.setUserRole(UserRole.ROLE_ADMIN);

        //저장된 유저 반환
        UserEntity saveUser = userRepository.save(userEntity);
        if (saveUser != null){
            log.info("saveUser {}", saveUser);
        }
        else {
            log.info("saveUser fail");
        }

    }

}
