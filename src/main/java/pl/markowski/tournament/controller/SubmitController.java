package pl.markowski.tournament.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.markowski.tournament.Service.SubmitService;
import pl.markowski.tournament.model.Submit;
import pl.markowski.tournament.repo.SubmitRepo;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;


@Controller
public class SubmitController {

    int counting = 0;

    private SubmitRepo submitRepo;
    private SubmitService submitService;

    @Autowired
    public SubmitController(SubmitRepo submitRepo, SubmitService submitService) {
        this.submitRepo = submitRepo;
        this.submitService = submitService;
    }

    @GetMapping("/list")
    public String showSubmit (Model model) {
        return findPaginated(1, "id", "asc", model);
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
    public String submitForm (@Valid @ModelAttribute("submit") Submit submit, BindingResult bindingResult, Model model) {

        if (counting>=4) {
            return "submit_max";
        }

        else if (bindingResult.hasErrors()) {
            List<String> rank = Arrays.asList("I", "II", "III", "IV", "V", "VI", "X" );
            model.addAttribute("rank", rank);
            return "submit_form";
        } else {
            submitRepo.save(submit);
            counting++;
            return "submit_ok";
        }
    }

    @GetMapping("/list/page/{pageNo}")
    public String findPaginated(@PathVariable (value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {

        int pageSize = 10;
        Page<Submit> page = submitService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Submit> submits = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalElements", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("submits", submits);
        return "submit_list";
    }

    @GetMapping("delete/{id}")
    public String deleteSubmit(@PathVariable ("id") long id, Model model) {

        this.submitService.deleteSubmitById(id);
        counting--;
        model.addAttribute("submits", submitRepo.findAll());
        return "submit_list";
    }

    @GetMapping("deleteAll")
    public String deleteAll(Model model) {

        this.submitService.deleteSubmitAll();
        counting = 0;
        model.addAttribute("submits", submitRepo.findAll());
        return "redirect:/list";
    }
}
