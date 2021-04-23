package com.github.fwidder.smartgarden.service.interfaces;

import com.github.fwidder.smartgarden.config.GPIOPumpOutputPin;
import com.github.fwidder.smartgarden.model.ui.PumpData;

import java.util.Arrays;
import java.util.Map;

public interface PumpServiceInterface {
    default void pumpTest() throws InterruptedException {
        Arrays.stream(GPIOPumpOutputPin.values()).forEach(gpioPumpOutputPin -> {
            setPump(gpioPumpOutputPin, true);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            setAll(false);
        });
    }

    Map<GPIOPumpOutputPin, PumpData> getPumpStatus();

    void setPump(GPIOPumpOutputPin pumpOutputPin, boolean state);

    void setAll(boolean state);
}
