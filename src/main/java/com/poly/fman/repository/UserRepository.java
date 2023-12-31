package com.poly.fman.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.fman.entity.AuthenticationProvider;
import com.poly.fman.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    public Page<User> findAllByActiveIsTrue(Pageable pageable);

    public Optional<User> findByUsername(String username);

    public Optional<List<User>> findAllByActiveIsTrue();

    public User findByIdAndActiveIsTrue(int id);

    // find by user name and user has active
    public Optional<User> findByUsernameAndActiveIsTrue(String username);

    // find by numberphone of user is active
    public Optional<User> findByNumberPhoneAndActiveIsTrue(String numberPhone);

    // find by email of user is active
    public Optional<User> findByEmailAndActiveIsTrue(String email);

    public Optional<User> findByUsernameAndActiveIsTrueAndAuthenticationProviderEquals(String username, AuthenticationProvider authenticationProvider);

    boolean existsById(int id);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByNumberPhone(String numberPhone);
}