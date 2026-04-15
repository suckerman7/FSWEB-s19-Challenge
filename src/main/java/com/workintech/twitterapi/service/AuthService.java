package com.workintech.twitterapi.service;

import com.workintech.twitterapi.dto.LoginRequestDTO;
import com.workintech.twitterapi.dto.RegisterRequestDTO;

public interface AuthService {

    void register(RegisterRequestDTO request);

    String login(LoginRequestDTO request);
}
