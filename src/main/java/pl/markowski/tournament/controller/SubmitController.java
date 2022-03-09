package pl.markowski.tournament.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.markowski.tournament.model.Submit;
import pl.markowski.tournament.service.SubmitService;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
class SubmitController {

    static final class Routes {
        static final String LIST = "/list";
        static final String SUBMIT = "/submit";
        static final String FIND_PAGINATED = LIST + "/page/{pageNo}";
        static final String DELETE = "/delete/{id}";
        static final String DELETE_ALL = "/deleteAll";
        static final String EDIT = "/edit/{id}";
        static final String UPDATE = "/update/{id}";
    }

    private final SubmitService submitService;

    @GetMapping(Routes.LIST)
    public String getAllSubmits(final Model model) {
        return submitService.getAllSubmits(model);
    }

    @GetMapping(Routes.SUBMIT)
    public String showSubmitForm(final Model model) {
        return submitService.showSubmitForm(model);
    }

    @PostMapping(Routes.SUBMIT)
    public String addSubmit(@Valid @ModelAttribute("submit") final Submit submit, final BindingResult bindingResult, final Model model) {
        return submitService.addSubmit(submit, bindingResult, model);
    }

    @GetMapping(Routes.FIND_PAGINATED)
    public String findPaginated(@PathVariable(value = "pageNo") final int pageNo,
                                @RequestParam("sortField") final String sortField,
                                @RequestParam("sortDir") final String sortDir, final Model model) {
        return submitService.findPaginated(pageNo, sortField, sortDir, model);
    }

    @GetMapping(Routes.DELETE)
    public String deleteSubmit(@PathVariable("id") final long id, final Model model, final Submit submit) {
        return submitService.deleteSubmit(id, model, submit);
    }

    @GetMapping(Routes.DELETE_ALL)
    public String deleteAll(final Model model, final Submit submit) {
        return submitService.deleteAll(model, submit);
    }

    @GetMapping(Routes.EDIT)
    public String showUpdateForm(@PathVariable("id") final long id, final Model model) {
        return submitService.showUpdateForm(id, model);
    }

    @PostMapping(Routes.UPDATE)
    @Transactional
    public String updateSubmit(@PathVariable("id") final long id, @Valid final Submit submit,
                               final BindingResult result, final Model model) {
        return submitService.updateSubmit(id, submit, result, model);
    }
}