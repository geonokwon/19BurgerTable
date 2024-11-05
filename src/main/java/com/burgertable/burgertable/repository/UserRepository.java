package com.burgertable.burgertable.repository;


import com.burgertable.burgertable.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUserId(String userId);
    UserEntity findByName(String userName);
}
