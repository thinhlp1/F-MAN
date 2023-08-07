package com.poly.fman.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mysql.cj.callback.FidoAuthenticationCallback;
import com.poly.fman.dto.model.AddressDTO;
import com.poly.fman.dto.model.AddressDTO2;
import com.poly.fman.dto.model.CartItemDTO2;
import com.poly.fman.entity.Address;
import com.poly.fman.entity.User;
import com.poly.fman.repository.AddressRepository;
import com.poly.fman.repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
@AllArgsConstructor
public class AddressService {
    private ModelMapper modelMapper = new ModelMapper();
    private final AddressRepository addressRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    public List<AddressDTO> getByUserId(Integer userId) {
        List<AddressDTO> listAddressDTOs = new ArrayList<>();
        try {
            List<Address> listAdress = addressRepository.findByUserIdAndActive(userId, (byte) 1).orElseThrow();
            listAddressDTOs = listAdress.stream()
                    .map(address -> modelMapper.map(address, AddressDTO.class))
                    .collect(Collectors.toList());
            return listAddressDTOs;
        } catch (Exception e) {
            e.printStackTrace();
            return listAddressDTOs;
        }

    }

    public AddressDTO getById(Integer id) {
        try {
            Address address = addressRepository.findById(id).orElseThrow();
            AddressDTO addressDTO = modelMapper.map(address, AddressDTO.class);
            return addressDTO;
        } catch (Exception e) {
            return null;
        }
    }

    public Address getByIdAndUserId(Integer id, Integer userId) {
        return this.addressRepository.findByIdAndUserId(id, userId).orElse(null);
    }

    public Page<Address> getPageAddress(Integer userId, Pageable pageable) {
        return this.addressRepository.findAllByUserIdAndActiveIsTrue(userId, pageable).orElse(null);
    }

    public List<Address> getPageAddress(Integer userId) {
        return this.addressRepository.findAllByUserIdAndActiveIsTrue(userId).orElse(null);
    }

    public Address getByIsDefault(byte isDefault) {
        return this.addressRepository.findByIsDefault(isDefault);
    }

    public Address getByUserIdAndIsDefaultTrue(Integer userId) {
        return this.addressRepository.findByUserIdAndIsDefaultTrue(userId).orElse(null);
    }

    public Address create(AddressDTO2 addressDTO, Integer userId) {
        User user = this.userService.getUserByIdAndActiveTrue(userId);
        addressDTO.setUser(user);
        addressDTO.setUserId(user.getId());
        Address address = modelMapper.map(addressDTO, Address.class);
        // address.setUser(userRepository.findById(addressDTO.getUserId()).orElse(null));
        address.setActive((byte) 1);
        if (this.getByUserId(userId).size() == 0) {
            address.setIsDefault((byte) 1);
        } else {
            address.setIsDefault((byte) 0);
        }
        return addressRepository.save(address);
    }

    public Address update(AddressDTO2 addressDTO, Integer addressId, Integer userId) {
        // User user = this.userService.getUserById(userId);
        // addressDTO.setUser(user);
        // addressDTO.setUserId(user.getId());
        Address address = this.getByIdAndUserId(addressId, userId);
        // address.setUser(addressDTO.getUser());
        address.setReceiverName(addressDTO.getReceiverName());
        address.setAddress(addressDTO.getAddress());
        address.setNumberPhone(addressDTO.getNumberPhone());
        return addressRepository.save(address);
    }

    public Address updateAddressDefaultIsFalse() {
        Address address = this.getByIsDefault((byte) 1);
        address.setIsDefault((byte) 0);
        return addressRepository.save(address);
        // List<Address> list =
        // this.addressRepository.findAllByUserIdAndActiveIsTrue(userId).orElse(null);
        // for (Address add : list) {
        // add.setIsDefault((byte) 0);
        // addressRepository.save(add);
        // }
    }

    public Address updateAddressDefault(Integer addressId, Integer userId) {
        Address address = this.getByIdAndUserId(addressId, userId);
        address.setIsDefault((byte) 1);
        return addressRepository.save(address);
    }

    public boolean delete(Integer addressId) {
        try {
            addressRepository.deleteById(addressId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
