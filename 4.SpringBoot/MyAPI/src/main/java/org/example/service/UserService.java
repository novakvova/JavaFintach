package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.config.security.JwtService;
import org.example.dto.user.UserAuthDto;
import org.example.dto.user.UserRegisterDto;
import org.example.entities.UserEntity;
import org.example.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    // Реєстрація нового користувача
    public void registerUser(UserRegisterDto dto) {
        if (userRepository.existsByUsername(dto.getUsername())) {
            throw new RuntimeException("Користувач з таким ім'ям вже існує");
        }
        var userEntity = new UserEntity();
        userEntity.setUsername(dto.getUsername());
        userEntity.setPassword(passwordEncoder.encode(dto.getPassword()));
        userRepository.save(userEntity);
    }

    // Аутентифікація користувача
    public String authenticateUser(UserAuthDto userEntity) {
        UserEntity foundUser = userRepository.findByUsername(userEntity.getUsername())
                .orElseThrow(() -> new RuntimeException("Користувач не знайдений"));

        if (!passwordEncoder.matches(userEntity.getPassword(), foundUser.getPassword())) {
            throw new RuntimeException("Невірний пароль");
        }

        // Генерація JWT токену
        return jwtService.generateAccessToken(foundUser);
    }
}