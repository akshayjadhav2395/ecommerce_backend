package com.ecom.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private int userId;
    @NotEmpty
    @Size(min = 4, max = 20, message = "name must be min of 4 characters and max of 20 characters")
    @Pattern(regexp = "^[A-Za-z]\\w{5,29}$", message = "Invalid username")
    private String name;
    @Email(message = "valid email id required")
    private String email;
    @NotEmpty
    @Size(min = 4, message = "password must be of 4 digit")
    private String password;
    @NotEmpty   
    private String about;
    private String gender;
    private long phoneNo;
    private boolean active;
    private String address;
    private Date createAt;

}
