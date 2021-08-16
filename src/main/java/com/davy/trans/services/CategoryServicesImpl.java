package com.davy.trans.services;

import com.davy.trans.domain.Category;
import com.davy.trans.exceptions.EtBadRequestException;
import com.davy.trans.exceptions.EtResourcesNotFoundException;
import com.davy.trans.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryServicesImpl implements CategoryServices {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<Category> fetchAllCategories(Integer userId) {
        return null;
    }

    @Override
    public Category fetchCategoryById(Category category) throws EtResourcesNotFoundException {
        return categoryRepository.findById(category.getUserId(), category.getCategoryId());
    }

    @Override
    public Category addCategory(Category category) throws EtBadRequestException {

        Integer categoryId = categoryRepository.create(category);

        return categoryRepository.findById(category.getUserId(), categoryId);
    }

    @Override
    public Category updateCategory(Category category) throws EtBadRequestException {
        return null;
    }

    @Override
    public void removeCategoryWithAllTransaction(Category category) throws EtResourcesNotFoundException {

    }
}
