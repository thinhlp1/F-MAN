package com.poly.fman.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.poly.fman.dto.model.UserDTO2;
import com.poly.fman.entity.AuthenticationProvider;
import com.poly.fman.entity.Brand;
import com.poly.fman.entity.Role;
import com.poly.fman.entity.User;
import com.poly.fman.entity.Voucher;
import com.poly.fman.repository.RoleRepository;
import com.poly.fman.repository.UserRepository;
import com.poly.fman.service.common.EmailService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
    private ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    {
        new ModelMapper();
    }

    public String userIsExisted(String username, String email, String numberPhone) {
        User user = this.getUserByUsernameAndActiveIsTrue(username);
        if (user != null) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return "usernameIsExisted";
            } else if (user.getEmail().equalsIgnoreCase(email)) {
                return "emailIsExisted";
            } else if (user.getNumberPhone().equalsIgnoreCase(numberPhone)) {
                return "numberPhoneIsExisted";
            }
        }
        return null;
    }
    public User userIsExistedUser(String username, String email, String numberPhone) {
        User user = this.getUserByUsernameAndActiveIsTrue(username);
        if (user != null) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return user;
            } else if (user.getEmail().equalsIgnoreCase(email)) {
                return user;
            } else if (user.getNumberPhone().equalsIgnoreCase(numberPhone)) {
                return user;
            }
        }
        return null;
    }

      public List<User> getAllActive() {
        return userRepository.findAllByActiveIsTrue().orElse(null);
    }

    public boolean existUserById(int id) {
        return userRepository.existsById(id);
    }



      public boolean exstUserByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
    public boolean exstUserByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean exstUserByPhoneNumber(String phoneNumber) {
        return userRepository.existsByEmail(phoneNumber);
    }





    public User getUser(String username) {
        User user = userRepository.findByUsernameAndActiveIsTrue(username).orElse(null);
        return user;
    }

    public User getUserByIdAndActiveTrue(int id) {
        User user = userRepository.findByIdAndActiveIsTrue(id);
        return user;
    }

    public UserDTO2 getUserDtoById(int id) {
        User user = userRepository.findByIdAndActiveIsTrue(id);
        UserDTO2 userDTO = modelMapper.map(user, UserDTO2.class);
        userDTO.setRoleId(user.getRole().getId());
        return userDTO;
    }

    public User getUserByUsernameAndActiveIsTrue(String username) {
        return userRepository.findByUsernameAndActiveIsTrue(username).orElse(null);
    }

    public Page<User> getListUser(Pageable pageable) {
        Page<User> page = userRepository.findAllByActiveIsTrue(pageable);
        return page;
    }

    public UserDTO2 getUserDTO(String username) {
        User user = userRepository.findByUsernameAndActiveIsTrue(username).orElse(null);
        UserDTO2 userDTO = modelMapper.map(user, UserDTO2.class);
        userDTO.setRoleId(user.getRole().getId());
        return userDTO;
    }

    public User getUserDtoById(int id, UserDTO2 userDTO) {
        User user = userRepository.findByIdAndActiveIsTrue(id);
        user.setName(userDTO.getName());
        // user.setEmail(userDTO.getEmail());
        user.setNumberPhone(userDTO.getNumberPhone());
        // user.setImage(userDTO.getImage());
        // userDTO = modelMapper.map(user, UserDTO.class);
        // userDTO.setRoleId(user.getRole().getId());
        return user;
    }

    public UserDTO2 getUserDtoByIdForUpdateImage(int id, UserDTO2 userDTO) {
        User user = userRepository.findByIdAndActiveIsTrue(id);
        user.setImage(userDTO.getImage());
        userDTO = modelMapper.map(user, UserDTO2.class);
        // userDTO.setRoleId(user.getRole().getId());
        return userDTO;
    }

    public User create(UserDTO2 userDTO) {
        String roleInput = userDTO.getRoleId();
        Role role = roleRepository.findById(roleInput).get();
        userDTO.setRole(role);
        User user = modelMapper.map(userDTO, User.class);
        user.setAuthenticationProvider(AuthenticationProvider.LOCAL);
        // user.setUsername(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
      
        return userRepository.save(user);
    }

    public User update(UserDTO2 userDTO, String username) {
        User user = this.getUser(username);
        user.setName(userDTO.getName());
        String roleID = userDTO.getRoleId();
        Role role = roleRepository.findById(roleID).get();
        userDTO.setRole(role);
        user.setRole(userDTO.getRole());
        user.setImage(userDTO.getImage());
        user.setActive(userDTO.isActive());
        user.setUpdateAt(new Date());
        return userRepository.save(user);
    }

    public User updateByUser(User user) {
        return userRepository.save(user);
    }

    public User updateImageByUser(UserDTO2 userDTO) {
        User user = this.getUser(userDTO.getUsername());
        user.setImage(userDTO.getImage());
        return userRepository.save(user);
    }

    public User resetPassword(UserDTO2 userDTO, String username) {
        User user = this.getUser(username);
        String newPassword = "000000";
        user.setPassword(newPassword);
        System.out.println("====> Đây là email của user: " + user.getEmail());
        String messBodyString = "Mật khẩu của bạn là " + newPassword + "\nVui lòng cập nhật khẩu lại sớm nhất";
        emailService.sendEmail(user.getEmail(), "Mật khẩu mới", messBodyString);
        return userRepository.save(user);
    }

    public User delete(String username) {
        User user = this.getUser(username);
        user.setActive(false);
        user.setDeleteAt(new Date());
        return userRepository.save(user);
    }




    
}
