package pl.markowski.tournament.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.markowski.tournament.model.Info;
import pl.markowski.tournament.repo.InfoRepo;
import pl.markowski.tournament.service.InfoService;

import javax.validation.Valid;
import java.util.List;

@Controller
public class InfoController {

    private InfoRepo infoRepo;
    private InfoService infoService;

    @Autowired
    public InfoController(InfoRepo infoRepo, InfoService infoService) {
        this.infoRepo = infoRepo;
        this.infoService = infoService;
    }

    @GetMapping("/info/list")
    public String showInfo (Model model) {
        model.addAttribute("infos", infoRepo.findAllByOrderByIdDesc());
        return findPaginated(1, model);
    }

    @GetMapping("info/add")
    public String showInfoForm (Model model) {
        Info info = new Info();
        model.addAttribute("info", info);
        return "info_form";
    }

    @PostMapping("info/add")
    public String infoForm (@Valid @ModelAttribute("info") Info info, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "info_form";
        } else {
            infoRepo.save(info);
            return "redirect:/info/list";
        }
    }

    @GetMapping("info/list/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model) {

        int pageSize = 10;
        Page<Info> page = infoService.findPaginated(pageNo, pageSize);
        List<Info> infos = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalElements", page.getTotalElements());

        model.addAttribute("infos", infos);
        return "info";
    }

    @GetMapping("info/delete/{id}")
    public String deleteInfoSubmit(@PathVariable ("id") long id, Model model) {

        this.infoService.deleteInfoById(id);
        model.addAttribute("infos", infoRepo.findAllByOrderByIdDesc());
        return "info";
    }

    @GetMapping("info/deleteAll")
    public String deleteInfoAll(Model model) {

        this.infoService.deleteInfoAll();
        model.addAttribute("infos", infoRepo.findAllByOrderByIdDesc());
        return "redirect:/info/list";
    }

    @GetMapping("info/edit/{id}")
    public String showUpdateInfoForm(@PathVariable ("id") long id, Model model) {
        Info info = infoRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid ID: " + id));
        model.addAttribute("info", info);
        return "info_update";
    }

    @PostMapping("info/update/{id}")
    @Transactional
    public String updateInfoSubmit(@PathVariable ("id") long id, @Valid Info info, BindingResult result, Model model) {

        if (result.hasErrors()) {
            info.setId(id);
            return "info_update";
        }

        infoRepo.save(info);
        model.addAttribute("info", infoRepo.findAllByOrderByIdDesc());
        return "redirect:/info/list";
    }
}