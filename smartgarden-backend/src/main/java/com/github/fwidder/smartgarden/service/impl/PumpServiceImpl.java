package com.github.fwidder.smartgarden.service.impl;

import com.github.fwidder.smartgarden.config.GPIOPumpOutputPin;
import com.github.fwidder.smartgarden.model.ui.PumpData;
import com.github.fwidder.smartgarden.service.interfaces.ConfigServiceInterface;
import com.github.fwidder.smartgarden.service.interfaces.GPIOServiceInterface;
import com.github.fwidder.smartgarden.service.interfaces.LEDServiceInterface;
import com.github.fwidder.smartgarden.service.interfaces.PumpServiceInterface;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
@Log4j2
public class PumpServiceImpl implements PumpServiceInterface {
    @Getter(AccessLevel.NONE)
    private final GPIOServiceInterface gpioService;
    @Getter(AccessLevel.NONE)
    private final LEDServiceInterface ledService;
    @Getter(AccessLevel.NONE)
    private final ConfigServiceInterface configService;

    public PumpServiceImpl(GPIOServiceInterface gpioService, LEDServiceInterface ledService, ConfigServiceInterface configService) throws InterruptedException {
        this.gpioService = gpioService;
        this.ledService = ledService;
        this.configService = configService;
        ledService.setYellow(true);
        setAll(false);
        pumpTest();
        ledService.setYellow(false);
    }


    @Override
    public Map<GPIOPumpOutputPin, PumpData> getPumpStatus() {
        Map<GPIOPumpOutputPin, PumpData> pumpDataMap = new HashMap<>();
        Arrays.stream(GPIOPumpOutputPin.values()).forEach(pin -> pumpDataMap.put(pin, PumpData.builder()//
                .name(pin.getName())//
                .lastChange(LocalDateTime.now()) // TODO Remember last actual Change
                .status(!gpioService.getStatus(pin)) //
                .disabled(configService.isDeactivated(pin)) //
                .build()));
        return pumpDataMap;
    }

    @Override
    public void setPump(GPIOPumpOutputPin pin, boolean state) {
        if(configService.isDeactivated(pin)) {
            log.atWarn().log("{} is deactivated. Pump will stay turned off", pin.getName());
            gpioService.enable(pin);
            return;
        }
        log.atDebug().log("Set {} to {}.", pin.getName(), state);
        if (state)
            gpioService.disable(pin);
        else
            gpioService.enable(pin);
    }

    @Override
    public void setAll(boolean state) {
        Arrays.stream(GPIOPumpOutputPin.values()).forEach(pin -> setPump(pin, state));
    }
}
