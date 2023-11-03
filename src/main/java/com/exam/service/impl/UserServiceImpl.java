package com.exam.service.impl;

import com.exam.entity.User;
import com.exam.entity.UserRole;
import com.exam.repo.RoleRepository;
import com.exam.repo.UserRepository;
import com.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    // creating user
    @Override
    public User createUser(User user, Set<UserRole> userRoles) throws Exception {

        User local = userRepository.findByUsername(user.getUsername());

        if(local != null){
            System.out.println("User is already there !!");
            throw new Exception("user already present !!");
        }
        else {
            // traverse to userRole
            // first we save the user's role
            for(UserRole ur : userRoles){
                roleRepository.save(ur.getRole());
            }

            // then through user we save the all roles that belong to that user
            user.getUserRoles().addAll(userRoles);
            local = userRepository.save(user);
        }
        return local;
    }

    @Override
    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
