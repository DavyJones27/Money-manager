package com.davy.trans.resources;

import com.davy.trans.domain.User;
import com.davy.trans.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class userResources {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody User userMap) {

        User user = userService.registerUser(userMap);

        Map<String, String> map = new HashMap<>();

        map.put("message", "registered Sucessfully");

        return new ResponseEntity<>(map, HttpStatus.OK);


    }
}
