package pl.markowski.tournament.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@Controller
public class SubmitController {

    @GetMapping("/submit")
    public String submit () {
        return "submit";
    }
}
