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
public enum GPIOPumpOutputPin {
    PUMP_1("Pump 1", RaspiPin.GPIO_22, PinState.HIGH),
    PUMP_2("Pump 2", RaspiPin.GPIO_23, PinState.HIGH),
    PUMP_3("Pump 3", RaspiPin.GPIO_24, PinState.HIGH);

    private final String name;
    private final Pin pin;
    private final PinState state;
}
