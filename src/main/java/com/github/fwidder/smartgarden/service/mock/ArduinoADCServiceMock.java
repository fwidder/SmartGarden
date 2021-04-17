package com.github.fwidder.smartgarden.service.mock;

import com.github.fwidder.smartgarden.config.ArduinoWaterSensorInputPin;
import com.github.fwidder.smartgarden.service.interfaces.ArduinoADCServiceInterface;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Random;

@Service
@Log4j2
@ConditionalOnProperty(
        value="application.mock",
        havingValue = "true")
public class ArduinoADCServiceMock implements ArduinoADCServiceInterface {

    public ArduinoADCServiceMock(@Value("${arduino.port}") String arduinoPort) throws IOException, InterruptedException {
        log.atInfo().log("Using ArduinoADCServiceMock");
        log.atInfo().log("Creating Arduino at Port {}.", arduinoPort);
        log.atInfo().log("Starting and Initializing Arduino.");
        readSensorTest();
        log.atInfo().log("Arduino is ready.");
    }

    @Override
    public void destroy() throws IOException {
        log.atInfo().log("Arduino is closing.");
    }

    @Override
    public void readSensorTest() throws IOException {
        log.atInfo().log("Doing readSensorTest.");
    }

    @Override
    public long readSensor(ArduinoWaterSensorInputPin inputPin) throws IOException {
        return new Random().nextInt(350) + 300;
    }
}
