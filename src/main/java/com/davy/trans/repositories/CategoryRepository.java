package com.davy.trans.repositories;

import com.davy.trans.domain.Category;
import com.davy.trans.exceptions.EtBadRequestException;
import com.davy.trans.exceptions.EtResourcesNotFoundException;

import java.util.List;

public interface CategoryRepository {

    List<Category> findAll(Integer userId) throws EtResourcesNotFoundException;

    Category findById(Integer userId,   Integer categoryId) throws EtResourcesNotFoundException;

    Integer create (Category category) throws EtBadRequestException;

    void update(Category category) throws EtBadRequestException;

    void removeById(Category category);


}
