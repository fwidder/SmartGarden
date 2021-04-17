package com.github.fwidder.smartgarden;

import com.github.fwidder.smartgarden.service.interfaces.ArduinoADCServiceInterface;
import com.github.fwidder.smartgarden.service.interfaces.GPIOServiceInterface;
import com.github.fwidder.smartgarden.service.mock.ArduinoADCServiceMock;
import com.github.fwidder.smartgarden.service.mock.GPIOServiceMock;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@Log4j2
public class IntegrationTests {

    @Autowired
    ArduinoADCServiceInterface arduinoADCService;

    @Autowired
    GPIOServiceInterface gpioService;

    @Test
    @DisplayName("Context Start Test")
    public void appStartTest(){
        log.atInfo().log("App is running!");
    }

    @Test
    @DisplayName("Mock Autoconfiguration Test")
    public void autoConfigureMockTest(){
        assertThat(gpioService instanceof GPIOServiceMock, is(true));
        assertThat(arduinoADCService instanceof ArduinoADCServiceMock, is(true));
    }
}
