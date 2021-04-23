package com.github.fwidder.smartgarden.service.interfaces;

import com.github.fwidder.smartgarden.config.ArduinoWaterSensorInputPin;
import com.github.fwidder.smartgarden.model.ui.SensorData;

import java.io.IOException;
import java.util.Map;

public interface SensorServiceInterface {
    void testSensor() throws IOException;

    void refreshSensor() throws IOException;

    SensorData getSensorData(ArduinoWaterSensorInputPin pin) throws IOException;

    Map<ArduinoWaterSensorInputPin, SensorData> getSensorDataMap();
}
