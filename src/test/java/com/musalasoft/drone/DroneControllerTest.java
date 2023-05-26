package com.musalasoft.drone;

import com.musalasoft.drone.controller.DroneController;
import com.musalasoft.drone.model.Drone;
import com.musalasoft.drone.model.DroneModel;
import com.musalasoft.drone.model.DroneState;
import com.musalasoft.drone.service.DroneService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RunWith(MockitoJUnitRunner.class)
public class DroneControllerTest {

    @Mock
    private DroneService droneService;

    @InjectMocks
    private DroneController droneController;

    @Test
    public void testRegisterDrone() {
        // Prepare test data
        Drone drone = new Drone();

        // Set the expected behavior of the mock service
        Mockito.when(droneService.registerDrone(Mockito.any(Drone.class))).thenReturn(drone);

        // Perform the test
        ResponseEntity<Drone> response = droneController.registerDrone(drone);

        // Assert the response
        Assert.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assert.assertEquals(drone, response.getBody());
    }

}
