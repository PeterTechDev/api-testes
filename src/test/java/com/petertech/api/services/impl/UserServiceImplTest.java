package com.petertech.api.services.impl;

import com.petertech.api.domain.User;
import com.petertech.api.domain.dto.UserDTO;
import com.petertech.api.repositories.UserRepository;
import com.petertech.api.services.exceptions.ObjetctNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

class UserServiceImplTest {

    public static final Integer ID      = 1;
    public static final String NAME     = "Peter";
    public static final String EMAIL    = "peter@gmail.com";
    public static final String PASSWORD = "123";

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;
    @Mock
    private ModelMapper mapper;

    private User user;
    private UserDTO userDTO;
    private Optional<User> optionalUser;

    @BeforeEach
    void setUp() {
        startUser();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenFindByIdThenReturnAnUserInstance() {
        when(userRepository.findById(anyInt())).thenReturn(optionalUser);

        User response = userService.findById(ID);

        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(PASSWORD, response.getPassword());
    }

    @Test
    void whenFindByNameThenReturnAnObjectNotFoundException() {
        when(userRepository.findById(anyInt()))
                .thenThrow(new ObjetctNotFoundException("Object not found"));

        try{
            userService.findById(ID);
        } catch (Exception e) {
            assertEquals(ObjetctNotFoundException.class, e.getClass());
            assertEquals("Object not found", e.getMessage());
        }

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
        optionalUser = Optional.of(new User(ID, NAME, EMAIL, PASSWORD));
    }
}