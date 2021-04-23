package pl.markowski.tournament.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.markowski.tournament.model.Submit;
import pl.markowski.tournament.repo.SubmitRepo;

import java.util.Arrays;
import java.util.List;


@Controller
public class SubmitController {

    private SubmitRepo submitRepo;

    @Autowired
    public SubmitController(SubmitRepo submitRepo) {
        this.submitRepo = submitRepo;
    }

    @GetMapping("/submit")
    public String showForm (Model model) {
        Submit submit = new Submit();
        model.addAttribute("submit", submit);
        List<String> rank = Arrays.asList("I", "II", "III", "IV", "V", "VI", "X" );
        model.addAttribute("rank", rank);
        return "submit_form";
    }

    @PostMapping("/submit")
    public String submitForm (@ModelAttribute("submit") Submit submit) {
        submitRepo.save(submit);
        return "submit_ok";
    }
}
