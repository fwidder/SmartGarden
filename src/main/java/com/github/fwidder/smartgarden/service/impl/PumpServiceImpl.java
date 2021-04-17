package com.github.fwidder.smartgarden.service.impl;

import com.github.fwidder.smartgarden.config.GPIOOutputPin;
import com.github.fwidder.smartgarden.service.interfaces.GPIOServiceInterface;
import com.github.fwidder.smartgarden.service.interfaces.LEDServiceInterface;
import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Getter
public class PumpServiceImpl implements com.github.fwidder.smartgarden.service.interfaces.PumpServiceInterface {
    @Getter(AccessLevel.NONE)
    private final GPIOServiceInterface gpioService;
    @Getter(AccessLevel.NONE)
    private final LEDServiceInterface ledService;
    private LocalDateTime pump1LastChange;
    private LocalDateTime pump2LastChange;
    private LocalDateTime pump3LastChange;
    private LocalDateTime pump4LastChange;

    public PumpServiceImpl(GPIOServiceInterface gpioService, LEDServiceInterface ledService) throws InterruptedException {
        this.gpioService = gpioService;
        this.ledService = ledService;
        ledService.setYellow(true);
        setAll(false);
        pumpTest();
        ledService.setYellow(false);
    }

    @Override
    public boolean getPump1Status(){
        return !gpioService.getStatus(GPIOOutputPin.PUMP_1);
    }

    @Override
    public boolean getPump2Status(){
        return !gpioService.getStatus(GPIOOutputPin.PUMP_2);
    }

    @Override
    public boolean getPump3Status(){
        return !gpioService.getStatus(GPIOOutputPin.PUMP_3);
    }

    @Override
    public boolean getPump4Status(){
        return !gpioService.getStatus(GPIOOutputPin.PUMP_4);
    }

    @Override
    public void setPump1(boolean state) {
        if (state)
            gpioService.disable(GPIOOutputPin.PUMP_1);
        else
            gpioService.enable(GPIOOutputPin.PUMP_1);
        pump1LastChange = LocalDateTime.now();
    }

    @Override
    public void setPump2(boolean state) {
        if (state)
            gpioService.disable(GPIOOutputPin.PUMP_2);
        else
            gpioService.enable(GPIOOutputPin.PUMP_2);
        pump2LastChange = LocalDateTime.now();
    }

    @Override
    public void setPump3(boolean state) {
        if (state)
            gpioService.disable(GPIOOutputPin.PUMP_3);
        else
            gpioService.enable(GPIOOutputPin.PUMP_3);
        pump3LastChange = LocalDateTime.now();
    }

    @Override
    public void setPump4(boolean state) {
        if (state)
            gpioService.disable(GPIOOutputPin.PUMP_4);
        else
            gpioService.enable(GPIOOutputPin.PUMP_4);
        pump4LastChange = LocalDateTime.now();
    }

    @Override
    public void setAll(boolean state) {
        setPump1(state);
        setPump2(state);
        setPump3(state);
        setPump4(state);
    }
}
