package com.petertech.api.resources;

import com.petertech.api.domain.User;
import com.petertech.api.domain.dto.UserDTO;
import com.petertech.api.services.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserResourceTest {
    //TODO: Implement factory

    public static final Integer ID      = 1;
    public static final String NAME     = "Peter";
    public static final String EMAIL    = "peter@gmail.com";
    public static final String PASSWORD = "123";
    public static final String OBJECT_NOT_FOUND = "Object not found";
    public static final int INDEX = 0;
    public static final String EMAIL_ALREADY_EXISTS = "E-mail already exists";

    private User user;
    private UserDTO userDTO;

    @InjectMocks
    private UserResource resource;

    @Mock
    private UserServiceImpl service;

    @Mock
    private ModelMapper mapper;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void findById() {
    }

    @Test
    void findAll() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    private void startUser() {
        user = new User(ID, NAME, EMAIL, PASSWORD);
        userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);
    }
}