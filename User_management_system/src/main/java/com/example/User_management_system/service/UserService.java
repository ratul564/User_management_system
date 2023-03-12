package com.example.User_management_system.service;

import com.example.User_management_system.dao.UserRepository;
import com.example.User_management_system.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public int addUser(Users user) {
        Users userObj = userRepository.save(user);
        return userObj.getUserId();
    }

    public List<Users> getUser(String userId) {
         List<Users> userList;

         if(null!=userId){
              userList = new ArrayList<>();
              userList.add(userRepository.findById(Integer.valueOf(userId)).get());
         }
         else{
             userList = userRepository.findAll();
         }
         return userList;
    }

    public void updateUser(int userId, Users newUser) {
        Users user = userRepository.findById(userId).get();

        user.setUsername(newUser.getUsername());
        user.setDateOfBirth(newUser.getDateOfBirth());
        user.setEmail(newUser.getEmail());
        user.setPhoneNumber(newUser.getPhoneNumber());

        userRepository.save(user);

    }

    public void deleteUser(int userId) {
        userRepository.deleteById(userId);
    }
}
