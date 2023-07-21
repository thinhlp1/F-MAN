package com.poly.fman.controller.user;

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

import com.poly.fman.dto.model.AddressDTO2;
import com.poly.fman.entity.Address;
import com.poly.fman.entity.User;
import com.poly.fman.service.AddressService;
import com.poly.fman.service.UserService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/user/address")
public class AddressController {
    private final UserService userService;
    private final AddressService addressService;

    @ModelAttribute("items")
    public Page<Address> getListAddress(
            @RequestParam("p") Optional<Integer> p,
            @RequestParam("field") Optional<String> field, @RequestParam(name = "user-id") Integer userId,
            Model model) {
        // Khởi tạo một đối tượng page
        Page<Address> page;
        // Và biến pageSize để render số lượng element trong 1 page
        int pageSize = 5;
        // Kiểm tra điều kiện nếu người dùng lọc theo những voucher không active
        Pageable pageable = PageRequest.of(p.orElse(0), pageSize, Sort.by(field.orElse("isDefault")).descending());
        page = addressService.getPageAddress(userId, pageable);
        model.addAttribute("field", field.orElse("isDefault"));
        return page;
    }

    @GetMapping(value = "/all", params = "user-id")
    public String getAllAddress(@RequestParam(name = "user-id") Integer userId, Model model) {
        User user = this.userService.getUserById(userId);
        AddressDTO2 address = new AddressDTO2();
        address.setUser(user);
        address.setUserId(user.getId());
        model.addAttribute("address", address);
        return "user/view/user/user-address";
    }

    @PostMapping(value = "/all", params = "user-id")
    public String createAddress(@Validated @ModelAttribute("address") AddressDTO2 addressDTO, BindingResult result,
            @RequestParam(name = "user-id") Integer userId, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("userId", userId);
            model.addAttribute("errorIsTrue", true);
            return "user/view/user/user-address";
        }
        addressService.create(addressDTO, userId);
        model.addAttribute("address", addressDTO);
        return "redirect:all?user-id=" + addressDTO.getUserId();
    }

    @GetMapping("/{id}")
    public String getAddress() {
        return "user/view/user/user-address";
    }

    @PostMapping(value = "/update/{addressId}", params = "user-id")
    public String updateAddress(@PathVariable("addressId") Integer addressId,
            @RequestParam(name = "user-id") Integer userId, AddressDTO2 addressDTO, Model model) {
        addressService.update(addressDTO, addressId, userId);
        model.addAttribute("address", addressDTO);
        return "redirect:/user/address/all?user-id=" + userId;
    }

    @PostMapping(value = "/address-default/{addressId}", params = "user-id")
    public String updateAddressDefault(@PathVariable("addressId") Integer addressId,
            @RequestParam(name = "user-id") Integer userId, AddressDTO2 addressDTO, Model model) {
        addressService.updateAddressDefaultIsFalse();
        addressService.updateAddressDefault(addressId, userId);
        model.addAttribute("address", addressDTO);
        return "redirect:/user/address/all?user-id=" + userId;
    }

    @PostMapping(value = "/delete/{id}", params = "user-id")
    public String deleteAddress(@PathVariable("id") int id, @RequestParam(name = "user-id") Integer userId) {
        addressService.delete(id);
        return "redirect:/user/address/all?user-id=" + userId;
    }

}
