package com.github.fwidder.smartgarden.service.interfaces;

public interface LEDServiceInterface {
    default void lightTest() throws InterruptedException {
        setRed(true);
        Thread.sleep(1000);
        setYellow(true);
        Thread.sleep(1000);
        setGreen(true);
        Thread.sleep(1000);
        setAll(false);
    }

    void setAll(boolean state);

    void setGreen(boolean state);

    void setYellow(boolean state);

    void setRed(boolean state);
}
