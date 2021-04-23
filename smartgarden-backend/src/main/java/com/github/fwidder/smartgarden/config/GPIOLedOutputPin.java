package com.github.fwidder.smartgarden.config;

import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum GPIOLedOutputPin {
    LED_RED("LED_RED", RaspiPin.GPIO_29, PinState.LOW),
    LED_YELLOW("LED_YELLOW", RaspiPin.GPIO_28, PinState.LOW),
    LED_GREEN("LED_GREEN", RaspiPin.GPIO_27, PinState.LOW);

    private final String name;
    private final Pin pin;
    private final PinState state;
}
