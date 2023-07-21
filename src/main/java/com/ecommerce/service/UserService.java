package com.ecommerce.service;

import com.ecommerce.entity.User;
import com.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user){
        return  userRepository.save(user);
    }

    public List<User> getAllUsers(){
        return  userRepository.findAll();
    }

    public Optional<User> verifyUserByPhoneAndPassword(String phone, String password) {
        Optional<User> optionalUser = userRepository.findByPhone(phone);
        if(optionalUser.isPresent()){
            User user= optionalUser.get();
            if(user.getPassword().equals(password)){
                return  Optional.of(user);
            }
        }
        return  Optional.empty();
    }

    public Optional<User> verifyUserByEmailAndPassword(String email, String password) {
        Optional<User> byEmail = userRepository.findByEmail(email);
        if(byEmail.isPresent()){
            User user = byEmail.get();
            if(user.getEmail().equals(email)){
                return  Optional.of(user);
            }
        }
        return Optional.empty();
    }


    public User updateUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(long id) {
       userRepository.deleteById(id);
    }

    public Optional<User> getUserById(long id) {
        return userRepository.findById(id);
    }
}
