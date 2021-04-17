package com.github.fwidder.smartgarden.service.impl;

import com.github.fwidder.smartgarden.config.ArduinoWaterSensorInputPin;
import com.github.fwidder.smartgarden.model.ui.SensorData;
import com.github.fwidder.smartgarden.service.interfaces.ArduinoADCServiceInterface;
import com.github.fwidder.smartgarden.service.interfaces.SensorServiceInterface;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
@Log4j2
@Getter
public class SensorServiceImpl implements SensorServiceInterface {
    @Getter(AccessLevel.NONE)
    private final ArduinoADCServiceInterface arduinoADCService;
    private Map<ArduinoWaterSensorInputPin, SensorData> sensorDataMap = new HashMap<>();

    public SensorServiceImpl(ArduinoADCServiceInterface arduinoADCService) throws IOException {
        this.arduinoADCService = arduinoADCService;
        testSensor();
    }

    @Override
    public void testSensor() throws IOException {
        refreshSensor();
        sensorDataMap.forEach((inputPin, sensorData) -> log.atInfo().log("Pin {} has Values: {}.",inputPin.getPort(),sensorData.toString()));
    }

    @Override
    public void refreshSensor() throws IOException {
        for(ArduinoWaterSensorInputPin pin: ArduinoWaterSensorInputPin.values()){
            sensorDataMap.remove(pin);
            sensorDataMap.put(pin , SensorData.builder() //
                    .currentAbsolute(arduinoADCService.readSensor(pin))
                    .min(pin.getMin())
                    .max(pin.getMax())
                    .toLow(pin.getToLow())
                    .lastMeasurement(LocalDateTime.now())
                    .name(pin.getName())
                    .build()
            );
        }
    }
}
