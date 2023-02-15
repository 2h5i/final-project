package com.sparta.finalproject.user.repository;

import com.sparta.finalproject.user.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByUserId(String userId);
  Optional<User> findByKakaoId(Long id);
  Optional<User> findByEmail(String email);
}
