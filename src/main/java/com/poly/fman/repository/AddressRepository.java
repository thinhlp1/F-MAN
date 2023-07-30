package com.poly.fman.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.fman.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

    // find all address of user -- active = true
    public Optional<List<Address>> findByUserIdAndActive(Integer userId, byte active);

    public Optional<Page<Address>> findAllByUserIdAndActiveIsTrue(Integer userId, Pageable pageable);

    public Optional<List<Address>> findAllByUserIdAndActiveIsTrue(Integer userId);

    public Optional<Address> findByUserIdAndIsDefaultTrue(Integer userId);

    public Address findByIsDefault(byte isDefault);

    public Optional<List<Address>> findByUserId(Integer userId);

    public Optional<Address> findByIdAndUserId(Integer id, Integer userId);

    public Optional<Address> findByIdAndActiveIsTrue(Integer id);


}
