package com.projpba.projpba.Repository;

import com.projpba.projpba.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserEmail(String userEmail);

    public User findByResetPasswordToken(String token);

    @Query(value = "SELECT first_name FROM users WHERE user_email = :email", nativeQuery = true)
    String findNameByEmail(@Param("email") String email);
}