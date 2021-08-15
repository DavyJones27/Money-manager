package com.davy.trans.services;

import com.davy.trans.domain.User;
import com.davy.trans.exceptions.EtAuthException;

public interface UserService {

    User validateUser(String email, String password) throws EtAuthException;

    User registerUser(User user) throws EtAuthException;
}
