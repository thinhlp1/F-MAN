package com.poly.fman.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.poly.fman.dto.UserDTO2;
import com.poly.fman.dto.auth.ChangeEmailDTO;
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

@Controller
@AllArgsConstructor
@RequestMapping("/user/profile")
public class ProfileController {
    private final UserService userService;
    private final AddressService addressService;
    private final ParamService paramService;
    private HttpSession session;
    private SessionService sessionService;

    @GetMapping("/{id}")
    public String profile(@PathVariable("id") int id, Model model) {
        User user = this.userService.getUserById(id);
        Address address = this.addressService.getByUserIdAndIsDefaultTrue(id);
        int listAddressSize = addressService.getPageAddress(id).size();
        model.addAttribute("user", user);
        model.addAttribute("user2", user);
        model.addAttribute("address", address);
        model.addAttribute("listAddressSize", listAddressSize);
        ChangeEmailDTO changeEmail = (ChangeEmailDTO) session.getAttribute("ChangeEmailDto");
        //Kiểm tra khi người dùng vào trang tới trang Đổi email nhưng nhấn vào profile chứ không đổi mail
        if(changeEmail == null){
            session.removeAttribute("successEmail");
        }
        model.addAttribute("showEmail", session.getAttribute("showEmail"));
        model.addAttribute("successEmail", session.getAttribute("successEmail"));
        return "user/view/user/profile";
    }

    @PostMapping("/{id}")
    public String updateProfile(@PathVariable("id") int id, @Valid @ModelAttribute("user2") UserDTO2 userDTO,
            BindingResult result,
            Model model) {

        // Kiểm tra các lỗi null, định dạng

        if (result.hasErrors() && userDTO.getName().equals("") && userDTO.getNumberPhone().equals("")) {
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
            userDTO.setImage(userService.getUserById(id).getImage());
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
