package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {
    void registerDefaultUser(User user);
    List<User> findAll();
    User findOne(Long id);
    User findUser(String userName);
    void save(User user);
    void update(Long id, User updatedUser);
    void delete(Long id);

}
