package com.ecommerce.controller;

import com.ecommerce.entity.User;
import com.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class UserController {

    @Autowired
    private  UserService userService;

    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User user){
        return  userService.createUser(user);
    }

    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAllUsers(){
       return userService.getAllUsers();
    }
    @GetMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User getUserById(@PathVariable long id){
        return userService.getUserById(id).get();
    }

    @PostMapping("/verify")
    public User VerifyLogin(@RequestParam(required = false) String phone,
                            @RequestParam(required = false) String email,
                            @RequestParam String password){
        User user= null;
        if(phone != null){
            Optional<User> optionalUser = userService.verifyUserByPhoneAndPassword(phone, password);
            if(optionalUser.isPresent()){
                user  = optionalUser.get();
            }
        } else if (email != null) {
            Optional<User> optionalUser = userService.verifyUserByEmailAndPassword(email, password);
            if (optionalUser.isPresent()){
                user  = optionalUser.get();
            }

        }

        return  user;
    }

    @PutMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User updateUser(@PathVariable("id") long id, @RequestBody User user){
        user.setId(id);
        return userService.updateUser(user);
    }

    @DeleteMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public  void  deleteUser(@PathVariable("id") long id){
         userService.deleteUser(id);
    }

}
