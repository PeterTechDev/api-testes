package com.petertech.api.services;

import com.petertech.api.domain.User;

public interface UserService {
    User findById(Integer id);
}
