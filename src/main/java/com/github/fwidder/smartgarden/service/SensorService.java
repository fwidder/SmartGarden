package com.github.fwidder.smartgarden.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class SensorService {
    private final GPIOService gpioService;

    public SensorService(GPIOService gpioService) {
        this.gpioService = gpioService;
        testSensor();
    }

    private void testSensor() {
    }

    public boolean checkSensor1(){
        return true;
    }

    public boolean checkSensor2(){
        return true;
    }

    public boolean checkSensor3(){
        return false;
    }

    public boolean checkSensor4(){
        return true;
    }
}
