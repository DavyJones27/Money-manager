package com.davy.trans.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/categories")
public class CategoryResource {

    @GetMapping("")
    public String getAllCategories(HttpServletRequest request) {

        int useId = (Integer) request.getAttribute("userId");

        return "userId" + useId;


    }

}
