package com.github.fwidder.smartgarden.controller;

import com.github.fwidder.smartgarden.config.GPIOPumpOutputPin;
import com.github.fwidder.smartgarden.model.ui.PumpData;
import com.github.fwidder.smartgarden.model.ui.SensorData;
import com.github.fwidder.smartgarden.service.interfaces.UIServiceInterface;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/control/")
public class ApiController {
    private final UIServiceInterface uiService;

    public ApiController(UIServiceInterface uiService) {
        this.uiService = uiService;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(value = "refresh")
    public void controlRefresh() throws IOException {
        uiService.refreshSensor();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(value = "start/{pump}")
    public void controlStart(@PathVariable String pump) {
        uiService.startPump(GPIOPumpOutputPin.valueOf(pump));
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(value = "stop/{pump}")
    public void controlStop(@PathVariable String pump) {
        uiService.stopPump(GPIOPumpOutputPin.valueOf(pump));
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(value = "activate/{pump}")
    public void controlActivate(@PathVariable String pump) {
        uiService.activatePump(GPIOPumpOutputPin.valueOf(pump));
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(value = "deactivate/{pump}")
    public void controlDeactivate(@PathVariable String pump) {
        uiService.deactivatePump(GPIOPumpOutputPin.valueOf(pump));
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(value = "sensordata")
    public List<SensorData> controlSensordata(){
        return uiService.getSensorData();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(value = "pumpdata")
    public List<PumpData> controlPumpdata(){
        return uiService.getPumpData();
    }
}
