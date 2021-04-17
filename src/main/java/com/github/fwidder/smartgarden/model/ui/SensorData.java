package com.github.fwidder.smartgarden.model.ui;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class SensorData {
    private final String name;
    private final LocalDateTime lastMeasurement;
    private final double currentPercent;
    private final double currentAbsolute;
    private final long min;
    private final long max;
}
