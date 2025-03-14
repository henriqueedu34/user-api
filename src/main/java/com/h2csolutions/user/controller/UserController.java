package com.h2csolutions.user.controller;

import com.h2csolutions.user.Dto.UserRequestDto;
import com.h2csolutions.user.Dto.UserResponseDto;
import com.h2csolutions.user.service.UserService;
import com.h2csolutions.user.status.Status;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Validated  // Para validação dos DTOs, caso queira usar @NotBlank e outras anotações de validação
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody UserRequestDto userRequestDto) {
        UserResponseDto userResponseDto = userService.createUser(userRequestDto);
        return ResponseEntity.ok(userResponseDto);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        List<UserResponseDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserResponseDto> getUserByUsername(@PathVariable String username) {
        return ResponseEntity.ok(userService.getUser(username));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserResponseDto> deleteUser(@PathVariable Long id) {
        UserResponseDto userResponseDto = userService.deleteUser(id);
        if (userResponseDto != null && userResponseDto.getStatus() != Status.ACTIVE) {
            return ResponseEntity.ok(userResponseDto);  // Retorna o DTO do usuário desativado
        } else {
            return ResponseEntity.status(404).body(null);  // Retorna 404 se não encontrar o usuário
        }
    }

}

