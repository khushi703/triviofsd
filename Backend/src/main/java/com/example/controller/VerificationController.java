package com.example.controller;

import com.example.model.MyUser;
import com.example.repository.MyUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("api/auth/verify-email")
public class VerificationController {
    private static final Logger logger = LoggerFactory.getLogger(VerificationController.class);

    @Autowired
    private MyUserRepo myUserRepo;

    @GetMapping
    public ResponseEntity<String> verifyEmail(@RequestParam("token") String token) {
        logger.info("Received email verification request for token: {}", token);

        Optional<MyUser> userOptional = myUserRepo.findByVerificationToken(token);

        if (userOptional.isPresent()) {
            MyUser user = userOptional.get();
            if (user.isVerified()) {
                logger.warn("User with email {} is already verified.", user.getEmail());
                return ResponseEntity.badRequest().body("Your email is already verified.");
            }
            user.setVerified(true);
            user.setVerificationToken(null);
            myUserRepo.save(user);
            logger.info("User with email {} successfully verified.", user.getEmail());
            return ResponseEntity.ok("Email successfully verified!");
        } else {
            logger.error("Invalid or expired token: {}", token);
            return ResponseEntity.badRequest().body("Invalid or expired verification link!");
        }
    }
}
