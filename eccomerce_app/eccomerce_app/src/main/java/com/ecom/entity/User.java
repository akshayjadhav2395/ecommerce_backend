package com.ecom.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;
    @Column(nullable = false)
    private String name;
    //email is userName for our project
    @Column(unique = true)
    private String email;
    private String password;
    private String about;
    private String gender;
    private long phoneNo;
    private boolean active;
    private String address;
    private Date createAt;
    @OneToOne(mappedBy = "user")
    private Cart cart;
    @OneToOne
    private Order order;
    @ManyToMany(mappedBy = "users",fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();

    public String getPassword() {
        System.out.println("Getting password : getter()");
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //important method for providing authority
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return null;
    }

    @Override
    public String getUsername() {
        System.out.println("Getting userName : getter()");
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
