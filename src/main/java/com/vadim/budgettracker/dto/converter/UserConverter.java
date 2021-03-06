package com.vadim.budgettracker.dto.converter;

import com.vadim.budgettracker.dto.CategoryDTO;
import com.vadim.budgettracker.dto.OperationDTO;
import com.vadim.budgettracker.dto.UserDTO;
import com.vadim.budgettracker.entity.User;
import com.vadim.budgettracker.entity.enums.Role;
import com.vadim.budgettracker.model.RegistrationRequestDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserConverter {

    private final CategoryConverter categoryConverter;
    private final OperationConverter operationConverter;

    public UserConverter(CategoryConverter categoryConverter, OperationConverter operationConverter) {
        this.categoryConverter = categoryConverter;
        this.operationConverter = operationConverter;
    }

    public User convertToEntity(UserDTO userDTO) {
        final Long id = userDTO.getId();
        final String nickname = userDTO.getNickname();
        final String email = userDTO.getEmail();
        final String password = userDTO.getPassword();
        final Role role = userDTO.getRole();
        final Boolean confirmed = userDTO.getConfirmed();
        final LocalDate createdDate = userDTO.getCreatedDate();
        final String mode = userDTO.getMode();
        final String currency = userDTO.getCurrency();
        final String language = userDTO.getLanguage();
        return User.builder()
                .id(id)
                .nickname(nickname)
                .role(role)
                .password(password)
                .email(email)
                .confirmed(confirmed)
                .createdDate(createdDate)
                .language(language)
                .mode(mode)
                .currency(currency)
                .build();
    }

    public UserDTO convertToDTO(User user) {
        final Long id = user.getId();
        final String email = user.getEmail();
        final String nickname = user.getNickname();
        final String password = user.getPassword();
        final Role role = user.getRole();
        final Boolean confirmed = user.isConfirmed();
        final String mode = user.getMode();
        final String currency = user.getCurrency();
        final String language = user.getLanguage();
        final LocalDate createdDate = user.getCreatedDate();

       // final List<CategoryDTO> categoryDTOS = user.getCategories().stream()
       //         .map(categoryConverter::convertToDTO).collect(Collectors.toList());

      //  final List<OperationDTO> operationDTOS = user.getOperations().stream()
      //          .map(operationConverter::convertToDTO).collect(Collectors.toList());

        return UserDTO.builder()
                .id(id)
                .role(role)
                .nickname(nickname)
                .password(password)
                .email(email)
                .confirmed(confirmed)
                .createdDate(createdDate)
                .mode(mode)
                .currency(currency)
                .language(language)
             //   .categories(categoryDTOS)
             //   .operations(operationDTOS)
                .build();
    }

    public User convertToEntity(RegistrationRequestDTO requestDTO) {
        final String nickname = requestDTO.getNickname();
        final String email = requestDTO.getEmail();
        final String password = requestDTO.getPassword();
        return User.builder()
                .nickname(nickname)
                .email(email)
                .password(password)
                .role(Role.USER)
                .confirmed(false)
                .createdDate(LocalDate.now())
                .mode("light")
                .language("en")
                .currency("$")
                .build();
    }
}
