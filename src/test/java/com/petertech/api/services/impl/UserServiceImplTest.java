package com.petertech.api.services.impl;

import com.petertech.api.domain.User;
import com.petertech.api.domain.dto.UserDTO;
import com.petertech.api.repositories.UserRepository;
import com.petertech.api.services.exceptions.DataIntegratyViolationException;
import com.petertech.api.services.exceptions.ObjetctNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

class UserServiceImplTest {

    public static final Integer ID      = 1;
    public static final String NAME     = "Peter";
    public static final String EMAIL    = "peter@gmail.com";
    public static final String PASSWORD = "123";
    public static final String OBJECT_NOT_FOUND = "Object not found";
    public static final int INDEX = 0;

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
                .thenThrow(new ObjetctNotFoundException(OBJECT_NOT_FOUND));

        try{
            userService.findById(ID);
        } catch (Exception e) {
            assertEquals(ObjetctNotFoundException.class, e.getClass());
            assertEquals(OBJECT_NOT_FOUND, e.getMessage());
        }

    }

    @Test
    void whenFindAllThenReturnListOfAllUsers() {
        when(userRepository.findAll()).thenReturn(List.of(user));

        List<User> response = userService.findAll();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(User.class, response.get(INDEX).getClass());

        assertEquals(ID, response.get(INDEX).getId());
        assertEquals(NAME, response.get(INDEX).getName());
        assertEquals(PASSWORD, response.get(INDEX).getPassword());
        assertEquals(EMAIL, response.get(INDEX).getEmail());
    }

    @Test
    void whenCreateThenReturnSuccess() {
        when(userRepository.save(any())).thenReturn(user);

        User response = userService.create(userDTO);

        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(PASSWORD, response.getPassword());
        assertEquals(EMAIL, response.getEmail());
    }

    @Test
    void whenCreateThenReturnDataIntegrityViolationException() {
        when(userRepository.findByEmail(anyString()))
                .thenReturn(optionalUser);

        try {
            optionalUser.get().setId(2);
            userService.create(userDTO);
        } catch (Exception e) {
            assertEquals(DataIntegratyViolationException.class, e.getClass());
            assertEquals("E-mail already exists", e.getMessage());
        }
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