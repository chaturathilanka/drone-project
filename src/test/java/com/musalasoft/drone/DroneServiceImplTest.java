package com.musalasoft.drone;

import com.musalasoft.drone.model.Drone;
import com.musalasoft.drone.repository.DroneRepository;
import com.musalasoft.drone.repository.MedicationRepository;
import com.musalasoft.drone.service.impl.DroneServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DroneServiceImplTest {

    @Mock
    private DroneRepository droneRepository;

    @Mock
    private MedicationRepository medicationRepository;

    @InjectMocks
    private DroneServiceImpl droneService;

    @Test
    public void testRegisterDrone() {
        // Prepare test data
        Drone drone = new Drone();
        // Set the expected behavior of the mock repository
        Mockito.when(droneRepository.save(Mockito.any(Drone.class))).thenReturn(drone);

        // Perform the test
        Drone result = droneService.registerDrone(drone);

        // Assert the result
        Assert.assertEquals(drone, result);
    }
}

