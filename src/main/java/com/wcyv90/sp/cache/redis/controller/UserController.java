package com.wcyv90.sp.cache.redis.controller;

import com.wcyv90.sp.cache.redis.domain.dto.CreateUserDTO;
import com.wcyv90.sp.cache.redis.domain.dto.UserDTO;
import com.wcyv90.sp.cache.redis.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public UserDTO createUser(@RequestBody CreateUserDTO createUserDTO) {
        return UserDTO.from(userService.createUser(createUserDTO.getName()));
    }

    @GetMapping
    public UserDTO getUser(@RequestParam("name") String name) {
        return UserDTO.from(userService.getUser(name).orElse(null));
    }

    @DeleteMapping
    public UserDTO deleteUser(@RequestParam("name") String name) {
        return UserDTO.from(userService.deleteUser(name).orElse(null));
    }

}
