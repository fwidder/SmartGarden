package com.github.fwidder.smartgarden.service.impl;

import com.github.fwidder.smartgarden.config.GPIOLedOutputPin;
import com.github.fwidder.smartgarden.service.interfaces.GPIOServiceInterface;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
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
        log.atDebug().log("Set green to {}.", state);
        if (state)
            gpioService.enable(GPIOLedOutputPin.LED_GREEN);
        else
            gpioService.disable(GPIOLedOutputPin.LED_GREEN);
    }

    @Override
    public void setYellow(boolean state) {
        log.atDebug().log("Set yellow to {}.", state);
        if (state)
            gpioService.enable(GPIOLedOutputPin.LED_YELLOW);
        else
            gpioService.disable(GPIOLedOutputPin.LED_YELLOW);
    }

    @Override
    public void setRed(boolean state) {
        log.atDebug().log("Set red to {}.", state);
        if (state)
            gpioService.enable(GPIOLedOutputPin.LED_RED);
        else
            gpioService.disable(GPIOLedOutputPin.LED_RED);
    }
}
