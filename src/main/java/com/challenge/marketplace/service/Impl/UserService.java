package com.challenge.marketplace.service.Impl;

import com.challenge.marketplace.entity.User;
import com.challenge.marketplace.repo.SaleRepository;
import com.challenge.marketplace.repo.UserRepository;
import com.challenge.marketplace.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final SaleRepository saleRepository;

    @Autowired
    public UserService(UserRepository userRepository, SaleRepository saleRepository){
        this.userRepository = userRepository;
        this.saleRepository = saleRepository;
    }

    @Override
    public User addUser(User user) {
        return userRepository.saveAndFlush(user);
    }

    @Override
    public User getUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    @Override
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("Product not found"));
        saleRepository.deleteAllByUser(user);
        userRepository.delete(user);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> allUsers = userRepository.findAll();
        if (allUsers.isEmpty()) {
            throw new EntityNotFoundException("No users found in DB");
        }
        return allUsers;
    }

}
