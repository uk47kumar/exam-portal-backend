package com.exam.controller;

import com.exam.entity.Role;
import com.exam.entity.User;
import com.exam.entity.UserRole;
import com.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    // creating user
    @PostMapping("/")
    public User createUser(@RequestBody User user) throws Exception {

        user.setProfile("default.png");

        Set<UserRole> userRoleSet = new HashSet<>();

        Role role = new Role();
        role.setRoleId(45L);
        role.setRoleName("NORMAL");

        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);

        userRoleSet.add(userRole);

        return userService.createUser(user, userRoleSet);
    }

    // get user by username
    @GetMapping("/{username}")
    public User getUser(@PathVariable("username")String username){
        return userService.getUser(username);
    }

    // delete user by id
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable("userId")Long userId){
        userService.deleteUser(userId);
    }
}
