package com.github.fwidder.smartgarden.service.impl;

import com.github.fwidder.smartgarden.config.ArduinoWaterSensorInputPin;
import lombok.extern.log4j.Log4j2;
import org.firmata4j.IODevice;
import org.firmata4j.Pin;
import org.firmata4j.firmata.FirmataDevice;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import java.io.IOException;
import java.util.Arrays;

@Service
@Log4j2
@ConditionalOnProperty(
        value="application.mock",
        havingValue = "false",
        matchIfMissing = true)
public class ArduinoADCServiceImpl implements com.github.fwidder.smartgarden.service.interfaces.ArduinoADCServiceInterface {
    private final IODevice arduino;

    public ArduinoADCServiceImpl(@Value("${arduino.port}") String arduinoPort) throws IOException, InterruptedException {
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
        Arrays.stream(ArduinoWaterSensorInputPin.values()).forEach(inputPin -> {
            try {
                log.atInfo().log("Pin {} has Value {}.", inputPin.getName(), readSensor(inputPin));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public long readSensor(ArduinoWaterSensorInputPin inputPin) throws IOException {
        arduino.getPin(inputPin.getPort()).setMode(Pin.Mode.ANALOG);
        long val = arduino.getPin(inputPin.getPort()).getValue();
        log.atDebug().log("Port {} has Value {}.", inputPin.getName(), val);
        return  val;
    }
}
