package com.example.User_management_system.controller;

import com.example.User_management_system.model.Users;
import com.example.User_management_system.service.UserService;
import jakarta.annotation.Nullable;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/add-user")
    public ResponseEntity<String> addUser(@RequestBody String userData){

        JSONObject isRequestValid = validateUserRequest(userData);

        Users user = null;

        if(isRequestValid.isEmpty()){
            user = setUser(userData);
            userService.addUser(user);
        }
        else{
            return new ResponseEntity<String>(isRequestValid.toString(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Saved",HttpStatus.CREATED);
    }

    @GetMapping(value = "/get-user")
    public List<Users> getUser(@Nullable @RequestParam String userId){
        return userService.getUser(userId);
    }

    @PutMapping(value = "/update-user-info/{userId}")
    public void updateUser(@PathVariable int userId,@RequestBody Users user){
         userService.updateUser(userId,user);
    }

    @DeleteMapping(value = "/delete-user/{userId}")
    public void deleteUser(@PathVariable int userId){
        userService.deleteUser(userId);
    }
    private Users setUser(String userData) {

        Users user = new Users();

         JSONObject json = new JSONObject(userData);

         user.setUsername(json.getString("username"));
         user.setEmail(json.getString("email"));
         user.setDateOfBirth(json.getString("dateOfBirth"));
         user.setPhoneNumber(json.getString("phoneNumber"));

        Timestamp createdTime = new Timestamp(System.currentTimeMillis());
        user.setCreatedDate(createdTime);

        return user;

    }

    private JSONObject validateUserRequest(String userData) {

        JSONObject userJson = new JSONObject(userData);
        JSONObject errorList = new JSONObject();

        if(userJson.has("username")){
            String username = userJson.getString("username");
        }
        else{
            errorList.put("username","Missing parameters");
        }

        if(userJson.has("dateOfBirth")){
            String dateOfBirth = userJson.getString("dateOfBirth");
        }
        else{
            errorList.put("dateOfBirth","Missing parameters");
        }

        if(userJson.has("email")){
            String email = userJson.getString("email");
        }
        else{
            errorList.put("email","Missing parameters");
        }

        if(userJson.has("phoneNumber")){
            String phoneNumber = userJson.getString("phoneNumber");
        }
        else{
            errorList.put("phoneNumber","Missing parameters");
        }
        return errorList;
    }
}
