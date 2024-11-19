package com.example.hulk_store_backend.service.Implementation;

import com.example.hulk_store_backend.dto.UserDTO;
import com.example.hulk_store_backend.model.User;
import com.example.hulk_store_backend.repository.UserRepository;
import com.example.hulk_store_backend.service.Interfaces.IUserService;
import lombok.Getter;
import lombok.Setter;
import org.jasypt.util.text.AES256TextEncryptor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Getter
@Setter
public class UserService implements IUserService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AES256TextEncryptor textEncryptor;

    @Override
    public List<UserDTO> findAll() {
        return this.userRepository.findAll().stream().map(entity -> {
            // Decrypt credit card
            String decryptedCreditCard = this.textEncryptor.decrypt(entity.getCreditCard());
            entity.setCreditCard(decryptedCreditCard);
            return this.modelMapper.map(entity, UserDTO.class);
        }).toList();
    }

    @Override
    public UserDTO findById(Long id) {
        User user = this.userRepository.findById(id).orElseGet(() -> null);
        if (user == null) return null;

        // Decrypt credit card
        String decryptedCreditCard = this.textEncryptor.decrypt(user.getCreditCard());
        user.setCreditCard(decryptedCreditCard);
        return this.modelMapper.map(user, UserDTO.class);
    }

    @Override
    public UserDTO save(UserDTO userDTO) {
        User user = this.modelMapper.map(userDTO, User.class);
        // Encrypt password
        String encryptedPassword = this.passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        // Encrypt credit card
        String encryptedCreditCard = this.textEncryptor.encrypt(user.getCreditCard());
        user.setCreditCard(encryptedCreditCard);
        return this.modelMapper.map(this.userRepository.save(user), UserDTO.class);
    }

    @Override
    public UserDTO update(Long id, UserDTO userDTO) {
        User user = this.userRepository.findById(id).orElseGet(() -> null);
        if (user == null) return null;
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setCreditCard(userDTO.getCreditCard());
        // Encrypt password
        String encryptedPassword = this.passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        // Encrypt credit card
        String encryptedCreditCard = this.textEncryptor.encrypt(user.getCreditCard());
        user.setCreditCard(encryptedCreditCard);
        return this.modelMapper.map(this.userRepository.save(user), UserDTO.class);
    }

    @Override
    public String destroy(Long id) {
        User user = this.userRepository.findById(id).orElseGet(() -> null);
        if (user == null) return "User not found";
        this.userRepository.delete(user);
        return "User deleted successfully";
    }
}
