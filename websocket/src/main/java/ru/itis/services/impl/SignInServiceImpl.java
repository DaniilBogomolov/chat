package ru.itis.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.dto.SignInDto;
import ru.itis.models.User;
import ru.itis.repositories.UserRepository;
import ru.itis.services.SignInService;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
public class SignInServiceImpl implements SignInService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public String signIn(SignInDto signInDto) {
        String name = signInDto.getName();
        Optional<User> userCandidate = userRepository.findUserByName(name);
        if (userCandidate.isPresent()) {
            User user = userCandidate.get();
            if (passwordEncoder.matches(signInDto.getPassword(), user.getPassword())) {
                String newCookieValue = UUID.randomUUID().toString();
                user.setToken(newCookieValue);
                userRepository.update(user);
                return newCookieValue;
            } throw new IllegalArgumentException("Username/password are incorrect");
        } throw new IllegalArgumentException("No user found!");
    }
}
