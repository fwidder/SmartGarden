package com.github.fwidder.smartgarden.service.interfaces;

import com.github.fwidder.smartgarden.config.GPIOPumpOutputPin;
import org.springframework.lang.NonNull;

public interface ConfigServiceInterface {
    boolean isDeactivated(GPIOPumpOutputPin pin);
    void setDeactivated(GPIOPumpOutputPin pin,@NonNull Boolean state);
}
