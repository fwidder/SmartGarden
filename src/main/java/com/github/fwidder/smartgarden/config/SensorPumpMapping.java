package com.github.fwidder.smartgarden.config;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class SensorPumpMapping {
    public static final Map<ArduinoWaterSensorInputPin, GPIOPumpOutputPin> map;

    static {
        map = new HashMap<>();
        map.put(ArduinoWaterSensorInputPin.SENSOR_1,GPIOPumpOutputPin.PUMP_1);
        map.put(ArduinoWaterSensorInputPin.SENSOR_2,GPIOPumpOutputPin.PUMP_2);
        map.put(ArduinoWaterSensorInputPin.SENSOR_3,GPIOPumpOutputPin.PUMP_3);
        map.put(ArduinoWaterSensorInputPin.SENSOR_4,GPIOPumpOutputPin.PUMP_4);
    }
}
