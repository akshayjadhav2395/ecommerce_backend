package com.ecom.service;

import com.ecom.payload.UserDto;

import java.util.List;

public interface UserServiceI {

    public UserDto createUser(UserDto userDto);
    public List<UserDto> getAllUser();
    public UserDto updateUser(UserDto userDto, int userId);
    public UserDto getUserByUserId(int userId);
    public UserDto getUserByEmail(String email);
    public void deleteUser(int userId);
}
