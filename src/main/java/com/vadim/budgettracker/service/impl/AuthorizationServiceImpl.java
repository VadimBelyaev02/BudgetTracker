package com.vadim.budgettracker.service.impl;

import com.vadim.budgettracker.dao.UserDAO;
import com.vadim.budgettracker.entity.User;
import com.vadim.budgettracker.exception.AccessDeniedException;
import com.vadim.budgettracker.exception.NotFoundException;
import com.vadim.budgettracker.model.AuthorizationRequestDTO;
import com.vadim.budgettracker.security.jwt.JwtToken;
import com.vadim.budgettracker.security.jwt.JwtTokenProvider;
import com.vadim.budgettracker.service.AuthorizationService;
import io.jsonwebtoken.Jwt;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {

    private final AuthenticationManager authenticationManager;
    private final UserDAO userDAO;
    private final JwtTokenProvider tokenProvider;

    public AuthorizationServiceImpl(AuthenticationManager authenticationManager, UserDAO userDAO, JwtTokenProvider tokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userDAO = userDAO;
        this.tokenProvider = tokenProvider;
    }

    @Override
    public JwtToken authorize(AuthorizationRequestDTO requestDTO) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    requestDTO.getEmail(), requestDTO.getPassword()));

            User user = userDAO.findByEmail(requestDTO.getEmail()).orElseThrow(() -> {
                throw new NotFoundException("User is not found");
            });
            if (!user.getConfirmed()) {
                throw new AccessDeniedException("User is not confirmed");
            }

            String token = tokenProvider.createToken(requestDTO.getEmail(), user.getRole().name());
            return new JwtToken(token);
        } catch (AuthenticationException e) {
            throw new AccessDeniedException("Invalid email or password", e);
        }

    }
}