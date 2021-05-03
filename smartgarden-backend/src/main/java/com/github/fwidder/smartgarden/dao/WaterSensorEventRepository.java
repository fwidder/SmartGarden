package com.github.fwidder.smartgarden.dao;

import com.github.fwidder.smartgarden.model.db.WaterSensorEvent;
import org.springframework.data.repository.CrudRepository;

public interface WaterSensorEventRepository extends CrudRepository<WaterSensorEvent, Long> {
}
