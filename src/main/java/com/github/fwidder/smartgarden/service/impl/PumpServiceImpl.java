package com.github.fwidder.smartgarden.service.impl;

import com.github.fwidder.smartgarden.config.GPIOPumpOutputPin;
import com.github.fwidder.smartgarden.model.ui.PumpData;
import com.github.fwidder.smartgarden.service.interfaces.GPIOServiceInterface;
import com.github.fwidder.smartgarden.service.interfaces.LEDServiceInterface;
import com.github.fwidder.smartgarden.service.interfaces.PumpServiceInterface;
import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
public class PumpServiceImpl implements PumpServiceInterface {
    @Getter(AccessLevel.NONE)
    private final GPIOServiceInterface gpioService;
    @Getter(AccessLevel.NONE)
    private final LEDServiceInterface ledService;

    public PumpServiceImpl(GPIOServiceInterface gpioService, LEDServiceInterface ledService) throws InterruptedException {
        this.gpioService = gpioService;
        this.ledService = ledService;
        ledService.setYellow(true);
        setAll(false);
        pumpTest();
        ledService.setYellow(false);
    }


    @Override
    public Map<GPIOPumpOutputPin, PumpData> getPumpStatus(){
        Map<GPIOPumpOutputPin, PumpData> pumpDataMap = new HashMap<>();
        Arrays.stream(GPIOPumpOutputPin.values()).forEach(pin -> {
            pumpDataMap.put(pin, PumpData.builder()//
                   .name(pin.getName())//
                   .lastChange(LocalDateTime.now()) // TODO
                   .status(!gpioService.getStatus(pin)) //
                .build());
        });
        return pumpDataMap;
    }

    @Override
    public void setPump(GPIOPumpOutputPin pin, boolean state) {
        if (state)
            gpioService.disable(pin);
        else
            gpioService.enable(pin);
    }

    @Override
    public void setAll(boolean state) {
        Arrays.stream(GPIOPumpOutputPin.values()).forEach(pin -> {
            setPump(pin, state);
        });
    }
}
