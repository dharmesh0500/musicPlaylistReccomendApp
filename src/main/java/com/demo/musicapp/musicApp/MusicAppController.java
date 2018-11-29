package com.demo.musicapp.musicApp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MusicAppController {

    @RequestMapping("/")
    public String home()
    {
        return "home.html";
    }
}
