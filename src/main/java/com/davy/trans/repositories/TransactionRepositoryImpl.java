package com.davy.trans.repositories;

import com.davy.trans.domain.Transaction;
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
public class TransactionRepositoryImpl implements TransactionRepository {

    private static final String SQL_CREATE = "INSERT INTO ET_TRANSACTIONS ( " +
            "TRANSACTION_ID, CATEGORY_ID, USER_ID, AMOUNT, NOTE, TRANSACTION_DATE ) " +
            "VALUES(NEXTVAL('ET_TRANSACTIONS_SEQ'), ?, ?, ?, ?, ?)";

    private static final String SQL_FIND_BY_ID = "SELECT " +
            "TRANSACTION_ID, " +
            "CATEGORY_ID, " +
            "USER_ID, " +
            "AMOUNT, " +
            "NOTE, " +
            "TRANSACTION_DATE " +
            "FROM ET_TRANSACTIONS WHERE USER_ID = ? AND CATEGORY_ID = ? AND TRANSACTION_ID = ? ";


    @Autowired
    JdbcTemplate jdbcTemplate;

    private RowMapper<Transaction> transactionRowMapper = ((rs, rowNum) -> {
        return new Transaction(
                rs.getInt("TRANSACTION_ID"),
                rs.getInt("CATEGORY_ID"),
                rs.getInt("USER_ID"),
                rs.getDouble("AMOUNT"),
                rs.getString("NOTE"),
                rs.getLong("TRANSACTION_DATE")
        );
    });

    @Override
    public List<Transaction> findAll(Integer userId, Integer categoryId) {
        return null;
    }

    @Override
    public Transaction findById(Integer userId, Integer categoryId, Integer transactionId) throws EtResourcesNotFoundException {
        try {
            return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, transactionRowMapper, userId, categoryId, transactionId);

        } catch (Exception e) {
            throw new EtResourcesNotFoundException("Transaction Not Found");
        }
    }

    @Override
    public Integer create(Transaction transaction) throws EtBadRequestException {

        try {

            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, transaction.getCategoryId());
                ps.setInt(2, transaction.getUserId());
                ps.setDouble(3, transaction.getAmount());
                ps.setString(4, transaction.getNote());
                ps.setLong(5, transaction.getTransactionDate());

                return ps;
            }, keyHolder);

            return (Integer) keyHolder.getKeys().get("TRANSACTION_ID");
        } catch (Exception e) {
            throw new EtBadRequestException("Invalid Request");
        }
    }

    @Override
    public void update(Transaction transaction) throws EtBadRequestException {

    }

    @Override
    public void removeById(Integer userId, Integer categoryId, Integer transactionId) throws EtResourcesNotFoundException {

    }
}
