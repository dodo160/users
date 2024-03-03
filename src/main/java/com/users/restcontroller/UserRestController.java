package com.users.restcontroller;

import com.users.dto.UserDTO;
import com.users.mapper.UserMapper;
import com.users.model.User;
import com.users.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class UserRestController {

    private UserService userService;

    private UserMapper userMapper;

    public UserRestController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") final Integer id) {
        final User user = userService.findById(id);
        if (Objects.isNull(user)) {
//            throw new NotFoundException("User not found whith id: " + id);
            return new ResponseEntity("User not found whith id: " + id, HttpStatus.NOT_FOUND);
        } else {
            return ResponseEntity.ok(userMapper.toDto(user));
        }
    }
}
