package ru.itis.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.dto.SignUpDto;
import ru.itis.models.User;
import ru.itis.repositories.UserRepository;
import ru.itis.services.SignUpService;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public String signUp(SignUpDto signUpDto) {
        String name = signUpDto.getName();
        if (!userRepository.findUserByName(name).isPresent()) {
            User user = User.builder()
                    .name(name)
                    .password(passwordEncoder.encode(signUpDto.getPassword()))
                    .token(UUID.randomUUID().toString())
                    .build();
            userRepository.save(user);
            return user.getToken();
        } throw new IllegalArgumentException("Username: " + name + " is already is use");
    }
}
