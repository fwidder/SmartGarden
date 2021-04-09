package com.github.fwidder.smartgarden.service;

import com.github.fwidder.smartgarden.config.GPIOOutputPin;
import org.springframework.stereotype.Service;

@Service
public class PumpService {
    private final GPIOService gpioService;
    private final LEDService ledService;

    public PumpService(GPIOService gpioService, LEDService ledService) throws InterruptedException {
        this.gpioService = gpioService;
        this.ledService = ledService;
        ledService.setYellow(true);
        setAll(false);
        pumpTest();
        ledService.setYellow(false);
    }

    private void pumpTest() throws InterruptedException {
        setPump1(true);
        Thread.sleep(500);
        setPump1(false);
        setPump2(true);
        Thread.sleep(500);
        setPump2(false);
        setPump3(true);
        Thread.sleep(500);
        setPump3(false);
        setPump4(true);
        Thread.sleep(500);
        setPump4(false);

        setAll(false);
    }

    public void setPump1(boolean state){
        if(state)
            gpioService.disable(GPIOOutputPin.PUMP_1);
        else
            gpioService.enable(GPIOOutputPin.PUMP_1);
    }

    public void setPump2(boolean state){
        if(state)
            gpioService.disable(GPIOOutputPin.PUMP_2);
        else
            gpioService.enable(GPIOOutputPin.PUMP_2);
    }

    public void setPump3(boolean state){
        if(state)
            gpioService.disable(GPIOOutputPin.PUMP_3);
        else
            gpioService.enable(GPIOOutputPin.PUMP_3);
    }

    public void setPump4(boolean state){
        if(state)
            gpioService.disable(GPIOOutputPin.PUMP_4);
        else
            gpioService.enable(GPIOOutputPin.PUMP_4);
    }

    public void setAll(boolean state){
        setPump1(state);
        setPump2(state);
        setPump3(state);
        setPump4(state);
    }
}
