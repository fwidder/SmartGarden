package com.github.fwidder.smartgarden.service;

import lombok.extern.log4j.Log4j2;
import org.firmata4j.IODevice;
import org.firmata4j.Pin;
import org.firmata4j.firmata.FirmataDevice;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import java.io.IOException;

@Service
@Log4j2
public class ArduinoADCService {
    private final IODevice arduino;

    public ArduinoADCService(@Value("${arduino.port}") String arduinoPort) throws IOException, InterruptedException {
        log.atInfo().log("Creating Arduino at Port {}.", arduinoPort);
        this.arduino = new FirmataDevice(arduinoPort);
        log.atInfo().log("Starting and Initializing Arduino.");
        arduino.start();
        arduino.ensureInitializationIsDone();
        log.atInfo().log("Arduino is ready.");
    }

    @PreDestroy
    public void destroy() throws IOException {
        log.atInfo().log("Closing Arduino.");
        arduino.stop();
    }

    @Scheduled(fixedDelay = 1000L)
    public void readSensorTest() throws IOException {
        arduino.getPin(14).setMode(Pin.Mode.ANALOG);
        long val = arduino.getPin(14).getValue();
        log.atInfo().log("Pin 14 has Value {}.", val);
        arduino.getPin(15).setMode(Pin.Mode.ANALOG);
        val = arduino.getPin(15).getValue();
        log.atInfo().log("Pin 15 has Value {}.", val);
        arduino.getPin(16).setMode(Pin.Mode.ANALOG);
        val = arduino.getPin(16).getValue();
        log.atInfo().log("Pin 16 has Value {}.", val);
        arduino.getPin(17).setMode(Pin.Mode.ANALOG);
        val = arduino.getPin(17).getValue();
        log.atInfo().log("Pin 17 has Value {}.", val);
    }
}
