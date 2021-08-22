package com.davy.trans.domain;

import com.davy.trans.validation.EmailCheck;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import static com.fasterxml.jackson.annotation.JsonInclude.*;
import static com.fasterxml.jackson.annotation.JsonProperty.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"password"})
@Builder
//@JsonInclude(value = Include.NON_EMPTY)
public class User {

    private Integer userId;

    @Size(min = 2, max = 20, message = "btw 2 - 4")
    private String firstName;

    @Size(min = 2, max = 4, message = "btw 2 - 4")
//    @JsonProperty(access = Access.READ_ONLY)
    private String lastName;

    @Email
    @EmailCheck
//    @JsonProperty(value = "emails")
    private String email;

    @Size(min = 4, max = 20, message = "btw 2 - 4")
    @JsonProperty(access = Access.WRITE_ONLY)
    private String password;
}
