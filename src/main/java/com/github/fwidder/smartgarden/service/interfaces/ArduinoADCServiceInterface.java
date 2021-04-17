package com.github.fwidder.smartgarden.service.interfaces;

import com.github.fwidder.smartgarden.config.ArduinoWaterSensorInputPin;

import javax.annotation.PreDestroy;
import java.io.IOException;

public interface ArduinoADCServiceInterface {
    @PreDestroy
    void destroy() throws IOException;

    void readSensorTest() throws IOException;

    long readSensor(ArduinoWaterSensorInputPin inputPin) throws IOException;
}
