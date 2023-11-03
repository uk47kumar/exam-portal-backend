package com.exam.service;

import com.exam.entity.User;
import com.exam.entity.UserRole;

import java.util.Set;

public interface UserService {

    // creating user
    User createUser(User user, Set<UserRole> userRoles) throws Exception;

    // get user by username
    User getUser(String username);

    // delete user by id
    void deleteUser(Long userId);
}
