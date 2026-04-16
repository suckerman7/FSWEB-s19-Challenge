package com.workintech.twitterapi;

import com.workintech.twitterapi.dto.request.RegisterRequestDTO;
import com.workintech.twitterapi.entity.User;
import com.workintech.twitterapi.repository.UserRepository;
import com.workintech.twitterapi.service.AuthServiceImpl;
import com.workintech.twitterapi.service.JwtService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @InjectMocks
    private AuthServiceImpl authService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @Test
    void shouldRegisterUserSuccessfully() {

        RegisterRequestDTO dto = new RegisterRequestDTO();
        dto.setUsername("test");
        dto.setEmail("test@test.com");
        dto.setPassword("1234");

        when(userRepository.existsByUsername("test")).thenReturn(false);
        when(userRepository.existsByEmail("test@test.com")).thenReturn(false);
        when(passwordEncoder.encode("1234")).thenReturn("encoded");

        authService.register(dto);

        verify(userRepository).save(any(User.class));
    }
}
