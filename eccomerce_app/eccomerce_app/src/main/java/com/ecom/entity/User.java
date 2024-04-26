package com.ecom.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;
    @Column(nullable = false)
    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    private String about;
    private String gender;
    private long phoneNo;
    private boolean active;
    private String address;
    private Date createAt;
    @OneToOne
    private Cart cart;
    @OneToOne
    private Order order;

}
