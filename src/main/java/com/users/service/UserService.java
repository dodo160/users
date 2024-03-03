package com.users.service;

import com.users.model.User;

import java.util.List;

public interface UserService {

    User findById(Integer id);

    List<User> findAll();

    User add(User entity);

    User update(User entity);
}
