package com.github.fwidder.smartgarden.dao;

import com.github.fwidder.smartgarden.config.GPIOPumpOutputPin;
import com.github.fwidder.smartgarden.model.db.PumpEvent;
import org.springframework.data.repository.CrudRepository;

public interface PumpEventRepository extends CrudRepository<PumpEvent, Long> {
    PumpEvent findTop1PumpEventByPumpEqualsOrderByTimeDesc(GPIOPumpOutputPin pin);
}
