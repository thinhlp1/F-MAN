package com.poly.fman.controller.auth;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poly.fman.dto.auth.AuthResponseDTO;
import com.poly.fman.dto.auth.ChangeEmailDTO;
import com.poly.fman.dto.auth.ChangePassDTO;
import com.poly.fman.dto.auth.ForgetDTO;
import com.poly.fman.dto.auth.LoginDTO;
import com.poly.fman.dto.auth.RegisterDTO;
import com.poly.fman.dto.reponse.SimpleReponseDTO;
import com.poly.fman.entity.User;
import com.poly.fman.service.AuthenticationServiceImlp;
import com.poly.fman.service.UserService;
import com.poly.fman.service.common.CookieService;
import com.poly.fman.service.common.EmailService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthenticationController {

    private final AuthenticationServiceImlp authenticationService;
    private final UserService userService;
    private final CookieService cookieService;
    private final EmailService emailService;
    private HttpSession session;
    private ModelMapper modelMapper = new ModelMapper();

    @GetMapping("/account")
    public String accountIndex() {
        return "user/view/account/account";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "user/view/account/login";
    }

    @GetMapping("/register")
    public String registerForm() {
        return "user/view/account/registration";
    }

    @GetMapping("/confirmOTP")
    public String confirmOTPForm() {
        return "user/view/account/confirmOTP";
    }

    @GetMapping("/change-password")
    public String changePasswordForm() {
        return "user/view/account/change-password";
    }

    @GetMapping("/change-email")
    public String changeEmailForm(Model model) {
        ChangeEmailDTO changeEmailDTO = new ChangeEmailDTO();
        session.setAttribute("ChangeEmailDto", changeEmailDTO);
        model.addAttribute("account", changeEmailDTO);
        return "user/view/account/change-email";
    }

    @GetMapping("/forget")
    public String forgetForm(Model model) {
        return "user/view/account/forget";
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<SimpleReponseDTO> login(@RequestBody LoginDTO loginDTO) {

        AuthResponseDTO authResponseDTO = new AuthResponseDTO();
        try {
            authResponseDTO = authenticationService.authenticate(loginDTO);
            if (loginDTO.isRemember()) {
                String unlimitedToken = authenticationService.getTokenUnlimited(loginDTO).getAccessToken();
                cookieService.add("token", unlimitedToken, -1);
                cookieService.add("userId", authResponseDTO.getUserId() + "", -1);
                cookieService.add("remember", "remember", -1);
                cookieService.add("username", authResponseDTO.getUsername(), -1);
                session.setAttribute("userId", authResponseDTO.getUserId());

                System.out.println("REMEMBER");
            } else {
                cookieService.add("token", authResponseDTO.getAccessToken(), 24);
                cookieService.add("userId", authResponseDTO.getUserId() + "", 24);
                cookieService.add("username", authResponseDTO.getUsername(), 24);
                cookieService.remove("remember");
                session.setAttribute("userId", authResponseDTO.getUserId());
                System.out.println("NOT REMEMBER");
            }
        } catch (Exception e) {

            return ResponseEntity.status(401).body(new SimpleReponseDTO("401", "Sai tên đăng nhập hoặc mật khẩu"));
        }

        return ResponseEntity.ok(authResponseDTO);
    }

    @PostMapping("/checkEmailExist")
    @ResponseBody
    public ResponseEntity checkEmail(@RequestBody String email) {

        if (authenticationService.checkEmailExit(email)) {
            System.out.println("EMAIL" + email);
            return ResponseEntity.status(409).body(new SimpleReponseDTO("409", "Email đã được sử dụng"));
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/checkNumberPhoneExist")
    @ResponseBody
    public ResponseEntity checkNumberPhone(@RequestBody String numberPhone) {
        if (authenticationService.checkNumberPhoneExit(numberPhone)) {
            return ResponseEntity.status(409).body(new SimpleReponseDTO("409", "Số điện thoại đã được sử dụng"));
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/check-register")
    public ResponseEntity<SimpleReponseDTO> checkRegister(@RequestBody @Valid RegisterDTO registerDTO,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(400).body(new SimpleReponseDTO("400", "Thông tin không hợp lệ"));
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<SimpleReponseDTO> register(@RequestBody @Valid RegisterDTO registerDTO,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(400).body(new SimpleReponseDTO("400", "Thông tin không hợp lệ"));
        }
        try {
            AuthResponseDTO authResponseDTO = authenticationService.register(registerDTO);
            return ResponseEntity.ok().body(authResponseDTO);
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(new SimpleReponseDTO("500", "Có lỗi xảy ra. Đăng ký không thành công"));
        }
    }

    @PostMapping("/change-password")
    @ResponseBody
    public ResponseEntity<SimpleReponseDTO> changePassword(@RequestBody @Valid ChangePassDTO changePassDTO,
            BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(400).body(new SimpleReponseDTO("400", "Thông tin không hợp lệ"));
        }

        changePassDTO.setUsername(cookieService.getValue("username"));

        if (authenticationService.checkAccount(changePassDTO)) {
            if (authenticationService.changePassword(changePassDTO.getUsername(), changePassDTO.getNewPassword())) {
                Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                if (auth != null) {
                    cookieService.remove("token");
                    cookieService.remove("userId");
                    cookieService.remove("username");
                    cookieService.remove("remember");
                    session.removeAttribute("userId");
                    new SecurityContextLogoutHandler().logout(request, response, auth);
                }
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.status(500)
                        .body(new SimpleReponseDTO("500", "Có lỗi xảy ra. Đổi mật không thành công"));
            }
        } else {
            return ResponseEntity.status(401).body(new SimpleReponseDTO("401", "Mật khẩu không đúng"));
        }

    }

    @PostMapping("/change-email")
    public String changeEmail(@ModelAttribute("account") @Valid ChangeEmailDTO changeEmailDTO,
            BindingResult bindingResult, Model model) {
        User user = userService.getUserByUsernameAndActiveIsTrue(cookieService.getValue("username"));

        if (changeEmailDTO.getNewEmail().equalsIgnoreCase(user.getEmail())) {
            model.addAttribute("err_message_email", "Email mới bị trùng với mail hiện tại");
            return "user/view/account/change-email";
        }
        if (bindingResult.hasErrors()) {
            return "user/view/account/change-email";
        }
        changeEmailDTO.setUsername(cookieService.getValue("username"));
        if (authenticationService.changeEmail(user.getId(), changeEmailDTO.getNewEmail())) {
            user = userService.getUserByUsernameAndActiveIsTrue(changeEmailDTO.getUsername());
            // model.addAttribute("user", user);
            // model.addAttribute("user2", user);
            // session.setAttribute("successEmail", true);
            // session.setAttribute("showEmail", "Đổi email thành công");
            // session.setAttribute("ChangeEmailDto", changeEmailDTO);

            return "redirect:/auth/logout";
            // model.addAttribute("showEmail", "Đổi email thành công");
            // return "user/view/user/profile";
            // return "redirect:/user/profile/" + user.getId();
        }
        model.addAttribute("err_message_email", "Đổi email không thành công");
        return "user/view/account/change-email";
    }

    @PostMapping("/forget")
    @ResponseBody
    public ResponseEntity<SimpleReponseDTO> forget(@RequestBody @Valid ForgetDTO forgetDTO,
            BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(400).body(new SimpleReponseDTO("400", "Thông tin không hợp lệ"));
        }

        boolean rs = authenticationService.changePassword(forgetDTO.getEmail(), forgetDTO.getNewPassword());
        if (rs) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null) {
                cookieService.remove("token");
                cookieService.remove("userId");
                cookieService.remove("username");
                cookieService.remove("remember");
                session.removeAttribute("userId");
            }
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(500)
                    .body(new SimpleReponseDTO("500", "Có lỗi xảy ra. Đổi mật khẩu không thành công"));
        }

    }

    @PostMapping("/sendConfirmOTP")
    @ResponseBody
    public ResponseEntity<SimpleReponseDTO> sendComfirmOTP(@RequestBody String email,
            @RequestParam(defaultValue = "true") boolean newEmail) {

        if (!newEmail) {
            if (!authenticationService.checkEmailExit(email)) {
                return ResponseEntity.status(409).body(new SimpleReponseDTO("409", "Email không tồn tại "));
            }

        }
        String otpCode = authenticationService.createOtpCode(email);
        String subject = "Mã OTP của bạn";
        String messBodyString = "<html>" + "<p>Mã OTP của bạn là<p>"
                + "<h3 style='padding: 10px 15px;background-color: #FFE6C7 ' >" + otpCode + "</h3>" + "</html>";
        System.out.println("OTP CODE: " + otpCode);
        boolean rs = emailService.sendEmail(email, subject, messBodyString);
        if (rs) {
            authenticationService.countDownAndClearOtpCode(email);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(500).body(new SimpleReponseDTO("500", "Có lỗi xảy ra khi gửi mã xác nhận"));
        }
    }

    @GetMapping("/sendComfirmOTPChangeEmail")
    public String sendComfirmOTPChangeEmail(Model model) {
        User user = this.userService.getUserByUsernameAndActiveIsTrue(cookieService.getValue("username"));
        String email = user.getEmail();
        // model.addAttribute("desUri", "ChangeEmail");
        String otpCode = authenticationService.createOtpCode(email);
        String subject = "Mã OTP của bạn";
        String messBodyString = "Mã OTP của bạn là: " + otpCode;
        System.out.println("OTP CODE: " + otpCode);
        boolean rs = emailService.sendEmail(email, subject, messBodyString);
        if (rs) {
            authenticationService.countDownAndClearOtpCode(email);
            model.addAttribute("email", email);
        } else {
            model.addAttribute("err_message_email", "Có lỗi xảy ra khi gửi mã xác nhận");
            return "user/view/account/confirmOtpEmail";
        }
        return "user/view/account/confirmOtpEmail";
    }

    @PostMapping("/confirmOTP")
    @ResponseBody
    public ResponseEntity<SimpleReponseDTO> confirmOTPRegister(@RequestParam("otp") String otp,
            @RequestParam("email") String email) {

        // if (authenticationService.isValidOtpCode(otp)){
        // System.out.println("OTP Không đúng định dạng vậy");
        // model.addAttribute("err_message", "Mã OTP không hợp lệ");
        // return "user/view/account/confirmOTP";
        // }

        if (!authenticationService.otpExit(email)) {
            return ResponseEntity.status(401).body(new SimpleReponseDTO("401", "Mã OTP không hợp lệ"));
        }

        boolean isValid = authenticationService.comfirmOTP(email, otp);
        if (isValid) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(401).body(new SimpleReponseDTO("401", "Mã OTP không hợp lệ"));
        }

    }

    @PostMapping("/confirmOTPForget")
    public String comfirmForgetOTP(@RequestParam("otp") String otp, Model model) {

        otp = otp.substring(7);
        ForgetDTO forgetDTO = (ForgetDTO) session.getAttribute("account");
        String emailForget = forgetDTO.getEmail();
        System.out.println("EMAIL CONFIRM: " + emailForget);
        model.addAttribute("desUri", "Forget");

        // if (authenticationService.isValidOtpCode(otp)){
        // System.out.println("OTP Không đúng định dạng vậy");
        // model.addAttribute("err_message", "Mã OTP không hợp lệ");
        // return "user/view/account/confirmOTP";
        // }

        if (!authenticationService.otpExit(emailForget)) {
            model.addAttribute("err_message", "Mã OTP không còn tồn tại");
            return "user/view/account/confirmOTP";
        }

        boolean isValid = authenticationService.comfirmOTP(emailForget, otp);
        if (isValid) {
            model.addAttribute("account", forgetDTO);
            model.addAttribute("showEmail", false);
            return "user/view/account/forget";
        } else {
            model.addAttribute("err_message", "Mã OTP không đúng");
            return "user/view/account/confirmOTP";

        }

    }

    @PostMapping("/confirmOTPChangeEmail")
    public String comfirmChangeEmailOTP(@RequestParam("otp") String otp, Model model) {

        otp = otp.substring(7);
        System.out.println("=====" + otp);
        User user = this.userService.getUserByUsernameAndActiveIsTrue(cookieService.getValue("username"));
        String email = user.getEmail(); // . lấy từ session ra
        String username = user.getUsername(); // . lấy user name của tk đang đổi

        if (!authenticationService.otpExit(email)) {
            model.addAttribute("err_message_email", "Mã OTP không còn tồn tại");
            return "user/view/account/confirmOtpEmail";
        }

        boolean isValid = authenticationService.comfirmOTP(email, otp);
        if (isValid) {
            // get User lên lại
            // User user = userService.getUserByUsernameAndActiveIsTrue(username);
            // user.setEmail(email);
            // UserDTO userDTO = modelMapper.map(user, UserDTO.class);
            // userService.create(userDTO);
            // model.addAttribute("user", user);
            return "redirect:/auth/change-email";
        } else {
            model.addAttribute("err_message_email", "Mã OTP không đúng");
            return "user/view/account/confirmOtpEmail";

        }

    }

    @GetMapping(value = "/logout")
    public ResponseEntity<SimpleReponseDTO> logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            cookieService.remove("token");
            cookieService.remove("userId");
            cookieService.remove("username");
            cookieService.remove("remember");
            session.invalidate();
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
         return ResponseEntity.status(200).body(new SimpleReponseDTO("200", "Đăng xuất thành công"));
    }
}
