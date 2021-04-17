package com.github.fwidder.smartgarden.service.mock;

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
        havingValue = "true")
public class GPIOServiceMock implements com.github.fwidder.smartgarden.service.interfaces.GPIOServiceInterface {

    private final Map<GPIOOutputPin, Boolean> outputMap;
    private final Map<GPIOInputPin, Boolean> inputMap;

    public GPIOServiceMock() {
        log.atInfo().log("Using GPIOServiceMock");
        outputMap = new HashMap<>();
        inputMap = new HashMap<>();
        log.atInfo().log("Creating GPIO Controller.");
        initializePins();
    }

    @Override
    public void initializePins() {
        log.atInfo().log("Start PIN Init.");
        for (GPIOInputPin pin : GPIOInputPin.values()) {
            log.atInfo().log("Init Input Pin \t{}.", pin.toString());
            inputMap.put(pin, false);
        }
        for (GPIOOutputPin pin : GPIOOutputPin.values()) {
            log.atInfo().log("Init Output Pin \t{}.", pin.toString());
            outputMap.put(pin, pin.getState().isHigh());
        }

    }

    @Override
    @PreDestroy
    public void destroy() {
        log.atInfo().log("Closing GPIO Controller.");
    }

    @Override
    public void enable(GPIOOutputPin outputPin) {
        log.atDebug().log("Enable Pin {}.", outputPin.toString());
        outputMap.remove(outputPin);
        outputMap.put(outputPin, true);
    }

    @Override
    public void disable(GPIOOutputPin outputPin) {
        log.atDebug().log("Disable Pin {}.", outputPin.toString());
        outputMap.remove(outputPin);
        outputMap.put(outputPin, false);
    }

    @Override
    public boolean getStatus(GPIOOutputPin outputPin) {
        return outputMap.get(outputPin);
    }
}
