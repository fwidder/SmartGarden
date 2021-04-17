package com.github.fwidder.smartgarden.service.impl;

import com.github.fwidder.smartgarden.model.ui.PumpData;
import com.github.fwidder.smartgarden.model.ui.SensorData;
import com.github.fwidder.smartgarden.service.impl.TimerServiceImpl;
import com.github.fwidder.smartgarden.service.interfaces.PumpServiceInterface;
import com.github.fwidder.smartgarden.service.interfaces.SensorServiceInterface;
import com.github.fwidder.smartgarden.service.interfaces.TimerServiceInterface;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UIServiceImpl implements com.github.fwidder.smartgarden.service.interfaces.UIServiceInterface {
    private final PumpServiceInterface pumpService;
    private final SensorServiceInterface sensorService;
    private final TimerServiceInterface timerService;

    public UIServiceImpl(PumpServiceInterface pumpService, SensorServiceInterface sensorService, TimerServiceInterface timerService) {
        this.pumpService = pumpService;
        this.sensorService = sensorService;
        this.timerService = timerService;
    }

    @Override
    public void refreshSensor() throws IOException {
        sensorService.refreshSensor();
    }

    @Override
    public List<PumpData> getPumpData() {
        List<PumpData> pumpData = new ArrayList<>();
        pumpService.getPumpStatus().forEach((pin, pump) -> {
            pumpData.add(pump);
        });
        pumpData.sort((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()));
        return pumpData;
    }

    private double calcSensorPercent(long min, long max, long current){
        return ((double) current - (double) max) / ((double) min - (double) max);
    }

    @Override
    public List<SensorData> getSensorData() {
        List<SensorData> sensorData = new ArrayList<>();
        sensorService.getSensorDataMap().forEach((inputPin, sensor) -> {
            sensorData.add(sensor.toBuilder().currentPercent(calcSensorPercent(sensor.getMin(), sensor.getMax(), sensor.getCurrentAbsolute()))
                    .toLow((long) (calcSensorPercent(sensor.getMin(), sensor.getMax(), sensor.getToLow()) * 100L)).build() );
        });
        sensorData.sort((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()));
        return sensorData;
    }
}
