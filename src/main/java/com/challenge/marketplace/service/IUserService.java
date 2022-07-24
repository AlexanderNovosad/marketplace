package com.challenge.marketplace.service;

import com.challenge.marketplace.entity.User;

import java.util.List;

public interface IUserService {
    User addUser(User user);

    User getUser(Long userId);

    void deleteUser(Long userId);

    List<User> getAllUsers();

}
