package com.davy.trans.domain;

import com.davy.trans.validation.EmailCheck;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"password"})
@Builder
public class User {

    private Integer userId;

    @Size(min = 2, max = 20, message = "btw 2 - 4")
    private String firstName;

    @Size(min = 2, max = 4, message = "btw 2 - 4")
    private String lastName;

    @Email
    @EmailCheck
    private String email;

    @Size(min = 4, max = 20, message = "btw 2 - 4")
    private String password;
}
