package com.ecom.payload;

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
    private String name;
    private String email;
    private String password;
    private String about;
    private String gender;
    private long phoneNo;
    private boolean active;
    private String address;
    private Date createAt;

}
