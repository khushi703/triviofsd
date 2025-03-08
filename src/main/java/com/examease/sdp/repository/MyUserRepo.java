package com.examease.sdp.repository;

import com.examease.sdp.model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface MyUserRepo extends JpaRepository<MyUser, Long> {

    Optional<MyUser> findByEmail(String email);  // Changed from findByUsername to findByEmail
    Optional<MyUser> findByVerificationToken(String email);
}
