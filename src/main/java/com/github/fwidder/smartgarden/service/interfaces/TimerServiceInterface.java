package com.github.fwidder.smartgarden.service.interfaces;

import org.springframework.scheduling.annotation.Scheduled;

public interface TimerServiceInterface {
    @Scheduled(cron = "${watering.cron}")
    void checkWatering() throws InterruptedException;
}
