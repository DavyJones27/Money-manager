package com.davy.trans.services;

import com.davy.trans.domain.User;
import com.davy.trans.exceptions.EtAuthException;

public interface UserService {

    User validateUser(User user) throws EtAuthException;

    User registerUser(User user) throws EtAuthException;
}
