package com.davy.trans.resources;

import com.davy.trans.domain.Category;
import com.davy.trans.services.CategoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@RestController
@RequestMapping("/categories")
public class CategoryResource {

    @Autowired
    CategoryServices categoryServices;

    @GetMapping("")
    public String getAllCategories(HttpServletRequest request) {

        int useId = (Integer) request.getAttribute("userId");

        return "userId" + useId;


    }

    @PostMapping("")
    public ResponseEntity<Locale.Category> addCategory(HttpServletRequest request, @RequestBody Category category) {

        category.setUserId((Integer) request.getAttribute("userId"));

        Category categoryRet = categoryServices.addCategory(category);


        return new ResponseEntity(categoryRet, HttpStatus.CREATED);
    }

}
