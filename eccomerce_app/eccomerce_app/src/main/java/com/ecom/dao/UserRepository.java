package com.ecom.dao;

import com.ecom.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    public User findByUserId(int userId);
    public Optional<User> findByEmail(String email);
    public List<User> findByName(String name);
    public List<User> findByAddress(String address);
    public User findByEmailAndPassword(String email, String password);
    public List<User> findByActiveTrue();
    public User findByEmailIsNotNull();
}
