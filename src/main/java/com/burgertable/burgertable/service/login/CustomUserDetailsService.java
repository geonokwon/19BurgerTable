package com.burgertable.burgertable.service.login;



import com.burgertable.burgertable.Security.CustomUserDetails;
import com.burgertable.burgertable.entity.UserEntity;
import com.burgertable.burgertable.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        log.info("loadUserByUsername");
        UserEntity userData = userRepository.findByUserId(userId);
        if (userData != null) {
            return new CustomUserDetails(userData);
        }
        return null;
    }
}
