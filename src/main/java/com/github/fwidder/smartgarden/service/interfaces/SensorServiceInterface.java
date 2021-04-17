package com.github.fwidder.smartgarden.service.interfaces;

public interface SensorServiceInterface {
    default void testSensor() {
        checkSensor1();
        checkSensor2();
        checkSensor3();
        checkSensor4();
    }

    boolean checkSensor1();

    boolean checkSensor2();

    boolean checkSensor3();

    boolean checkSensor4();

    long getSensor1Max();

    long getSensor1Min();

    long getSensor2Max();

    long getSensor2Min();

    long getSensor3Max();

    long getSensor3Min();

    long getSensor4Max();

    long getSensor4Min();

    long getSensor1Last();

    long getSensor2Last();

    long getSensor3Last();

    long getSensor4Last();

    java.time.LocalDateTime getSensor1LastMeasurement();

    java.time.LocalDateTime getSensor2LastMeasurement();

    java.time.LocalDateTime getSensor3LastMeasurement();

    java.time.LocalDateTime getSensor4LastMeasurement();
}
