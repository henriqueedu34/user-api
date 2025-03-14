package com.h2csolutions.user.Dto;

import com.h2csolutions.user.model.User;
import com.h2csolutions.user.status.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {
    private String name;
    private String username;
    private String email;
    private String phoneNumber;
    private String address;
    private boolean isActive;
    private Status status;

    public UserResponseDto(User user) {
        this.name = user.getName();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
        this.address = user.getAddress();
        this.isActive = user.isActive();
        this.status = user.getStatus();
    }
}
