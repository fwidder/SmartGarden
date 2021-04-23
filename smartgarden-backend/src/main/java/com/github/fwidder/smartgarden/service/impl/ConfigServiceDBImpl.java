package com.github.fwidder.smartgarden.service.impl;

import com.github.fwidder.smartgarden.config.GPIOPumpOutputPin;
import com.github.fwidder.smartgarden.dao.SettingsRepository;
import com.github.fwidder.smartgarden.service.interfaces.ConfigServiceInterface;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
public class ConfigServiceDBImpl implements ConfigServiceInterface {

    private final SettingsRepository settingsRepository;

    public ConfigServiceDBImpl(SettingsRepository settingsRepository) {
        this.settingsRepository = settingsRepository;
    }

    @Override
    public boolean isDeactivated(GPIOPumpOutputPin pin) {
        return Boolean.parseBoolean(settingsRepository.findByName(pin.getName()).getValue());
    }

    @Override
    public void setDeactivated(GPIOPumpOutputPin pin,@NonNull Boolean state) {
        settingsRepository.save(settingsRepository.findByName(pin.getName()).toBuilder().value(state.toString()).build());
    }
}
