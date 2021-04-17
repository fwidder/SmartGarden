package com.github.fwidder.smartgarden.service.mock;

import com.github.fwidder.smartgarden.config.GPIOLedOutputPin;
import com.github.fwidder.smartgarden.config.GPIOPumpOutputPin;
import com.github.fwidder.smartgarden.service.interfaces.GPIOServiceInterface;
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
public class GPIOServiceMock implements GPIOServiceInterface {

    private final Map<GPIOPumpOutputPin, Boolean> pumpMap;
    private final Map<GPIOLedOutputPin, Boolean> ledMap;

    public GPIOServiceMock() {
        log.atInfo().log("Using GPIOServiceMock");
        pumpMap = new HashMap<>();
        ledMap = new HashMap<>();
        log.atInfo().log("Creating GPIO Controller.");
        initializePins();
    }

    @Override
    public void initializePins() {
        log.atInfo().log("Start PIN Init.");
        for (GPIOPumpOutputPin pin : GPIOPumpOutputPin.values()) {
            log.atInfo().log("Init Output Pin \t{}.", pin.toString());
            pumpMap.put(pin, pin.getState().isHigh());
        }
        for (GPIOLedOutputPin pin : GPIOLedOutputPin.values()) {
            log.atInfo().log("Init Output Pin \t{}.", pin.toString());
            ledMap.put(pin, pin.getState().isHigh());
        }

    }

    @Override
    @PreDestroy
    public void destroy() {
        log.atInfo().log("Closing GPIO Controller.");
    }

    @Override
    public void enable(GPIOPumpOutputPin outputPin) {
        log.atDebug().log("Enable Pin {}.", outputPin.toString());
        pumpMap.remove(outputPin);
        pumpMap.put(outputPin, true);
    }

    @Override
    public void disable(GPIOPumpOutputPin outputPin) {
        log.atDebug().log("Disable Pin {}.", outputPin.toString());
        pumpMap.remove(outputPin);
        pumpMap.put(outputPin, false);
    }

    @Override
    public boolean getStatus(GPIOPumpOutputPin outputPin) {
        return pumpMap.get(outputPin);
    }

    @Override
    public void enable(GPIOLedOutputPin outputPin) {
        log.atDebug().log("Enable Pin {}.", outputPin.toString());
        ledMap.remove(outputPin);
        ledMap.put(outputPin, true);

    }

    @Override
    public void disable(GPIOLedOutputPin outputPin) {
        log.atDebug().log("Disable Pin {}.", outputPin.toString());
        ledMap.remove(outputPin);
        ledMap.put(outputPin, false);

    }

    @Override
    public boolean getStatus(GPIOLedOutputPin outputPin) {
        return ledMap.get(outputPin);
    }
}
