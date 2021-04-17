package com.github.fwidder.smartgarden.service.impl;

import com.github.fwidder.smartgarden.service.interfaces.ArduinoADCServiceInterface;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
@Log4j2
@Getter
public class SensorServiceImpl implements com.github.fwidder.smartgarden.service.interfaces.SensorServiceInterface {
    @Getter(AccessLevel.NONE)
    private final ArduinoADCServiceInterface arduinoADCService;

    private final long sensor1Max;
    private final long sensor1Min;
    private final long sensor2Max;
    private final long sensor2Min;
    private final long sensor3Max;
    private final long sensor3Min;
    private final long sensor4Max;
    private final long sensor4Min;
    private long sensor1Last;
    private long sensor2Last;
    private long sensor3Last;
    private long sensor4Last;
    private LocalDateTime sensor1LastMeasurement;
    private LocalDateTime sensor2LastMeasurement;
    private LocalDateTime sensor3LastMeasurement;
    private LocalDateTime sensor4LastMeasurement;


    public SensorServiceImpl(ArduinoADCServiceInterface arduinoADCService, @Value("${watering.sensor.sensor1.high}") int sensor1Max, @Value("${watering.sensor.sensor1.low}") int sensor1Min, @Value("${watering.sensor.sensor2.high}") int sensor2Max, @Value("${watering.sensor.sensor2.low}") int sensor2Min, @Value("${watering.sensor.sensor3.high}") int sensor3Max, @Value("${watering.sensor.sensor3.low}") int sensor3Min, @Value("${watering.sensor.sensor4.high}") int sensor4Max, @Value("${watering.sensor.sensor4.low}") int sensor4Min) {
        this.arduinoADCService = arduinoADCService;
        this.sensor1Max = sensor1Max;
        this.sensor1Min = sensor1Min;
        this.sensor2Max = sensor2Max;
        this.sensor2Min = sensor2Min;
        this.sensor3Max = sensor3Max;
        this.sensor3Min = sensor3Min;
        this.sensor4Max = sensor4Max;
        this.sensor4Min = sensor4Min;
        testSensor();
    }

    @Override
    public boolean checkSensor1() {
        try {
            sensor1Last = arduinoADCService.readSensor1();
            sensor1LastMeasurement = LocalDateTime.now();
        } catch (IOException e) {
            log.atWarn().log(e);
        }
        return false;
    }

    @Override
    public boolean checkSensor2() {
        try {
            sensor2Last = arduinoADCService.readSensor2();
            sensor2LastMeasurement = LocalDateTime.now();
        } catch (IOException e) {
            log.atWarn().log(e);
        }
        return false;
    }

    @Override
    public boolean checkSensor3() {
        try {
            sensor3Last = arduinoADCService.readSensor3();
            sensor3LastMeasurement = LocalDateTime.now();
        } catch (IOException e) {
            log.atWarn().log(e);
        }
        return false;
    }

    @Override
    public boolean checkSensor4() {
        try {
            sensor4Last = arduinoADCService.readSensor4();
            sensor4LastMeasurement = LocalDateTime.now();
        } catch (IOException e) {
            log.atWarn().log(e);
        }
        return false;
    }
}
