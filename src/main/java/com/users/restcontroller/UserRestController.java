package com.users.restcontroller;

import com.users.dto.UserDTO;
import com.users.mapper.UserMapper;
import com.users.model.User;
import com.users.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        return ResponseEntity.ok(userService.findAll().stream().map(x->userMapper.toDto(x)).collect(Collectors.toList()));
    }

    @PostMapping(value = "/user/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addUser(@Valid @RequestBody final UserDTO dto){
        userService.add(userMapper.fromDto(dto));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/user/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody final UserDTO dto){
        return ResponseEntity.ok(userMapper.toDto(userService.update(userMapper.fromDto(dto))));
    }

}
