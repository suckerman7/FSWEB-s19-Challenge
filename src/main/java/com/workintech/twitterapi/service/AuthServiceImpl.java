package com.workintech.twitterapi.service;

import com.workintech.twitterapi.dto.LoginRequestDTO;
import com.workintech.twitterapi.dto.RegisterRequestDTO;
import com.workintech.twitterapi.entity.Role;
import com.workintech.twitterapi.entity.User;
import com.workintech.twitterapi.exception.badrequest.BadRequestException;
import com.workintech.twitterapi.exception.notfound.UserNotFoundException;
import com.workintech.twitterapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public void register(RegisterRequestDTO request) {

        if(userRepository.existsByUsername(request.getUsername())) {
            throw new BadRequestException("Bu kullanıcı adı zaten kullanılıyor!");
        }

        if(userRepository.existsByEmail(request.getEmail())) {
            throw new BadRequestException("Bu email zaten kullanılıyor!");
        }

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(user);
    }

    @Override
    public String login(LoginRequestDTO request) {

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UserNotFoundException("Kullanıcı bulunamadı!"));

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadRequestException("Şifre yanlış!");
        }

        return jwtService.generateToken(user);
    }
}
