package com.davy.trans.services;

import com.davy.trans.domain.Category;
import com.davy.trans.exceptions.EtBadRequestException;
import com.davy.trans.exceptions.EtResourcesNotFoundException;

import java.util.List;

public interface CategoryServices {

    List<Category> fetchAllCategories(Integer userId);

    Category fetchCategoryById(Category category) throws EtResourcesNotFoundException;

    Category addCategory(Category category) throws EtBadRequestException;

    Category updateCategory(Category category) throws EtBadRequestException;

    void removeCategoryWithAllTransaction(Integer userId, Integer categoryId) throws EtResourcesNotFoundException;

}
