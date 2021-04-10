package com.github.fwidder.smartgarden.config;

import com.pi4j.io.gpio.Pin;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum GPIOInputPin {
    ;

    private final String name;
    private final Pin pin;
}
