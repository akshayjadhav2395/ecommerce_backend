package com.ecom.service.impl;

import com.ecom.dao.UserRepository;
import com.ecom.entity.User;
import com.ecom.exception.ResourceNotFoundException;
import com.ecom.payload.UserDto;
import com.ecom.service.UserServiceI;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserServiceI {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto userDto) {

        User user = this.modelMapper.map(userDto, User.class);

        User saveUser = this.userRepository.save(user);

        UserDto userDto1 = this.modelMapper.map(saveUser, UserDto.class);

        return userDto1;
    }

    @Override
    public List<UserDto> getAllUser() {

        List<User> userList = this.userRepository.findAll();
        List<UserDto> userDtos = userList.stream().map(user -> this.modelMapper.map(user, UserDto.class)).collect(Collectors.toList());

        return userDtos;
    }

    @Override
    public UserDto updateUser(UserDto userDto, int userId) {

        User user = this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("Resource with given userId not found...!"));

        user.setAbout(userDto.getAbout());
        user.setActive(userDto.isActive());
        user.setAddress(userDto.getAddress());
        user.setEmail(userDto.getEmail());
        user.setCreateAt(userDto.getCreateAt());
        user.setGender(userDto.getGender());
        user.setName(userDto.getName());
        user.setPassword(userDto.getPassword());
        user.setPhoneNo(userDto.getPhoneNo());

        User updatedUser = this.userRepository.save(user);

        UserDto userDto1 = this.modelMapper.map(updatedUser, UserDto.class);

        return userDto1;
    }

    @Override
    public UserDto getUserByUserId(int userId) {

        User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Resource with given userId not found..!"));
        UserDto userDto = this.modelMapper.map(user, UserDto.class);

        return userDto;
    }

    @Override
    public UserDto getUserByEmail(String email) {

        User user = this.userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Resource with given email id not found..!"));
        UserDto userDto = this.modelMapper.map(user, UserDto.class);
        return userDto;
    }


    @Override
    public void deleteUser(int userId) {

        User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Resource with given userId not found..!"));
        this.userRepository.delete(user);
    }
}
