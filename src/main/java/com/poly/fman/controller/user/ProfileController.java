package com.poly.fman.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.poly.fman.dto.auth.ChangeEmailDTO;
import com.poly.fman.dto.model.UserDTO2;
import com.poly.fman.entity.Address;
import com.poly.fman.entity.User;
import com.poly.fman.service.AddressService;
import com.poly.fman.service.UserService;
import com.poly.fman.service.common.ParamService;
import com.poly.fman.service.common.SessionService;

import jakarta.mail.Session;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@CrossOrigin("*")
@Controller
@AllArgsConstructor
public class ProfileController {
    private final UserService userService;
    private final AddressService addressService;
    private final ParamService paramService;
    private HttpSession session;
    private SessionService sessionService;

    @GetMapping("/user/profile/{id}")
    public String profile(@PathVariable("id") int id, Model model) {
        User user = userService.getUserByIdAndActiveTrue(id);
        if (!user.getAuthenticationProvider().name().equalsIgnoreCase("LOCAL")){
            model.addAttribute("checkUserAuthProviderIsLocal", false);
        }else{
            model.addAttribute("checkUserAuthProviderIsLocal", true);
        }
        Address address = addressService.getByUserIdAndIsDefaultTrue(id);
//        if (address == null){
//            address = new Address();
//        }
        model.addAttribute("user2", user);
        model.addAttribute("address", address);
        return "user/view/user/profile";
    }

    @PostMapping("/user/profile/{id}")
    public String updateProfile(@PathVariable("id") int id, @Valid @ModelAttribute("user2") UserDTO2 userDTO, Errors errors,
            Model model) {

        // Kiểm tra các lỗi null, định dạng

        if (errors.hasErrors() && userDTO.getName().equals("") && userDTO.getNumberPhone().equals("")) {
            UserDTO2 userDefault = userService.getUserDtoById(id);
            Address address = this.addressService.getByUserIdAndIsDefaultTrue(id);
            model.addAttribute("user2", userDTO);
            model.addAttribute("user", userDefault);
            model.addAttribute("address", address);
            model.addAttribute("errorIsTrue", true);
            return "user/view/user/profile";
        }

        User user = this.userService.getUserDtoById(id, userDTO);
        User user2 = userService.updateByUser(user);
        model.addAttribute("user", user2);
        return "redirect:/user/profile/" + id;
    }

    @PostMapping("/update-image/{id}")
    public String updateProfileImage(@PathVariable("id") int id, UserDTO2 userDTO,
            Model model, @RequestParam("photo_file") MultipartFile img) {
        if (img.isEmpty()) {
            userDTO.setImage(userService.getUserByIdAndActiveTrue(id).getImage());
        } else {
            String image = paramService.save(img, "/views/admin/plugins/images/");
            userDTO.setImage(image);
        }
        // Kiểm tra các lỗi null, định dạng, hình ảnh
        userDTO = userService.getUserDtoByIdForUpdateImage(id, userDTO);
        userService.updateImageByUser(userDTO);// Bước này để save user vô csdl
        model.addAttribute("user", userDTO);
        return "redirect:/user/profile/" + id;
    }
}
