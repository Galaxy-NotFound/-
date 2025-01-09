package com.match1.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {
    private Integer id;
    private String username;
    private String password;
    private String name;
    private String gender;
    private String image;
    private String email;

    public User(Integer id, String username, String name, String gender) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.gender = gender;
    }

    public User(Integer id, String username, String password, String name, String gender, String image) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.image = image;
    }
}
