package com.github.fwidder.smartgarden.service.impl;

import com.github.fwidder.smartgarden.service.interfaces.LEDServiceInterface;
import com.github.fwidder.smartgarden.service.interfaces.PumpServiceInterface;
import com.github.fwidder.smartgarden.service.interfaces.SensorServiceInterface;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

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
    public void checkWatering() throws InterruptedException {
        log.atInfo().log("Check Watering for all Plants.");
        ledService.setYellow(true);
        if (sensorService.checkSensor1()) {
            log.atInfo().log("Pump 1 will be started.");
            pumpService.setPump1(true);
        }
        if (sensorService.checkSensor2()) {
            log.atInfo().log("Pump 2 will be started.");
            pumpService.setPump2(true);
        }
        if (sensorService.checkSensor3()) {
            log.atInfo().log("Pump 3 will be started.");
            pumpService.setPump3(true);
        }
        if (sensorService.checkSensor4()) {
            log.atInfo().log("Pump 4 will be started.");
            pumpService.setPump4(true);
        }
        log.atInfo().log("Watering Plants for {}ms.", wateringTime);
        Thread.sleep(wateringTime);
        pumpService.setAll(false);
        ledService.setAll(false);
        log.atInfo().log("Finished Watering.");
    }
}
