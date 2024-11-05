package com.burgertable.burgertable.utils;


import com.burgertable.burgertable.Security.CustomUserDetails;
import com.burgertable.burgertable.entity.UserEntity;
import com.burgertable.burgertable.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
public class SecurityUtil {
    private final UserRepository userRepository;

    //현재 사용자의 권한(Role) 중 특정 권한을 가지고 있는지?
    public static boolean hasRole(String role) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return false;
        }
        return authentication.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals(role));
    }

    //현재 사용자의 모든 권한(Role) 조회
    public static List<String> getUserRoles() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null) {
            return List.of(); //빈 리스트 반환
        }
        return authentication.getAuthorities().stream()
                .map(auth -> auth.getAuthority())
                .collect(Collectors.toList());
    }

    //현재 사용자의 UserNum 값 조회
    public static Long getUserNum() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return null;
        }
        Object principal = authentication.getPrincipal();
        if(principal instanceof CustomUserDetails) {
            return ((CustomUserDetails) principal).getUserNum();
        }
        return null;
    }

    //현재 사용자의 userName 값 조회
    public static String getUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return null;
        }
        Object principal = authentication.getPrincipal();
        if(principal instanceof CustomUserDetails) {
            return ((CustomUserDetails) principal).getName();
        }
        return null;
    }

    //유저의 권한이 변경되었을때 재로그인을 하지않고 최신 role 값을 불러오는 메서드
    public static boolean reloadUserRole(UserEntity user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        //일반 유저일때 롤값 최신화
        CustomUserDetails userDetails = new CustomUserDetails(user);
        Authentication newAuth = new UsernamePasswordAuthenticationToken
                (userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(newAuth);

        return true;
    }



}
