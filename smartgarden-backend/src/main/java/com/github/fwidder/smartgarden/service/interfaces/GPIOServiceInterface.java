package com.github.fwidder.smartgarden.service.interfaces;

import com.github.fwidder.smartgarden.config.GPIOLedOutputPin;
import com.github.fwidder.smartgarden.config.GPIOPumpOutputPin;

import javax.annotation.PreDestroy;

public interface GPIOServiceInterface {
    void initializePins();

    @PreDestroy
    void destroy();

    void enable(GPIOPumpOutputPin outputPin);

    void disable(GPIOPumpOutputPin outputPin);

    boolean getStatus(GPIOPumpOutputPin outputPin);

    void enable(GPIOLedOutputPin outputPin);

    void disable(GPIOLedOutputPin outputPin);

    boolean getStatus(GPIOLedOutputPin outputPin);
}
