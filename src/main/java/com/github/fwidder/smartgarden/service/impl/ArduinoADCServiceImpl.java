package com.github.fwidder.smartgarden.service.impl;

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
        havingValue = "false",
        matchIfMissing = true)
public class ArduinoADCServiceImpl implements com.github.fwidder.smartgarden.service.interfaces.ArduinoADCServiceInterface {
    private final IODevice arduino;
    private final int sensor1Port;
    private final int sensor2Port;
    private final int sensor3Port;
    private final int sensor4Port;

    public ArduinoADCServiceImpl(@Value("${arduino.port}") String arduinoPort, @Value("${watering.sensor.sensor1.port}") int sensor1Port, @Value("${watering.sensor.sensor2.port}") int sensor2Port, @Value("${watering.sensor.sensor3.port}") int sensor3Port, @Value("${watering.sensor.sensor4.port}") int sensor4Port) throws IOException, InterruptedException {
        this.sensor1Port = sensor1Port;
        this.sensor2Port = sensor2Port;
        this.sensor3Port = sensor3Port;
        this.sensor4Port = sensor4Port;
        log.atInfo().log("Creating Arduino at Port {}.", arduinoPort);
        this.arduino = new FirmataDevice(arduinoPort);
        log.atInfo().log("Starting and Initializing Arduino.");
        arduino.start();
        arduino.ensureInitializationIsDone();
        readSensorTest();
        log.atInfo().log("Arduino is ready.");
    }

    @Override
    @PreDestroy
    public void destroy() throws IOException {
        log.atInfo().log("Closing Arduino.");
        arduino.stop();
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
        arduino.getPin(sensor1Port).setMode(Pin.Mode.ANALOG);
        long val = arduino.getPin(sensor1Port).getValue();
        log.atDebug().log("Pin {} has Value {}.", sensor1Port, val);
        return  val;
    }

    @Override
    public long readSensor2() throws IOException {
        arduino.getPin(sensor2Port).setMode(Pin.Mode.ANALOG);
        long val = arduino.getPin(sensor2Port).getValue();
        log.atDebug().log("Pin {} has Value {}.", sensor2Port, val);
        return  val;
    }

    @Override
    public long readSensor3() throws IOException {
        arduino.getPin(sensor3Port).setMode(Pin.Mode.ANALOG);
        long val = arduino.getPin(sensor3Port).getValue();
        log.atDebug().log("Pin {} has Value {}.", sensor3Port, val);
        return  val;
    }

    @Override
    public long readSensor4() throws IOException {
        arduino.getPin(sensor4Port).setMode(Pin.Mode.ANALOG);
        long val = arduino.getPin(sensor4Port).getValue();
        log.atDebug().log("Pin {} has Value {}.", sensor4Port, val);
        return  val;
    }

}
