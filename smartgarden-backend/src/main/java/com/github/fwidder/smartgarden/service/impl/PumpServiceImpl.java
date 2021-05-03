package com.github.fwidder.smartgarden.service.impl;

import com.github.fwidder.smartgarden.config.GPIOPumpOutputPin;
import com.github.fwidder.smartgarden.dao.PumpEventRepository;
import com.github.fwidder.smartgarden.model.db.PumpEvent;
import com.github.fwidder.smartgarden.model.ui.PumpData;
import com.github.fwidder.smartgarden.service.interfaces.ConfigServiceInterface;
import com.github.fwidder.smartgarden.service.interfaces.GPIOServiceInterface;
import com.github.fwidder.smartgarden.service.interfaces.LEDServiceInterface;
import com.github.fwidder.smartgarden.service.interfaces.PumpServiceInterface;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
@Log4j2
public class PumpServiceImpl implements PumpServiceInterface {
    private final GPIOServiceInterface gpioService;
    private final LEDServiceInterface ledService;
    private final ConfigServiceInterface configService;
    private final PumpEventRepository pumpEventRepository;

    public PumpServiceImpl(GPIOServiceInterface gpioService, LEDServiceInterface ledService, ConfigServiceInterface configService, PumpEventRepository pumpEventRepository) throws InterruptedException {
        this.gpioService = gpioService;
        this.ledService = ledService;
        this.configService = configService;
        this.pumpEventRepository = pumpEventRepository;
        ledService.setYellow(true);
        setAll(false);
        pumpTest();
        ledService.setYellow(false);
    }


    @Override
    public Map<GPIOPumpOutputPin, PumpData> getPumpStatus() {
        Map<GPIOPumpOutputPin, PumpData> pumpDataMap = new HashMap<>();
        Arrays.stream(GPIOPumpOutputPin.values()).forEach(pin ->
                {
                    PumpEvent event = pumpEventRepository.findTop1PumpEventByPumpEqualsOrderByTimeDesc(pin);
                    pumpDataMap.put(pin, PumpData.builder() //
                            .name(pin.getName()) //
                            .lastChange(event != null ? event.getTime() : LocalDateTime.now()) //
                            .status(!gpioService.getStatus(pin)) //
                            .disabled(configService.isDeactivated(pin)) //
                            .build());
                }
        );
        return pumpDataMap;
    }

    @Override
    public void setPump(GPIOPumpOutputPin pin, boolean state) {
        if (configService.isDeactivated(pin)) {
            log.atWarn().log("{} is deactivated. Pump will stay turned off", pin.getName());
            gpioService.enable(pin);
            return;
        }
        log.atDebug().log("Set {} to {}.", pin.getName(), state);
        if (state)
            gpioService.disable(pin);
        else
            gpioService.enable(pin);
        pumpEventRepository.save(PumpEvent.builder() //
                .pump(pin) //
                .state(state) //
                .time(LocalDateTime.now()) //
                .build());
    }

    @Override
    public void setAll(boolean state) {
        Arrays.stream(GPIOPumpOutputPin.values()).forEach(pin -> setPump(pin, state));
    }
}
