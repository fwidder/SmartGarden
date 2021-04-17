package com.github.fwidder.smartgarden.service.impl;

import com.github.fwidder.smartgarden.model.ui.PumpData;
import com.github.fwidder.smartgarden.model.ui.SensorData;
import com.github.fwidder.smartgarden.service.impl.TimerServiceImpl;
import com.github.fwidder.smartgarden.service.interfaces.PumpServiceInterface;
import com.github.fwidder.smartgarden.service.interfaces.SensorServiceInterface;
import com.github.fwidder.smartgarden.service.interfaces.TimerServiceInterface;
import org.springframework.stereotype.Service;

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
    public void refreshSensor(){
        sensorService.checkSensor1();
        sensorService.checkSensor2();
        sensorService.checkSensor3();
        sensorService.checkSensor4();
    }

    @Override
    public List<PumpData> getPumpData() {
        List<PumpData> pumpData = new ArrayList<>();
        pumpData.add(
                PumpData.builder() //
                        .name("Pumpe #1")
                        .status(pumpService.getPump1Status())
                        .lastChange(pumpService.getPump1LastChange())
                        .build() //
        );
        pumpData.add(
                PumpData.builder() //
                        .name("Pumpe #2")
                        .status(pumpService.getPump2Status())
                        .lastChange(pumpService.getPump2LastChange())
                        .build() //
        );
        pumpData.add(
                PumpData.builder() //
                        .name("Pumpe #3")
                        .status(pumpService.getPump3Status())
                        .lastChange(pumpService.getPump3LastChange())
                        .build() //
        );
        pumpData.add(
                PumpData.builder() //
                        .name("Pumpe #4")
                        .status(pumpService.getPump4Status())
                        .lastChange(pumpService.getPump4LastChange())
                        .build() //
        );
        return pumpData;
    }

    private double calcSensorPercent(long min, long max, long current){
        return ((double) current - (double) max) / ((double) min - (double) max);
    }

    @Override
    public List<SensorData> getSensorData() {
        List<SensorData> sensorData = new ArrayList<>();
        sensorData.add( //
                SensorData.builder() //
                        .name("Sensor #1") //
                        .currentAbsolute(sensorService.getSensor1Last()) //
                        .currentPercent(calcSensorPercent(sensorService.getSensor1Min(), sensorService.getSensor1Max() ,sensorService.getSensor1Last())) //
                        .lastMeasurement(sensorService.getSensor1LastMeasurement()) //
                        .max(sensorService.getSensor1Max()) //
                        .min(sensorService.getSensor1Min()) //
                        .build() //
        );
        sensorData.add( //
                SensorData.builder() //
                        .name("Sensor #2")
                        .currentAbsolute(sensorService.getSensor2Last()) //
                        .currentPercent(calcSensorPercent(sensorService.getSensor2Min(), sensorService.getSensor2Max() ,sensorService.getSensor2Last())) //
                        .lastMeasurement(sensorService.getSensor2LastMeasurement()) //
                        .max(sensorService.getSensor2Max()) //
                        .min(sensorService.getSensor2Min()) //
                        .build() //
        );
        sensorData.add( //
                SensorData.builder() //
                        .name("Sensor #3") //
                        .currentAbsolute(sensorService.getSensor3Last()) //
                        .currentPercent(calcSensorPercent(sensorService.getSensor3Min(), sensorService.getSensor3Max() ,sensorService.getSensor3Last())) //
                        .lastMeasurement(sensorService.getSensor3LastMeasurement()) //
                        .max(sensorService.getSensor3Max()) //
                        .min(sensorService.getSensor3Min()) //
                        .build() //
        );
        sensorData.add( //
                SensorData.builder() //
                        .name("Sensor #4") //
                        .currentAbsolute(sensorService.getSensor4Last()) //
                        .currentPercent(calcSensorPercent(sensorService.getSensor4Min(), sensorService.getSensor4Max() ,sensorService.getSensor4Last())) //
                        .lastMeasurement(sensorService.getSensor4LastMeasurement()) //
                        .max(sensorService.getSensor4Max()) //
                        .min(sensorService.getSensor4Min()) //
                        .build() //
        );
        return sensorData;
    }
}
