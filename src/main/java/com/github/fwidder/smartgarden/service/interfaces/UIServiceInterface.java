package com.github.fwidder.smartgarden.service.interfaces;

import com.github.fwidder.smartgarden.model.ui.PumpData;
import com.github.fwidder.smartgarden.model.ui.SensorData;

import java.util.List;

public interface UIServiceInterface {
    void refreshSensor();

    List<PumpData> getPumpData();

    List<SensorData> getSensorData();
}
