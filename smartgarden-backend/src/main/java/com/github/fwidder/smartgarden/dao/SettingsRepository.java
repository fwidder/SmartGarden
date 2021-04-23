package com.github.fwidder.smartgarden.dao;

import com.github.fwidder.smartgarden.model.db.Setting;
import org.springframework.data.repository.CrudRepository;

public interface  SettingsRepository extends CrudRepository<Setting, Long> {
    Setting findByName(String name);
}
