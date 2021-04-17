package com.github.fwidder.smartgarden.controller;

import com.github.fwidder.smartgarden.service.interfaces.UIServiceInterface;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/")
public class UIController {
    private final UIServiceInterface uiService;

    public UIController(UIServiceInterface uiService) {
        this.uiService = uiService;
    }

    @RequestMapping(value = "/control")
    public ModelAndView control() {
        ModelAndView mav = new ModelAndView("control");
        mav.addObject("sensorData", uiService.getSensorData());
        mav.addObject("pumpData", uiService.getPumpData());
        return mav;
    }

    @RequestMapping(value = "/control/refresh")
    public RedirectView refreshControl(){
        uiService.refreshSensor();
        return new RedirectView("/control");
    }
}
