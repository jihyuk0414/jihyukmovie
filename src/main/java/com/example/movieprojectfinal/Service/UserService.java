package com.example.movieprojectfinal.service;

import com.example.movieprojectfinal.domain.User;
import com.example.movieprojectfinal.repository.UserRepository;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void register(User user) {
        this.userRepository.findByEmail(user.getEmail()).ifPresent((m) -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
        this.userRepository.save(user);
    }

    public Optional<User> finduser(String email) {
        return this.userRepository.findByEmail(email);
    }
}

