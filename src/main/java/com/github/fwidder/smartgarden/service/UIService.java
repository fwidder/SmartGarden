package com.github.fwidder.smartgarden.service;

import com.github.fwidder.smartgarden.model.ui.PumpData;
import com.github.fwidder.smartgarden.model.ui.SensorData;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UIService {
    private final PumpService pumpService;
    private final SensorService sensorService;
    private final TimerService timerService;

    public UIService(PumpService pumpService, SensorService sensorService, TimerService timerService) {
        this.pumpService = pumpService;
        this.sensorService = sensorService;
        this.timerService = timerService;
    }

    public void refreshSensor(){
        sensorService.checkSensor1();
        sensorService.checkSensor2();
        sensorService.checkSensor3();
        sensorService.checkSensor4();
    }

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

    public List<SensorData> getSensorData() {
        List<SensorData> sensorData = new ArrayList<>();
        sensorData.add( //
                SensorData.builder() //
                        .name("Sensor #1") //
                        .current(sensorService.getSensor1Last()) //
                        .lastMeasurement(sensorService.getSensor1LastMeasurement()) //
                        .max(sensorService.getSensor1Max()) //
                        .min(sensorService.getSensor1Min()) //
                        .build() //
        );
        sensorData.add( //
                SensorData.builder() //
                        .name("Sensor #2")
                        .current(sensorService.getSensor2Last()) //
                        .lastMeasurement(sensorService.getSensor2LastMeasurement()) //
                        .max(sensorService.getSensor2Max()) //
                        .min(sensorService.getSensor2Min()) //
                        .build() //
        );
        sensorData.add( //
                SensorData.builder() //
                        .name("Sensor #3") //
                        .current(sensorService.getSensor3Last()) //
                        .lastMeasurement(sensorService.getSensor3LastMeasurement()) //
                        .max(sensorService.getSensor3Max()) //
                        .min(sensorService.getSensor3Min()) //
                        .build() //
        );
        sensorData.add( //
                SensorData.builder() //
                        .name("Sensor #4") //
                        .current(sensorService.getSensor4Last()) //
                        .lastMeasurement(sensorService.getSensor4LastMeasurement()) //
                        .max(sensorService.getSensor4Max()) //
                        .min(sensorService.getSensor4Min()) //
                        .build() //
        );
        return sensorData;
    }
}
