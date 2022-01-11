package com.vadim.budgettracker.dto.converter;

import com.vadim.budgettracker.dto.UserDTO;
import com.vadim.budgettracker.entity.User;
import com.vadim.budgettracker.entity.enums.Role;
import com.vadim.budgettracker.model.RegistrationRequestDTO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class UserConverter {

    public User convertToEntity(UserDTO userDTO) {
        final Long id = userDTO.getId();
        final String nickname = userDTO.getNickname();
        final String email = userDTO.getEmail();
        final String password = userDTO.getPassword();
        final Role role = userDTO.getRole();
        final Boolean confirmed = userDTO.getConfirmed();
        final LocalDate createdDate = userDTO.getCreatedDate();
        return User.builder()
                .id(id)
                .nickname(nickname)
                .role(role)
                .password(password)
                .email(email)
                .confirmed(confirmed)
                .createdDate(createdDate)
                .build();
    }

    public UserDTO convertToDTO(User user) {
        final Long id = user.getId();
        final String email = user.getEmail();
        final String nickname = user.getNickname();
        final String password = user.getPassword();
        final Role role = user.getRole();
        final Boolean confirmed = user.getConfirmed();
        final LocalDate createdDate = user.getCreatedDate();
        return UserDTO.builder()
                .id(id)
                .role(role)
                .nickname(nickname)
                .password(password)
                .email(email)
                .confirmed(confirmed)
                .createdDate(createdDate)
                .build();
    }

    public User convertToEntity(RegistrationRequestDTO requestDTO) {
        final String nickname = requestDTO.getNickname();
        final String email = requestDTO.getEmail();
        final String password = requestDTO.getPassword();
        final Role role = Role.USER;
        final LocalDate createdDate = LocalDate.now();
        return User.builder()
                .nickname(nickname)
                .email(email)
                .password(password)
                .role(role)
                .confirmed(false)
                .createdDate(createdDate)
                .build();
    }
}