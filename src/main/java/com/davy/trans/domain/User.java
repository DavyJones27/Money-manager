package com.davy.trans.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"password"})
public class User {

    private Integer userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
