package com.ecom.controller;

import com.ecom.payload.ApiResponse;
import com.ecom.payload.UserDto;
import com.ecom.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto)
    {
        userDto.setActive(true);
        UserDto user = this.userService.createUser(userDto);
        return new ResponseEntity<UserDto>(user, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUser()
    {
        List<UserDto> allUser = this.userService.getAllUser();
        return new ResponseEntity<List<UserDto>>(allUser, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getSingleUser(@PathVariable int userId)
    {
        UserDto userByUserId = this.userService.getUserByUserId(userId);
        return new ResponseEntity<UserDto>(userByUserId, HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email)
    {
        UserDto userByEmail = this.userService.getUserByEmail(email);
        return new ResponseEntity<UserDto>(userByEmail, HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable int userId)
    {
        UserDto userDto1 = this.userService.updateUser(userDto, userId);
        return new ResponseEntity<UserDto>(userDto1, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable int userId)
    {
        this.userService.deleteUser(userId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted successfully..!", false), HttpStatus.OK);
    }
}
