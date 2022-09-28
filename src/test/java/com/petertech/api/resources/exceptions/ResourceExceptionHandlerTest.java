package com.petertech.api.resources.exceptions;

import com.petertech.api.services.exceptions.DataIntegratyViolationException;
import com.petertech.api.services.exceptions.ObjetctNotFoundException;
import org.hibernate.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ResourceExceptionHandlerTest {

    public static final String OBJECT_NOT_FOUND = "Object not found";
    public static final String EMAIL_ALREADY_EXISTS = "E-mail already exists";
    @InjectMocks
    private ResourceExceptionHandler exceptionHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenObjectNotFoundExceptionThenReturnResponseEntity() {
        ResponseEntity<StandartError> response = exceptionHandler
                .objectNotFound(
                        new ObjetctNotFoundException(OBJECT_NOT_FOUND),
                        new MockHttpServletRequest());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(404, response.getBody().getStatus());
        assertEquals(OBJECT_NOT_FOUND, response.getBody().getError());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(StandartError.class, response.getBody().getClass());
        assertNotEquals("/user/2", response.getBody().getPath());
        assertNotEquals(LocalDateTime.now(), response.getBody().getTimestamp());
    }

    @Test
    void whenDataIntegrityViolationExceptionThenReturnResponseEntity() {
        ResponseEntity<StandartError> response = exceptionHandler
                .dataIntegrityViolation(
                        new DataIntegratyViolationException(EMAIL_ALREADY_EXISTS),
                        new MockHttpServletRequest());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(400, response.getBody().getStatus());
        assertEquals(EMAIL_ALREADY_EXISTS, response.getBody().getError());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(StandartError.class, response.getBody().getClass());
    }
}