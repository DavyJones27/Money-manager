package com.davy.trans.domain;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"password"})
@Builder
public class User {

    private Integer userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
