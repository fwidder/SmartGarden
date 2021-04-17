package com.github.fwidder.smartgarden.service.impl;

import com.github.fwidder.smartgarden.config.GPIOLedOutputPin;
import com.github.fwidder.smartgarden.config.GPIOPumpOutputPin;
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
    private final Map<GPIOPumpOutputPin, GpioPinDigitalOutput> pumpMap;
    private final Map<GPIOLedOutputPin, GpioPinDigitalOutput> ledMap;

    public GPIOServiceImpl() {
        pumpMap = new HashMap<>();
        ledMap = new HashMap<>();
        log.atInfo().log("Creating GPIO Controller.");
        gpioController = GpioFactory.getInstance();
        initializePins();
    }

    @Override
    public void initializePins() {
        log.atInfo().log("Start PIN Init.");
        for (GPIOPumpOutputPin pin : GPIOPumpOutputPin.values()) {
            log.atInfo().log("Init Output Pin \t{}.", pin.toString());
            GpioPinDigitalOutput tmp = gpioController.provisionDigitalOutputPin(pin.getPin(), pin.getName(), pin.getState());
            tmp.setShutdownOptions(true, pin.getState(), PinPullResistance.OFF);
            pumpMap.put(pin, tmp);
        }
        for (GPIOLedOutputPin pin : GPIOLedOutputPin.values()) {
            log.atInfo().log("Init Output Pin \t{}.", pin.toString());
            GpioPinDigitalOutput tmp = gpioController.provisionDigitalOutputPin(pin.getPin(), pin.getName(), pin.getState());
            tmp.setShutdownOptions(true, pin.getState(), PinPullResistance.OFF);
            ledMap.put(pin, tmp);
        }
    }

    @Override
    @PreDestroy
    public void destroy() {
        log.atInfo().log("Closing GPIO Controller.");
        gpioController.shutdown();
    }

    @Override
    public void enable(GPIOPumpOutputPin outputPin) {
        log.atDebug().log("Enable Pin {}.", outputPin.toString());
        pumpMap.get(outputPin).high();
    }

    @Override
    public void disable(GPIOPumpOutputPin outputPin) {
        log.atDebug().log("Disable Pin {}.", outputPin.toString());
        pumpMap.get(outputPin).low();
    }

    @Override
    public boolean getStatus(GPIOPumpOutputPin outputPin) {
        log.atDebug().log("Pin {} has Status {}.", outputPin.toString(), pumpMap.get(outputPin).getState());
        return pumpMap.get(outputPin).getState().isHigh();
    }

    @Override
    public void enable(GPIOLedOutputPin outputPin) {
        log.atDebug().log("Enable Pin {}.", outputPin.toString());
        ledMap.get(outputPin).high();
    }

    @Override
    public void disable(GPIOLedOutputPin outputPin) {
        log.atDebug().log("Disable Pin {}.", outputPin.toString());
        pumpMap.get(outputPin).low();
    }

    @Override
    public boolean getStatus(GPIOLedOutputPin outputPin) {
        log.atDebug().log("Pin {} has Status {}.", outputPin.toString(), ledMap.get(outputPin).getState());
        return ledMap.get(outputPin).getState().isHigh();
    }
}
