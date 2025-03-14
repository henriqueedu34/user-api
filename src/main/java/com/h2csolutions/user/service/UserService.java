package com.h2csolutions.user.service;

import com.h2csolutions.user.Dto.UserRequestDto;
import com.h2csolutions.user.Dto.UserResponseDto;
import com.h2csolutions.user.model.User;
import com.h2csolutions.user.repository.UserRepository;
import com.h2csolutions.user.status.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        User user = new User();
        user.setName(userRequestDto.getName());
        user.setUsername(userRequestDto.getUsername());
       // user.setPasswordCription(passwordEncoder.encode(userRequestDto.getPasswordCription())); // Senha criptografada
        user.setPassword(userRequestDto.getPassword());
        user.setEmail(userRequestDto.getEmail());
        user.setPhoneNumber(userRequestDto.getPhoneNumber());
        user.setAddress(userRequestDto.getAddress());
        user.setActive(true);
        user.setConfirmationToken(userRequestDto.getConfirmationToken());
        user.setStatus(Status.ACTIVE); // Definição do status inicial

        user = userRepository.save(user);
        return mapToDTO(user);  // Retorna UserResponseDto
    }

    private UserResponseDto mapToDTO(User user) {
        UserResponseDto dto = new UserResponseDto(user);
        dto.setName(user.getName());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setPhoneNumber(user.getPhoneNumber());
        dto.setAddress(user.getAddress());
        dto.setActive(user.isActive());
        dto.setStatus(user.getStatus()); // Adicionando status ao DTO
        return dto;
    }

    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public UserResponseDto getUser(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("Usuário não encontrado");
        }
        return mapToDTO(user);
    }

    public UserResponseDto deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (user.getStatus() == Status.DELETED) {
            throw new RuntimeException("Usuário já foi deletado anteriormente.");
        }

        user.setStatus(Status.DELETED);  // Atualiza o status para DELETED
        user.setActive(false);  // Define o usuário como inativo

        userRepository.save(user); // Salva a alteração no banco

        return mapToDTO(user);
    }
}
