package com.blog.blogapi.payloads;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserDto {

    @Id
    private int id;
    private String name;
    private String email;
    private String password;
    private String about;
}
