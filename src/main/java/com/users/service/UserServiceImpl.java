package com.users.service;

import com.users.model.User;
import com.users.repository.UserRepository;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> findAll() {
        final List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    @Override
    public User add(final User entity) {
        return userRepository.save(entity);
    }

    @Override
    public User update(final User entity) {
        final User user = userRepository.findById(entity.getId()).orElse(null);
        if (Objects.isNull(user)) {
            throw new ValidationException("User entity doesn't exist with id: " + entity.getId());
        }
        user.setName(entity.getName());
        user.setUsername(entity.getUsername());
        user.setPassword(entity.getPassword());
        return userRepository.save(user);
    }

    @Override
    public void delete(final Integer id) {
        userRepository.deleteById(id);
    }
}
