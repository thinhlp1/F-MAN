package com.poly.fman.controller.user;

import java.util.List;
import java.util.Optional;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.poly.fman.dto.model.AddressDTO2;
import com.poly.fman.entity.Address;
import com.poly.fman.entity.User;
import com.poly.fman.service.AddressService;
import com.poly.fman.service.UserService;

import lombok.AllArgsConstructor;

@CrossOrigin("*")
@Controller
@AllArgsConstructor
public class AddressController {
    private final UserService userService;
    private final AddressService addressService;

    @GetMapping(value = "/user/address/all", params = "user-id")
    public String getAllAddress(@RequestParam(name = "user-id") Integer userId, Model model) {
        User user = this.userService.getUserByIdAndActiveTrue(userId);
        List<Address> items = addressService.getListAddressByUserId(userId, Sort.by(Sort.Direction.DESC, "isDefault"));
        AddressDTO2 addressDTO = new AddressDTO2();
        model.addAttribute("address", addressDTO);
        model.addAttribute("user", user);
        model.addAttribute("items", items);
        return "user/view/user/user-address";
    }

    @PostMapping(value = "/user/address/create", params = "user-id")
    public String createAddress(@Valid @ModelAttribute("address") AddressDTO2 addressDTO,
                                @RequestParam(name = "user-id") Integer userId, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("userId", userId);
            model.addAttribute("errorIsTrue", true);
            return "user/view/user/user-address";
        }
        addressService.create(addressDTO, userId);
        model.addAttribute("address", addressDTO);
        return "redirect:/user/address/all?user-id=" + addressDTO.getUserId();
    }

    @GetMapping("/user/address/{id}")
    public String getAddress() {

        return "user/view/user/user-address";
    }

    @PostMapping(value = "/user/address/update/{addressId}", params = "user-id")
    public String updateAddress(@PathVariable("addressId") Integer addressId,
            @RequestParam(name = "user-id") Integer userId, AddressDTO2 addressDTO, Model model) {
//        addressDTO.setId(addressId);
        System.out.println(addressDTO);
        addressService.update(addressDTO, addressId, userId);
//        model.addAttribute("address", addressDTO);
        return "redirect:/user/address/all?user-id=" + userId;
    }

    @PostMapping(value = "/user/address/address-default/{addressId}", params = "user-id")
    public String updateAddressDefault(@PathVariable("addressId") Integer addressId,
            @RequestParam(name = "user-id") Integer userId, AddressDTO2 addressDTO, Model model) {
        System.out.println(addressId);
        System.out.println(userId);
        addressService.updateAddressDefaultIsFalse(userId);
        addressService.updateAddressDefault(addressId);
        model.addAttribute("address", addressDTO);
        return "redirect:/user/address/all?user-id=" + userId;
    }

    @PostMapping(value = "/user/address/delete/{id}", params = "user-id")
    public String deleteAddress(@PathVariable("id") int id, @RequestParam(name = "user-id") Integer userId) {
        addressService.delete(id);
        return "redirect:/user/address/all?user-id=" + userId;
    }

}
