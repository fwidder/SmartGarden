package com.github.fwidder.smartgarden.service;

import com.github.fwidder.smartgarden.config.GPIOOutputPin;
import org.springframework.stereotype.Service;

@Service
public class LEDService {
    private final GPIOService gpioService;

    public LEDService(GPIOService gpioService) throws InterruptedException {
        this.gpioService = gpioService;
        lightTest();
    }

    private void lightTest() throws InterruptedException {
        setRed(true);
        Thread.sleep(1000);
        setYellow(true);
        Thread.sleep(1000);
        setGreen(true);
        Thread.sleep(1000);
        setAll(false);
    }

    public void setAll(boolean state){
        setGreen(state);
        setYellow(state);
        setRed(state);
    }

    public void setGreen(boolean state){
        if(state)
            gpioService.enable(GPIOOutputPin.LED_GREEN);
        else
            gpioService.disable(GPIOOutputPin.LED_GREEN);
    }

    public void setYellow(boolean state){
        if(state)
            gpioService.enable(GPIOOutputPin.LED_YELLOW);
        else
            gpioService.disable(GPIOOutputPin.LED_YELLOW);
    }

    public void setRed(boolean state){
        if(state)
            gpioService.enable(GPIOOutputPin.LED_RED);
        else
            gpioService.disable(GPIOOutputPin.LED_RED);
    }
}
