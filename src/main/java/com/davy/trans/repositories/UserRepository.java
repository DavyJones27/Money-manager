package com.davy.trans.repositories;

import com.davy.trans.domain.User;
import com.davy.trans.exceptions.EtAuthException;

public interface UserRepository {

    Integer create(User user) throws EtAuthException;

    User findByEmailAndPassword(String email, String password) throws EtAuthException;

    Integer getCountByEmail(String email);

    User finById(Integer userId);


}
