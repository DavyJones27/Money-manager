package com.davy.trans.repositories;

import com.davy.trans.domain.User;
import com.davy.trans.exceptions.EtAuthException;

public interface UserRepository {

    Integer create(User user) throws EtAuthException;

    User findByEmailAndPassword(User user) throws EtAuthException;

    Integer getCountByEmail(String email);

    User finById(Integer userId);


}
