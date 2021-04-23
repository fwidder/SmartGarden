package com.github.fwidder.smartgarden.service.impl;

import com.github.fwidder.smartgarden.config.SensorPumpMapping;
import com.github.fwidder.smartgarden.service.interfaces.LEDServiceInterface;
import com.github.fwidder.smartgarden.service.interfaces.PumpServiceInterface;
import com.github.fwidder.smartgarden.service.interfaces.SensorServiceInterface;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Log4j2
public class TimerServiceImpl implements com.github.fwidder.smartgarden.service.interfaces.TimerServiceInterface {
    private final LEDServiceInterface ledService;
    private final SensorServiceInterface sensorService;
    private final PumpServiceInterface pumpService;
    private final Long wateringTime;

    public TimerServiceImpl(LEDServiceInterface ledService, SensorServiceInterface sensorService, PumpServiceInterface pumpService, @Value("${watering.period}") String wateringTime) throws NumberFormatException {
        this.ledService = ledService;
        this.sensorService = sensorService;
        this.pumpService = pumpService;
        this.wateringTime = Long.parseLong(wateringTime) * 1000L;
    }

    @Override
    @Scheduled(cron = "${watering.cron}")
    public void checkWatering() throws InterruptedException, IOException {
        log.atInfo().log("Check Watering for all Plants.");
        log.atInfo().log("Watering Plants for {}ms.", wateringTime);
        ledService.setYellow(true);
        sensorService.refreshSensor();
        sensorService.getSensorDataMap().forEach((inputPin, sensorData) -> {
            if (sensorData.getCurrentAbsolute() > sensorData.getToLow()) {
                log.atInfo().log("Pump {} will be started.", SensorPumpMapping.map.get(inputPin).getName());
                pumpService.setPump(SensorPumpMapping.map.get(inputPin), true);
                try {
                    Thread.sleep(wateringTime);
                } catch (InterruptedException e) {
                    log.atError().log(e);
                }finally {
                    pumpService.setPump(SensorPumpMapping.map.get(inputPin), false);
                }
            }
        });
        pumpService.setAll(false);
        ledService.setAll(false);
        log.atInfo().log("Finished Watering.");
    }
}
