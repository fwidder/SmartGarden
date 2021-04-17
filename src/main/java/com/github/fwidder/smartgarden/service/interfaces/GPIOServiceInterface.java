package com.github.fwidder.smartgarden.service.interfaces;

import com.github.fwidder.smartgarden.config.GPIOOutputPin;

import javax.annotation.PreDestroy;

public interface GPIOServiceInterface {
    void initializePins();

    @PreDestroy
    void destroy();

    void enable(GPIOOutputPin outputPin);

    void disable(GPIOOutputPin outputPin);

    boolean getStatus(GPIOOutputPin outputPin);
}
