package com.workintech.twitterapi.service;

import com.workintech.twitterapi.dto.request.LoginRequestDTO;
import com.workintech.twitterapi.dto.request.RegisterRequestDTO;

public interface AuthService {

    void register(RegisterRequestDTO request);

    String login(LoginRequestDTO request);
}
