package com.example.hulk_store_backend.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String role;
    private String creditCard;
}
