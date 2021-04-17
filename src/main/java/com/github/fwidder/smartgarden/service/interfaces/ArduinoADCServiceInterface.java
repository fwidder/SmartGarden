package com.github.fwidder.smartgarden.service.interfaces;

import javax.annotation.PreDestroy;
import java.io.IOException;

public interface ArduinoADCServiceInterface {
    @PreDestroy
    void destroy() throws IOException;

    void readSensorTest() throws IOException;

    long readSensor1() throws IOException;

    long readSensor2() throws IOException;

    long readSensor3() throws IOException;

    long readSensor4() throws IOException;
}
