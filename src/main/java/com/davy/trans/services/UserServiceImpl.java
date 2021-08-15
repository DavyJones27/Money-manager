package com.davy.trans.services;

import com.davy.trans.domain.User;
import com.davy.trans.exceptions.EtAuthException;
import com.davy.trans.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Pattern;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User validateUser(String email, String password) throws EtAuthException {
        return null;
    }

    @Override
    public User registerUser(User user) throws EtAuthException {
        Pattern pattern = Pattern.compile("^(.+)@(.+)$");

        if (user.getEmail() != null) user.setEmail(user.getEmail().toLowerCase());

        if (!pattern.matcher(user.getEmail()).matches()) throw new EtAuthException("Invalid email Format");

        Integer count = userRepository.getCountByEmail(user.getEmail());

        if (count > 0) throw new EtAuthException("email already in use");

        Integer userId = userRepository.create(user);

        return userRepository.finById(userId);
    }
}
