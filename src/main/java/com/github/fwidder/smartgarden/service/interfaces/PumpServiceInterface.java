package com.github.fwidder.smartgarden.service.interfaces;

public interface PumpServiceInterface {
    default void pumpTest() throws InterruptedException {
        setPump1(true);
        Thread.sleep(500);
        setPump1(false);
        setPump2(true);
        Thread.sleep(500);
        setPump2(false);
        setPump3(true);
        Thread.sleep(500);
        setPump3(false);
        setPump4(true);
        Thread.sleep(500);
        setPump4(false);

        setAll(false);
    }

    boolean getPump1Status();

    boolean getPump2Status();

    boolean getPump3Status();

    boolean getPump4Status();

    void setPump1(boolean state);

    void setPump2(boolean state);

    void setPump3(boolean state);

    void setPump4(boolean state);

    void setAll(boolean state);

    java.time.LocalDateTime getPump1LastChange();

    java.time.LocalDateTime getPump2LastChange();

    java.time.LocalDateTime getPump3LastChange();

    java.time.LocalDateTime getPump4LastChange();
}
