package com.example.becoderapi.persistance.services;

import com.example.becoderapi.model.dto.auth.AuthRequest;
import com.example.becoderapi.model.dto.auth.AuthResponse;

public interface AuthenticService {
    AuthResponse register(AuthRequest request);
    AuthResponse login(AuthRequest request);
}
