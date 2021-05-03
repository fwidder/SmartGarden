package com.github.fwidder.smartgarden.service.impl;

import com.github.fwidder.smartgarden.config.ArduinoWaterSensorInputPin;
import com.github.fwidder.smartgarden.dao.WaterSensorEventRepository;
import com.github.fwidder.smartgarden.model.db.WaterSensorEvent;
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
    @Getter(AccessLevel.NONE)
    private final WaterSensorEventRepository waterSensorEventRepository;
    private Map<ArduinoWaterSensorInputPin, SensorData> sensorDataMap = new HashMap<>();

    public SensorServiceImpl(ArduinoADCServiceInterface arduinoADCService, WaterSensorEventRepository waterSensorEventRepository) throws IOException {
        this.arduinoADCService = arduinoADCService;
        this.waterSensorEventRepository = waterSensorEventRepository;
        testSensor();
    }

    @Override
    public void testSensor() throws IOException {
        refreshSensor();
        sensorDataMap.forEach((inputPin, sensorData) -> log.atInfo().log("Pin {} has Values: {}.", inputPin.getPort(), sensorData.toString()));
    }

    @Override
    public void refreshSensor() throws IOException {
        for (ArduinoWaterSensorInputPin pin : ArduinoWaterSensorInputPin.values()) {
            sensorDataMap.remove(pin);
            SensorData data;
            sensorDataMap.put(pin, data = getSensorData(pin));
            waterSensorEventRepository.save(WaterSensorEvent.builder() //
                    .sensor(pin) //
                    .time(LocalDateTime.now()) //
                    .value(data.getCurrentAbsolute()) //
                    .build());
        }
    }
    @Override
    public SensorData getSensorData(ArduinoWaterSensorInputPin pin) throws IOException {
        return SensorData.builder() //
                .currentAbsolute(arduinoADCService.readSensor(pin))
                .min(pin.getMin())
                .max(pin.getMax())
                .toLow(pin.getToLow())
                .lastMeasurement(LocalDateTime.now())
                .name(pin.getName())
                .build();
    }
}
