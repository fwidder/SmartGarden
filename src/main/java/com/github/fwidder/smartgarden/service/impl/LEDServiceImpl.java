package com.github.fwidder.smartgarden.service.impl;

import com.github.fwidder.smartgarden.config.GPIOLedOutputPin;
import com.github.fwidder.smartgarden.config.GPIOPumpOutputPin;
import com.github.fwidder.smartgarden.service.interfaces.GPIOServiceInterface;
import org.springframework.stereotype.Service;

@Service
public class LEDServiceImpl implements com.github.fwidder.smartgarden.service.interfaces.LEDServiceInterface {
    private final GPIOServiceInterface gpioService;

    public LEDServiceImpl(GPIOServiceInterface gpioService) throws InterruptedException {
        this.gpioService = gpioService;
        lightTest();
    }

    @Override
    public void setAll(boolean state) {
        setGreen(state);
        setYellow(state);
        setRed(state);
    }

    @Override
    public void setGreen(boolean state) {
        if (state)
            gpioService.enable(GPIOLedOutputPin.LED_GREEN);
        else
            gpioService.disable(GPIOLedOutputPin.LED_GREEN);
    }

    @Override
    public void setYellow(boolean state) {
        if (state)
            gpioService.enable(GPIOLedOutputPin.LED_YELLOW);
        else
            gpioService.disable(GPIOLedOutputPin.LED_YELLOW);
    }

    @Override
    public void setRed(boolean state) {
        if (state)
            gpioService.enable(GPIOLedOutputPin.LED_RED);
        else
            gpioService.disable(GPIOLedOutputPin.LED_RED);
    }
}
