package com.github.fwidder.smartgarden.service;

import com.github.fwidder.smartgarden.config.GPIOOutputPin;
import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Getter
public class PumpService {
    @Getter(AccessLevel.NONE)
    private final GPIOService gpioService;
    @Getter(AccessLevel.NONE)
    private final LEDService ledService;
    private LocalDateTime pump1LastChange;
    private LocalDateTime pump2LastChange;
    private LocalDateTime pump3LastChange;
    private LocalDateTime pump4LastChange;

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

    public boolean getPump1Status(){
        return !gpioService.getStatus(GPIOOutputPin.PUMP_1);
    }

    public boolean getPump2Status(){
        return !gpioService.getStatus(GPIOOutputPin.PUMP_2);
    }

    public boolean getPump3Status(){
        return !gpioService.getStatus(GPIOOutputPin.PUMP_3);
    }

    public boolean getPump4Status(){
        return !gpioService.getStatus(GPIOOutputPin.PUMP_4);
    }

    public void setPump1(boolean state) {
        if (state)
            gpioService.disable(GPIOOutputPin.PUMP_1);
        else
            gpioService.enable(GPIOOutputPin.PUMP_1);
        pump1LastChange = LocalDateTime.now();
    }

    public void setPump2(boolean state) {
        if (state)
            gpioService.disable(GPIOOutputPin.PUMP_2);
        else
            gpioService.enable(GPIOOutputPin.PUMP_2);
        pump2LastChange = LocalDateTime.now();
    }

    public void setPump3(boolean state) {
        if (state)
            gpioService.disable(GPIOOutputPin.PUMP_3);
        else
            gpioService.enable(GPIOOutputPin.PUMP_3);
        pump3LastChange = LocalDateTime.now();
    }

    public void setPump4(boolean state) {
        if (state)
            gpioService.disable(GPIOOutputPin.PUMP_4);
        else
            gpioService.enable(GPIOOutputPin.PUMP_4);
        pump4LastChange = LocalDateTime.now();
    }

    public void setAll(boolean state) {
        setPump1(state);
        setPump2(state);
        setPump3(state);
        setPump4(state);
    }
}
