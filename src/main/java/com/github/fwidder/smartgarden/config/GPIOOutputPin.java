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
public enum GPIOOutputPin {
    PUMP_1("Pump 1", RaspiPin.GPIO_22, PinState.HIGH),
    PUMP_2("Pump 2", RaspiPin.GPIO_23, PinState.HIGH),
    PUMP_3("Pump 3", RaspiPin.GPIO_24, PinState.HIGH),
    PUMP_4("Pump 4", RaspiPin.GPIO_25, PinState.HIGH),
    LED_RED("Led red", RaspiPin.GPIO_29, PinState.LOW),
    LED_YELLOW("Led yellow", RaspiPin.GPIO_28, PinState.LOW),
    LED_GREEN("Pump Led green", RaspiPin.GPIO_27, PinState.LOW);

    private String name;
    private Pin pin;
    private PinState state;
}
