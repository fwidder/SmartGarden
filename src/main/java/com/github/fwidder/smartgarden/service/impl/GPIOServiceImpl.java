package com.github.fwidder.smartgarden.service.impl;

import com.github.fwidder.smartgarden.config.GPIOInputPin;
import com.github.fwidder.smartgarden.config.GPIOOutputPin;
import com.pi4j.io.gpio.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import java.util.HashMap;
import java.util.Map;

@Service
@Log4j2
@ConditionalOnProperty(
        value="application.mock",
        havingValue = "false",
        matchIfMissing = true)
public class GPIOServiceImpl implements com.github.fwidder.smartgarden.service.interfaces.GPIOServiceInterface {
    private final GpioController gpioController;
    private final Map<GPIOOutputPin, GpioPinDigitalOutput> outputMap;
    private final Map<GPIOInputPin, GpioPinDigitalInput> inputMap;

    public GPIOServiceImpl() {
        inputMap = new HashMap<>();
        outputMap = new HashMap<>();
        log.atInfo().log("Creating GPIO Controller.");
        gpioController = GpioFactory.getInstance();
        initializePins();
    }

    @Override
    public void initializePins() {
        log.atInfo().log("Start PIN Init.");
        for (GPIOInputPin pin : GPIOInputPin.values()) {
            log.atInfo().log("Init Input Pin \t{}.", pin.toString());
            GpioPinDigitalInput tmp = gpioController.provisionDigitalInputPin(pin.getPin(), pin.getName());
        }
        for (GPIOOutputPin pin : GPIOOutputPin.values()) {
            log.atInfo().log("Init Output Pin \t{}.", pin.toString());
            GpioPinDigitalOutput tmp = gpioController.provisionDigitalOutputPin(pin.getPin(), pin.getName(), pin.getState());
            tmp.setShutdownOptions(true, pin.getState(), PinPullResistance.OFF);
            outputMap.put(pin, tmp);
        }
    }

    @Override
    @PreDestroy
    public void destroy() {
        log.atInfo().log("Closing GPIO Controller.");
        gpioController.shutdown();
    }

    @Override
    public void enable(GPIOOutputPin outputPin) {
        log.atDebug().log("Enable Pin {}.", outputPin.toString());
        outputMap.get(outputPin).high();
    }

    @Override
    public void disable(GPIOOutputPin outputPin) {
        log.atDebug().log("Disable Pin {}.", outputPin.toString());
        outputMap.get(outputPin).low();
    }

    @Override
    public boolean getStatus(GPIOOutputPin outputPin) {
        log.atDebug().log("Pin {} has Status {}.", outputPin.toString(), outputMap.get(outputPin).getState());
        return outputMap.get(outputPin).getState().isHigh();
    }
}