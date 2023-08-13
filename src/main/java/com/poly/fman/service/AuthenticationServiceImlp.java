package com.poly.fman.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.poly.fman.config.security.CustomOAuth2User;
import com.poly.fman.dto.auth.AuthResponseDTO;
import com.poly.fman.dto.auth.ChangeEmailDTO;
import com.poly.fman.dto.auth.ChangePassDTO;
import com.poly.fman.dto.auth.LoginDTO;
import com.poly.fman.dto.auth.RegisterDTO;
import com.poly.fman.entity.AuthenticationProvider;
import com.poly.fman.entity.Role;
import com.poly.fman.entity.User;
import com.poly.fman.interfaces.service.AuthenticationService;
import com.poly.fman.repository.UserRepository;
import com.poly.fman.service.common.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImlp implements AuthenticationService {

        private ModelMapper modelMapper = new ModelMapper();
        @Autowired
        private UserRepository userRepository;
        private final PasswordEncoder passwordEncoder;
        private final JwtService jwtService;
        private final AuthenticationManager authenticationManager;
        private Map<String, String> mapOtpCode = new HashMap<String, String>();

        @Override
        public AuthResponseDTO authenticate(LoginDTO loginDTO) {
                authenticationManager.authenticate(
                                new UsernamePasswordAuthenticationToken(
                                                loginDTO.getUsername(),
                                                loginDTO.getPassword()));

                User user = userRepository.findByUsernameAndActiveIsTrue(loginDTO.getUsername())
                                .orElseThrow();
                String jwtToken = jwtService.generateToken(user);
                return AuthResponseDTO.builder()
                                .accessToken(jwtToken)
                                .roleAccess(user.getRole().getName())
                                .username(user.getUsername())
                                .userId(user.getId())
                                .build();
        }


        public AuthResponseDTO getTokenUnlimited(LoginDTO loginDTO) {
                User user = userRepository.findByUsernameAndActiveIsTrue(loginDTO.getUsername())
                                .orElseThrow();
                String jwtToken = jwtService.generateTokenUnlimited(user);
                return AuthResponseDTO.builder()
                                .accessToken(jwtToken)
                                .roleAccess(user.getRole().getName())
                                .username(user.getUsername())
                                .build();
        }

        @Override
        public AuthResponseDTO register(RegisterDTO registerDTO) {
                User user = modelMapper.map(registerDTO, User.class);
                user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

                Role role = new Role();
                role.setId("USER");
                role.setName("User");
                user.setRole(role);

                user.setEmail(registerDTO.getUsername());
                user.setCreateAt(new Date());
                user.setActive(true);
                user.setAuthenticationProvider(AuthenticationProvider.LOCAL);

                System.out.println(user.toString());

                user = userRepository.save(user);
                String jwtToken = jwtService.generateToken(user);
                return AuthResponseDTO.builder()
                                .accessToken(jwtToken)
                                .roleAccess(user.getRole().getName())
                                .username(user.getUsername())
                                .userId(user.getId())
                                .build();

        }

        

        public boolean changePassword(String userName, String newPassword) {
                try {
                        User user = userRepository.findByUsernameAndActiveIsTrue(userName)
                                        .orElseThrow();
                        user.setPassword(passwordEncoder.encode(newPassword));
                        userRepository.save(user);
                        return true;
                } catch (Exception e) {
                        return false;
                }
        }

        public boolean changeEmail(String userName, String newEmail) {
                try {
                        User user = userRepository.findByUsernameAndActiveIsTrue(userName).orElseThrow();
                        user.setEmail(newEmail);
                        user.setUsername(newEmail);
                        userRepository.save(user);
                        return true;
                } catch (Exception e) {
                        return false;
                }
        }

        public boolean checkAccount(ChangePassDTO changePass) {
                try {
                        User user = userRepository.findByUsernameAndActiveIsTrue(changePass.getUsername())
                                        .orElseThrow();
                        if (passwordEncoder.matches(changePass.getOldPassword(), user.getPassword())) {
                                return true;
                        } else {
                                return false;
                        }
                } catch (Exception e) {
                        return false;
                }
        }

        

        public boolean checkNumberPhoneExit(String numberPhone) {
                return userRepository.findByNumberPhoneAndActiveIsTrue(numberPhone).isPresent();

        }

        public boolean checkEmailExit(String email) {
                return userRepository.findByEmailAndActiveIsTrue(email).isPresent();
        }

        public String createOtpCode(String email) {
                String otpCode = generateOtpCode();
                mapOtpCode.put(email, otpCode);
                return otpCode;
        }

        public void countDownAndClearOtpCode(String email) {
                Thread thread = new Thread(() -> {
                        try {
                                Thread.sleep(60000); // Đợi 60 giây
                                mapOtpCode.replace(email, ""); // Đặt giá trị của otpCode thành rỗng
                                System.out.println("delete " + mapOtpCode.get(email));
                        } catch (InterruptedException e) {
                                e.printStackTrace();
                        }
                });
                thread.start(); // Bắt đầu đếm ngược

        }

        public String generateOtpCode() {
                int otpLength = 6; // độ dài mã OTP
                String numbers = "0123456789"; // chuỗi chứa các ký tự sẽ được sử dụng để tạo mã OTP
                Random random = new Random();
                char[] otp = new char[otpLength];
                for (int i = 0; i < otpLength; i++) {
                        otp[i] = numbers.charAt(random.nextInt(numbers.length()));
                }
                return new String(otp);
        }

        // Hàm kiểm tra tính hợp lệ của mã OTP
        public boolean isValidOtpCode(String otp) {
                return otp.matches("[0-9]{6}");
        }

        public boolean otpExit(String email) {
                return mapOtpCode.get(email) == null ? false : true;
        }

        public boolean comfirmOTP(String email, String opt) {
                return mapOtpCode.get(email) == null ? false : mapOtpCode.get(email).equals(opt);
        }

}
