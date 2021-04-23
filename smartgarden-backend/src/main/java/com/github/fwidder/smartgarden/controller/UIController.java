package com.github.fwidder.smartgarden.controller;

import com.github.fwidder.smartgarden.service.interfaces.UIServiceInterface;
import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.InputStream;

@Controller
@RequestMapping("/")
public class UIController {
    private final UIServiceInterface uiService;

    public UIController(UIServiceInterface uiService) {
        this.uiService = uiService;
    }

    @GetMapping(value = "th-ui")
    public ModelAndView thUi() {
        ModelAndView mav = new ModelAndView("th-ui");
        mav.addObject("sensorData", uiService.getSensorData());
        mav.addObject("pumpData", uiService.getPumpData());
        return mav;
    }

    @GetMapping(
            value = "react-ui",
            produces = MediaType.TEXT_HTML_VALUE
    )
    public @ResponseBody
    byte[] getFile() throws IOException {
        InputStream in = getClass()
                .getResourceAsStream("/static/index.html");
        return IOUtils.toByteArray(in);
    }
}
