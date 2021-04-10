package com.github.fwidder.smartgarden.model.ui;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class PumpData {
    private final String name;
    private final LocalDateTime lastChange;
    private final boolean status;
}
