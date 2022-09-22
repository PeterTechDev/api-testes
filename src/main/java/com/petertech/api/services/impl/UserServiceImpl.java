package com.petertech.api.services.impl;

import com.petertech.api.domain.User;
import com.petertech.api.domain.dto.UserDTO;
import com.petertech.api.repositories.UserRepository;
import com.petertech.api.services.UserService;
import com.petertech.api.services.exceptions.DataIntegratyViolationException;
import com.petertech.api.services.exceptions.ObjetctNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public User findById(Integer id) {
       Optional<User> user = userRepository.findById(id);

       return user.orElseThrow(() -> new ObjetctNotFoundException("Object not found: " + id));
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User create(UserDTO obj) {
        findByEmail(obj);
        return userRepository.save(mapper.map(obj, User.class));
    }

    @Override
    public User update(UserDTO obj) {
        findByEmail(obj);
        return userRepository.save(mapper.map(obj, User.class));
    }

    private void findByEmail(UserDTO obj) {
        Optional<User> user = userRepository.findByEmail(obj.getEmail());
        if (user.isPresent() && !user.get().getId().equals(obj.getId())) {
            throw new DataIntegratyViolationException("E-mail already exists");
        }
    }
}
