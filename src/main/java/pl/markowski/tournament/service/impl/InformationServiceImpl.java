package pl.markowski.tournament.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import pl.markowski.tournament.model.Information;
import pl.markowski.tournament.repository.InformationRepository;
import pl.markowski.tournament.service.InformationService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class InformationServiceImpl implements InformationService {

    private final InformationRepository informationRepository;

    @Override
    public String getAllInformation(final Model model) {
        final List<Information> allByOrderByIdDesc = informationRepository.findAllByOrderByIdDesc();
        model.addAttribute("infos", allByOrderByIdDesc);
        return findPaginated(1, model);
    }

    @Override
    public String showInformationForm(final Model model) {
        final Information information = new Information();
        model.addAttribute("info", information);
        return "info_form";
    }

    @Override
    public String addInformation(final Information information, final BindingResult bindingResult, final Model model) {
        if (bindingResult.hasErrors()) {
            return "info_form";
        } else {
            informationRepository.save(information);
            log.info("Successfully added new information with ID " + information.getId());
            return "redirect:/info/list";
        }
    }

    @Override
    public String findPaginated(final int pageNo, final Model model) {
        final Page<Information> page = findPaginatedSorted(pageNo);
        final List<Information> information = page.getContent();
        final int totalPages = page.getTotalPages();
        final long totalElements = page.getTotalElements();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalElements", totalElements);
        model.addAttribute("infos", information);
        return "info";
    }

    @Override
    public String deleteInformationSubmit(final long id, final Model model) {
        final List<Information> allByOrderByIdDesc = informationRepository.findAllByOrderByIdDesc();
        final Information information = informationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid ID : " + id));
        informationRepository.delete(information);
        model.addAttribute("infos", allByOrderByIdDesc);
        log.info("Successfully deleted information with ID " + id);
        return "info";
    }

    @Override
    public String deleteAllInformation(final Model model) {
        final List<Information> allByOrderByIdDesc = informationRepository.findAllByOrderByIdDesc();
        informationRepository.deleteAll();
        model.addAttribute("infos", allByOrderByIdDesc);
        log.info("Successfully deleted all information");
        return "redirect:/info/list";
    }

    @Override
    public String showUpdateInformationForm(final long id, final Model model) {
        final Information information = informationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid ID: " + id));
        model.addAttribute("info", information);
        return "info_update";
    }

    @Override
    public String updateInformationSubmit(final long id, final Information information, final BindingResult result, final Model model) {
        if (result.hasErrors()) {
            information.setId(id);
            return "info_update";
        }
        informationRepository.save(information);
        final List<Information> allByOrderByIdDesc = informationRepository.findAllByOrderByIdDesc();
        model.addAttribute("info", allByOrderByIdDesc);
        log.info("Successfully updated information with ID " + information.getId());
        return "redirect:/info/list";
    }

    private Page<Information> findPaginatedSorted(final int pageNo) {
        final Pageable pageable = PageRequest.of(pageNo - 1, 10, Sort.Direction.DESC, "id");
        return informationRepository.findAll(pageable);
    }
}