package me.nick.springbootlearn.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import me.nick.springbootlearn.entity.User;
import me.nick.springbootlearn.repository.UserRepository;

@Service
public class UserService {
 
    @Resource
    private UserRepository userRepository;
 
    public String test() {
        Optional<User> user = userRepository.findById(8L);
        return user.get().getUsername();
    }
 
    public Long insert() {
        User user = new User();
        user.setUsername("zhouyu");
        Long id = userRepository.save(user).getId();
        return id;
    }
 
    public Long update() {
        User user = new User();
        user.setId(8L);
        user.setUsername("zhouyu123");
        Long id = userRepository.save(user).getId();
        return id;
    }
 
    public String delete() {
        userRepository.deleteById(9L);
        return "success";
    }
}
