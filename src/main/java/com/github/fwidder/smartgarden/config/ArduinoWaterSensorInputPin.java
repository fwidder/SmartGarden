package com.github.fwidder.smartgarden.config;

import com.pi4j.io.gpio.Pin;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum ArduinoWaterSensorInputPin {
    SENSOR_1("Sensor 1", 14, 300, 650, 550),
    SENSOR_2("Sensor 2", 15, 300, 650, 550),
    SENSOR_3("Sensor 3", 16, 300, 650, 550),
    SENSOR_4("Sensor 4", 17, 300, 650, 550);

    private final String name;
    private final int port;
    private final int min;
    private final int max;
    private final int toLow;
}
