package com.github.fwidder.smartgarden.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum ArduinoWaterSensorInputPin {
    SENSOR_1("SENSOR_1", 14, 300, 650, 550),
    SENSOR_2("SENSOR_2", 15, 300, 650, 550),
    SENSOR_3("SENSOR_3", 16, 300, 650, 550);

    private final String name;
    private final int port;
    private final int min;
    private final int max;
    private final int toLow;
}
