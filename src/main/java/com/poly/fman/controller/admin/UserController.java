package com.poly.fman.controller.admin;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.poly.fman.dto.model.UserDTO2;
import com.poly.fman.entity.User;
import com.poly.fman.service.UserService;
import com.poly.fman.service.common.DateUtils;
import com.poly.fman.service.common.ParamService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/admin/users")
public class UserController {

    private final UserService userService;
    private final ParamService paramService;

    @ModelAttribute("items")
    public Page<User> getListVoucher(
            @RequestParam("p") Optional<Integer> p,
            @RequestParam("field") Optional<String> field,
            Model model) {
        // Khởi tạo một đối tượng page
        Page<User> page;
        // Và biến pageSize để render số lượng element trong 1 page
        int pageSize = 5;
        // Kiểm tra điều kiện nếu người dùng lọc theo những voucher không active
        Pageable pageable = PageRequest.of(p.orElse(0), pageSize, Sort.by(field.orElse("createAt")).descending());
        page = userService.getListUser(pageable);
        model.addAttribute("field", field.orElse("createAt"));
        return page;
    }

    @GetMapping("/")
    public String getUsers(Model model) {
        UserDTO2 userDTO = new UserDTO2();
        model.addAttribute("user", userDTO);
        return "admin/layout/User/user_list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        UserDTO2 userDTO = new UserDTO2();
        System.out.println("=======>Đây là user dto id : " + userDTO.getRoleId());
        model.addAttribute("user", userDTO);
        return "admin/layout/User/user_add";
    }

    @GetMapping("/update-form/{username}")
    public String updateForm(@PathVariable("username") String username, Model model) {
        UserDTO2 userDTO = userService.getUserDTO(username);
        model.addAttribute("user", userDTO);
        return "admin/layout/User/user_update";
    }

    @GetMapping("/details/{username}")
    public String details(@PathVariable("username") String username, Model model) {
        UserDTO2 userDTO = this.userService.getUserDTO(username);
        String createDate = DateUtils.toString(userDTO.getCreateAt(), "dd/MM/yyyy HH:mm:ss");

        if (userDTO.getUpdateAt() != null) {
            String updateDate = DateUtils.toString(userDTO.getUpdateAt(), "dd/MM/yyyy HH:mm:ss");
            model.addAttribute("updateDate", updateDate);
        }
        model.addAttribute("createDate", createDate);

        model.addAttribute("user", userDTO);
        return "admin/layout/User/user_details";
    }

    @PostMapping("/delete/{username}")
    public String delete(@PathVariable("username") String username) {
        userService.delete(username);
        return "redirect:/admin/users/";
    }

    @PostMapping("/create")
    public String create(@Validated @ModelAttribute("user") UserDTO2 userDTO, BindingResult result,
            @RequestParam("photo_file") MultipartFile img,
            Model model) {
        // Kiểm tra tên đăng nhập, email, số điện thoại đã tồn tại
        String checkUser = userService.userIsExisted(userDTO.getUsername(), userDTO.getEmail(),
                userDTO.getNumberPhone());
        if (checkUser != null) {
            if (checkUser.equals("usernameIsExisted")) {
                model.addAttribute("message", "Tên đăng nhập đã tồn tại");
                return "admin/layout/User/user_add";
            } else if (checkUser.equals("emailIsExisted")) {
                model.addAttribute("messageEmail", "Email đã tồn tại, hãy chọn email khác");
                return "admin/layout/User/user_add";
            } else if (checkUser.equals("numberPhoneIsExisted")) {
                model.addAttribute("messagePhone", "Số điện thoại đã tồn tại");
                return "admin/layout/User/user_add";
            }
        }

        // Kiểm tra các lỗi null, định dạng, hình ảnh
        if (result.hasErrors() || img.isEmpty()) {
            if (img.isEmpty()) {
                model.addAttribute("message_img", "Vui lòng chọn hình ảnh");
            }
            return "admin/layout/User/user_add";
        } else {
            String image = paramService.save(img, "/views/admin/plugins/images/");
            userDTO.setImage(image);
            userDTO.setUsername(userDTO.getEmail());
            userService.create(userDTO);
            model.addAttribute("user", userDTO);
        }
        return "redirect:/admin/users/update-form/" + userDTO.getUsername();
    }

    @PostMapping("/update/{username}")
    public String update(@PathVariable("username") String username, UserDTO2 userDTO, Model model,
            @RequestParam("photo_file") MultipartFile img) {
        if (img.isEmpty()) {
            userDTO.setImage(userService.getUser(username).getImage());
        } else {
            String image = paramService.save(img, "/views/admin/plugins/images/");
            userDTO.setImage(image);
        }
        User user = userService.update(userDTO, username);
        model.addAttribute("user", user);
        return "redirect:/admin/users/update-form/" + user.getUsername();
    }

    @PostMapping("/reset-password/{username}")
    public String resetPass(@PathVariable("username") String username, UserDTO2 userDTO, Model model) {
        User user = userService.resetPassword(userDTO, username);
        model.addAttribute("user", user);
        return "redirect:/admin/users/update-form/" + user.getUsername();
    }

}