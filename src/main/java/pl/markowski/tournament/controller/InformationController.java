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
import pl.markowski.tournament.model.Information;
import pl.markowski.tournament.service.InformationService;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
class InformationController {

    static final class Routes {
        static final String ROOT = "/information";
        static final String LIST = ROOT + "/list";
        static final String ADD = ROOT + "/add";
        static final String FIND_PAGINATED = LIST + "/page/{pageNo}";
        static final String DELETE = ROOT + "/delete/{id}";
        static final String DELETE_ALL = ROOT + "/deleteAll";
        static final String EDIT = ROOT + "/edit/{id}";
        static final String UPDATE = ROOT + "/update/{id}}";
    }

    private final InformationService informationService;

    @GetMapping(Routes.LIST)
    public String getAllInformation(final Model model) {
        return informationService.getAllInformation(model);
    }

    @GetMapping(Routes.ADD)
    public String showInformationForm(final Model model) {
        return informationService.showInformationForm(model);
    }

    @PostMapping(Routes.ADD)
    public String addInformation(@Valid @ModelAttribute("info") final Information information,
                                 final BindingResult bindingResult, final Model model) {
        return informationService.addInformation(information, bindingResult, model);
    }

    @GetMapping(Routes.FIND_PAGINATED)
    public String findPaginated(@PathVariable(value = "pageNo") final int pageNo, final Model model) {
        return informationService.findPaginated(pageNo, model);
    }

    @GetMapping(Routes.DELETE)
    public String deleteInformationSubmit(@PathVariable("id") final long id, final Model model) {
        return informationService.deleteInformationSubmit(id, model);
    }

    @GetMapping(Routes.DELETE_ALL)
    public String deleteAllInformation(final Model model) {
        return informationService.deleteAllInformation(model);
    }

    @GetMapping(Routes.EDIT)
    public String showUpdateInformationForm(@PathVariable("id") final long id, final Model model) {
        return informationService.showUpdateInformationForm(id, model);
    }

    @PostMapping(Routes.UPDATE)
    @Transactional
    public String updateInformationSubmit(@PathVariable("id") final long id, @Valid final Information information,
                                          final BindingResult result, final Model model) {
        return informationService.updateInformationSubmit(id, information, result, model);
    }
}