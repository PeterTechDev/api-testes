package com.petertech.api.services.impl;

import com.petertech.api.domain.User;
import com.petertech.api.repositories.UserRepository;
import com.petertech.api.services.UserService;
import com.petertech.api.services.exceptions.ObjetctNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findById(Integer id) {
       Optional<User> user = userRepository.findById(id);

       return user.orElseThrow(() -> new ObjetctNotFoundException("Object not found: " + id));
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}
