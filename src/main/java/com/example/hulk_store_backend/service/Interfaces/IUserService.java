package com.example.hulk_store_backend.service.Interfaces;

import com.example.hulk_store_backend.dto.UserDTO;
import com.example.hulk_store_backend.model.User;

import java.util.List;

public interface IUserService {
    List<UserDTO> findAll();
    UserDTO findById(Long id);
    UserDTO save(UserDTO user);
    UserDTO update(Long id, UserDTO user);
    String destroy(Long id);
}
