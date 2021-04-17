package com.github.fwidder.smartgarden.model.ui;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
public class SensorData {
    private final String name;
    private final LocalDateTime lastMeasurement;
    private final double currentPercent;
    private final long currentAbsolute;
    private final long min;
    private final long max;
    private final long toLow;
}
