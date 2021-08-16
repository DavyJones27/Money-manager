package com.davy.trans.repositories;

import com.davy.trans.domain.Category;
import com.davy.trans.exceptions.EtBadRequestException;
import com.davy.trans.exceptions.EtResourcesNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;


import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {

    private static final String SQL_CREATE = "INSERT INTO ET_CATEGORIES " +
            "(CATEGORY_ID , USER_ID, TITLE, DESCRIPTION)" +
            "VALUES(NEXTVAL('ET_CATEGORIES_SEQ'), ?, ?, ?)";

    private static final String SQL_FIND_BY_ID = "SELECT  " +
            "C.CATEGORY_ID , C.USER_ID, C.TITLE, " +
            "C.DESCRIPTION, COALESCE(SUM(T.AMOUNT), 0) TOTAL_EXPENSE " +
            "FROM ET_TRANSACTIONS T " +
            "RIGHT OUTER JOIN ET_CATEGORIES C ON C.CATEGORY_ID = T.CATEGORY_ID " +
            "WHERE C.USER_ID = ? AND C.CATEGORY_ID = ? GROUP BY C.CATEGORY_ID";

    private static final String SQL_FIND_ALL = "SELECT  " +
            "C.CATEGORY_ID , C.USER_ID, C.TITLE, " +
            "C.DESCRIPTION, COALESCE(SUM(T.AMOUNT), 0) TOTAL_EXPENSE " +
            "FROM ET_TRANSACTIONS T " +
            "RIGHT OUTER JOIN ET_CATEGORIES C ON C.CATEGORY_ID = T.CATEGORY_ID " +
            "WHERE C.USER_ID = ? GROUP BY C.CATEGORY_ID";

    private static final String SQL_UPDATE = "UPDATE ET_CATEGORIES SET TITLE = ?, DESCRIPTION = ? " +
            "WHERE USER_ID = ? AND CATEGORY_ID = ?";

    @Autowired
    JdbcTemplate jdbcTemplate;


    private RowMapper<Category> categoryRowMapper = ((rs, rowNum) -> {
        return new Category(
                rs.getInt("CATEGORY_ID"),
                rs.getInt("CATEGORY_ID"),
                rs.getString("TITLE"),
                rs.getString("DESCRIPTION"),
                rs.getDouble("TOTAL_EXPENSE")
        );
    });

    @Override
    public List<Category> findAll(Integer userId) throws EtResourcesNotFoundException {
        return jdbcTemplate.query(SQL_FIND_ALL, categoryRowMapper, userId);
    }

    @Override
    public Category findById(Integer userId, Integer categoryId) throws EtResourcesNotFoundException {

        try {
            return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, categoryRowMapper, userId, categoryId);
        } catch (Exception e) {
            throw new EtResourcesNotFoundException("Catogory Not Found");
        }
    }

    @Override
    public Integer create(Category category) throws EtBadRequestException {
        try {

            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, category.getUserId());
                ps.setString(2, category.getTitle());
                ps.setString(3, category.getDescription());

                return ps;

            }, keyHolder);

            return (Integer) keyHolder.getKeys().get("CATEGORY_ID");

        } catch (Exception e) {
            throw new EtBadRequestException("Invalid request");
        }
    }

    @Override
    public void update(Category category) throws EtBadRequestException {
        System.out.println(category);
        try {
            jdbcTemplate.update(
                    SQL_UPDATE,
                    category.getTitle(),
                    category.getDescription(),
                    category.getUserId(),
                    category.getCategoryId()
            );

        } catch (Exception e) {
            throw new EtBadRequestException("Invalid request");
        }
    }

    @Override
    public void removeById(Category category) {

    }
}
