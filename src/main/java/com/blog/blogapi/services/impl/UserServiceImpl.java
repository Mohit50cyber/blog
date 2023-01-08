package com.blog.blogapi.services.impl;

import com.blog.blogapi.entities.User;
import com.blog.blogapi.exceptions.ResourceNotFoundException;
import com.blog.blogapi.payloads.UserDto;
import com.blog.blogapi.repository.UserRepo;
import com.blog.blogapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDto createUser(UserDto userDto) {

        User user=this.dtotoUser(userDto);
        User savedUser=this.userRepo.save(user);
        return this.usertoDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User" ,"id" ,userId));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());

        User updateUser = this.userRepo.save(user);
        UserDto userDto1 = this.usertoDto(updateUser);
        return userDto1;
    }

    @Override
    public UserDto getUserById(Integer userId) {
       User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User" ,"Id" , userId));
       return this.usertoDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = this.userRepo.findAll();
        List<UserDto> userDtos = users.stream().map(user -> this.usertoDto(user)).collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public void deleteUser(Integer userId) {
        User user =this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","Id",userId));
        this.userRepo.delete(user);
    }

    public User dtotoUser(UserDto userDto){
        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setAbout(user.getAbout());
        user.setEmail(user.getEmail());
        user.setPassword(user.getPassword());
        return user;
    }

    public UserDto usertoDto(User user){
        UserDto userDto=new UserDto();
        userDto.setId(user.getId());
        userDto.setAbout(user.getAbout());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        return userDto;
    }
}
