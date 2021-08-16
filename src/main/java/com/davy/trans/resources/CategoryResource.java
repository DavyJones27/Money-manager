package com.davy.trans.resources;

import com.davy.trans.domain.Category;
import com.davy.trans.services.CategoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/categories")
public class CategoryResource {

    @Autowired
    CategoryServices categoryServices;

    @GetMapping("")
    public ResponseEntity<List<Category>> getAllCategories(HttpServletRequest request) {

        int useId = getUserId(request);
        List<Category> categories = categoryServices.fetchAllCategories(useId);

        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> getCategoryById(
            HttpServletRequest request,
            @PathVariable("categoryId") Integer categoryId
    ) {

        int userId = getUserId(request);
        Category category = Category
                .builder()
                .categoryId(categoryId)
                .userId(userId).build();

        Category categoryRet = categoryServices.fetchCategoryById(category);

        return new ResponseEntity<>(categoryRet, HttpStatus.OK);
    }


    @PostMapping("")
    public ResponseEntity<Category> addCategory(HttpServletRequest request, @RequestBody Category category) {

        category.setUserId(getUserId(request));

        Category categoryRet = categoryServices.addCategory(category);


        return new ResponseEntity<>(categoryRet, HttpStatus.CREATED);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<Map<String, Boolean>> updateCategory(
            HttpServletRequest request,
            @PathVariable("categoryId") Integer categoryId,
            @RequestBody Category category
    ) {
        category.setUserId(getUserId(request));
        category.setCategoryId(categoryId);
        categoryServices.updateCategory(category);

        Map<String, Boolean> map = new HashMap<>();

        map.put("Success", true);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    private Integer getUserId(HttpServletRequest request) {

        return (Integer) request.getAttribute("userId");
    }

}
