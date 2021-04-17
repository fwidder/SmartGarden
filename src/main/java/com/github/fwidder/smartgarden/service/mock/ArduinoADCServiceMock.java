package com.github.fwidder.smartgarden.service.mock;

import lombok.extern.log4j.Log4j2;
import org.firmata4j.IODevice;
import org.firmata4j.Pin;
import org.firmata4j.firmata.FirmataDevice;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import java.io.IOException;

@Service
@Log4j2
@ConditionalOnProperty(
        value="application.mock",
        havingValue = "true")
public class ArduinoADCServiceMock implements com.github.fwidder.smartgarden.service.interfaces.ArduinoADCServiceInterface {
    private final int sensor1Port;
    private final int sensor2Port;
    private final int sensor3Port;
    private final int sensor4Port;

    public ArduinoADCServiceMock(@Value("${arduino.port}") String arduinoPort, @Value("${watering.sensor.sensor1.port}") int sensor1Port, @Value("${watering.sensor.sensor2.port}") int sensor2Port, @Value("${watering.sensor.sensor3.port}") int sensor3Port, @Value("${watering.sensor.sensor4.port}") int sensor4Port) throws IOException, InterruptedException {
        log.atInfo().log("Using ArduinoADCServiceMock");
        this.sensor1Port = sensor1Port;
        this.sensor2Port = sensor2Port;
        this.sensor3Port = sensor3Port;
        this.sensor4Port = sensor4Port;
        log.atInfo().log("Creating Arduino at Port {}.", arduinoPort);
        log.atInfo().log("Starting and Initializing Arduino.");
        readSensorTest();
        log.atInfo().log("Arduino is ready.");
    }

    @Override
    @PreDestroy
    public void destroy() throws IOException {
        log.atInfo().log("Closing Arduino.");
    }

    @Override
    public void readSensorTest() throws IOException {
        log.atInfo().log("Pin {} has Value {}.", sensor1Port, readSensor1());
        log.atInfo().log("Pin {} has Value {}.", sensor2Port, readSensor2());
        log.atInfo().log("Pin {} has Value {}.", sensor3Port, readSensor3());
        log.atInfo().log("Pin {} has Value {}.", sensor4Port, readSensor4());
    }

    @Override
    public long readSensor1() throws IOException {
        long val = 300;
        log.atDebug().log("Pin {} has Value {}.", sensor1Port, val);
        return  val;
    }

    @Override
    public long readSensor2() throws IOException {
        long val = 400;
        log.atDebug().log("Pin {} has Value {}.", sensor2Port, val);
        return  val;
    }

    @Override
    public long readSensor3() throws IOException {
        long val = 500;
        log.atDebug().log("Pin {} has Value {}.", sensor3Port, val);
        return  val;
    }

    @Override
    public long readSensor4() throws IOException {
        long val = 600;
        log.atDebug().log("Pin {} has Value {}.", sensor4Port, val);
        return  val;
    }

}
