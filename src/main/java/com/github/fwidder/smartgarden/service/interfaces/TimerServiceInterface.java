package com.github.fwidder.smartgarden.service.interfaces;

import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;

public interface TimerServiceInterface {
    @Scheduled(cron = "${watering.cron}")
    void checkWatering() throws InterruptedException, IOException;
}
