package com.woact.dolplads.entity;

import com.woact.dolplads.annotations.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by dolplads on 12/10/2016.
 * Represents a user
 */
@NamedQueries({
        @NamedQuery(name = User.FIND_BY_USERNAME, query = "select user from User user where userName=:userName"),
        @NamedQuery(name = User.FIND_BY_CREDENTIALS,
                query = "select u from User u where u.userName = :userName and u.passwordHash = :password")
})
@Getter
@Setter
@Entity
public class User {
    public static final String FIND_BY_USERNAME = "user_find_by_username";
    public static final String FIND_BY_CREDENTIALS = "user_find_by_credentials";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 40)
    @NotEmpty
    private String name;

    @Size(max = 40)
    @NotEmpty
    private String surName;

    @Pattern(regexp = "[A-Za-z0-9]{1,32}")
    @Column(unique = true)
    @Size(max = 25)
    @NotEmpty
    private String userName;

    @Valid
    @NotNull
    @Embedded
    private Address address;

    @NotEmpty
    private String passwordHash;

    @NotEmpty
    private String salt;

    @Transient
    private boolean loggedIn;

    @OneToMany(mappedBy = "user")
    private List<Post> posts;

    public User() {
        address = new Address();
    }

    public User(String userName, String name, String surName, Address address) {
        this.name = name;
        this.surName = surName;
        this.userName = userName;
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surName='" + surName + '\'' +
                ", userName='" + userName + '\'' +
                ", address=" + address +
                ", passwordHash='" + passwordHash + '\'' +
                ", salt='" + salt + '\'' +
                ", loggedIn=" + loggedIn +
                '}';
    }
}
