package com.github.fwidder.smartgarden.service.impl;

import com.github.fwidder.smartgarden.config.ArduinoWaterSensorInputPin;
import com.github.fwidder.smartgarden.config.GPIOPumpOutputPin;
import com.github.fwidder.smartgarden.model.ui.PumpData;
import com.github.fwidder.smartgarden.model.ui.SensorData;
import com.github.fwidder.smartgarden.service.interfaces.ConfigServiceInterface;
import com.github.fwidder.smartgarden.service.interfaces.PumpServiceInterface;
import com.github.fwidder.smartgarden.service.interfaces.SensorServiceInterface;
import com.github.fwidder.smartgarden.service.interfaces.TimerServiceInterface;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UIServiceImpl implements com.github.fwidder.smartgarden.service.interfaces.UIServiceInterface {
    private final PumpServiceInterface pumpService;
    private final SensorServiceInterface sensorService;
    private final TimerServiceInterface timerService;
    private final ConfigServiceInterface configService;

    public UIServiceImpl(PumpServiceInterface pumpService, SensorServiceInterface sensorService, TimerServiceInterface timerService, ConfigServiceInterface configService) {
        this.pumpService = pumpService;
        this.sensorService = sensorService;
        this.timerService = timerService;
        this.configService = configService;
    }

    @Override
    public synchronized void refreshSensor() throws IOException {
        sensorService.refreshSensor();
    }

    @Override
    public synchronized List<PumpData> getPumpData() {
        List<PumpData> pumpData = new ArrayList<>();
        Map<GPIOPumpOutputPin, PumpData> data = new HashMap<>(pumpService.getPumpStatus());
        data.forEach((pin, pump) -> pumpData.add(pump));
        pumpData.sort((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()));
        return pumpData;
    }

    private double calcSensorPercent(long min, long max, long current) {
        return ((double) current - (double) max) / ((double) min - (double) max);
    }

    @Override
    public synchronized List<SensorData> getSensorData() {
        List<SensorData> sensorData = new ArrayList<>();
        Map<ArduinoWaterSensorInputPin, SensorData> data = new HashMap<>(sensorService.getSensorDataMap());
        data.forEach((inputPin, sensor) -> sensorData.add(sensor.toBuilder().currentPercent(calcSensorPercent(sensor.getMin(), sensor.getMax(), sensor.getCurrentAbsolute()))
                .toLow((long) (calcSensorPercent(sensor.getMin(), sensor.getMax(), sensor.getToLow()) * 100L)).build()));
        sensorData.sort((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()));
        return sensorData;
    }

    @Override
    public void startPump(GPIOPumpOutputPin pin) {
        pumpService.setPump(pin, true);
    }

    @Override
    public void stopPump(GPIOPumpOutputPin pin) {
        pumpService.setPump(pin, false);
    }

    @Override
    public void activatePump(GPIOPumpOutputPin pin) {
        configService.setDeactivated(pin, false);
    }

    @Override
    public void deactivatePump(GPIOPumpOutputPin pin) {
        configService.setDeactivated(pin, true);
    }
}
