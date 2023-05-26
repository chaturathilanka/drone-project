package com.musalasoft.drone;

import com.musalasoft.drone.exception.DroneNotFoundException;
import com.musalasoft.drone.exception.RestExceptionHandler;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RunWith(MockitoJUnitRunner.class)
public class ExceptionHandlersTest {

    private RestExceptionHandler exceptionHandlerAdvice;

    @Before
    public void setup() {
        exceptionHandlerAdvice = new RestExceptionHandler();
    }

    @Test
    public void testHandleDroneNotFoundException() {
        // Prepare test data
        DroneNotFoundException exception = new DroneNotFoundException("Drone not found");

        // Perform the test
        ResponseEntity<Object> response = exceptionHandlerAdvice.handleDroneNotFoundException(exception);

        // Assert the response
        Assert.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Assert.assertEquals("Drone not found", response.getBody());
    }

}

