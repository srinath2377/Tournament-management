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
import pl.markowski.tournament.model.Submit;
import pl.markowski.tournament.repository.SubmitRepository;
import pl.markowski.tournament.service.SubmitService;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SubmitServiceImpl implements SubmitService {

    private final SubmitRepository submitRepository;

    @Override
    public String getAllSubmits(final Model model) {
        return findPaginated(1, "id", "asc", model);
    }

    @Override
    public String showSubmitForm(final Model model) {
        final Submit submit = new Submit();
        model.addAttribute("submit", submit);
        final List<String> rank = Arrays.asList("I", "II", "III", "IV", "V", "VI", "X");
        model.addAttribute("rank", rank);
        return "submit_form";
    }

    @Override
    public String addSubmit(Submit submit, BindingResult bindingResult, Model model) {
        if (submitRepository.count() >= 4) {
            log.info("Submit reached max value");
            return "submit_max";
        } else if (bindingResult.hasErrors()) {
            final List<String> rank = Arrays.asList("I", "II", "III", "IV", "V", "VI", "X");
            model.addAttribute("rank", rank);
            return "submit_form";
        } else {
            submitRepository.save(submit);
            log.info("Successfully added new submit");
            return "submit_ok";
        }
    }

    @Override
    public String findPaginated(int pageNo, String sortField, String sortDir, Model model) {
        final Page<Submit> paginated = findPaginatedSorted(pageNo, sortField, sortDir);
        final List<Submit> submits = paginated.getContent();
        final int totalPages = paginated.getTotalPages();
        final long totalElements = paginated.getTotalElements();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalElements", totalElements);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("submits", submits);
        return "submit_list";
    }

    @Override
    public String deleteSubmit(final long id, final Model model, final Submit submit) {
        final List<Submit> allSubmits = submitRepository.findAll();
        final Submit submitFindById = submitRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid ID : " + id));
        submitRepository.delete(submitFindById);
        model.addAttribute("submits", allSubmits);
        return "submit_list";
    }

    @Override
    public String deleteAll(final Model model, final Submit submit) {
        final List<Submit> all = submitRepository.findAll();
        submitRepository.deleteAll();
        model.addAttribute("submits", all);
        log.info("Successfully deleted all submits");
        return "redirect:/list";
    }

    @Override
    public String showUpdateForm(final long id, final Model model) {
        final Submit submit = submitRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid ID: " + id));
        final List<String> rank = Arrays.asList("I", "II", "III", "IV", "V", "VI", "X");
        model.addAttribute("rank", rank);
        model.addAttribute("submit", submit);
        return "submit_update";
    }

    @Override
    public String updateSubmit(final long id, final Submit submit, final BindingResult result, final Model model) {
        if (result.hasErrors()) {
            submit.setId(id);
            return "submit_update";
        }
        increase(submit);
        submitRepository.save(submit);
        final List<Submit> allSubmits = submitRepository.findAll();
        model.addAttribute("submit", allSubmits);
        log.info("Successfully updated submit with id " + submit.getId());
        return "redirect:/list";
    }

    private Page<Submit> findPaginatedSorted(final int pageNo, final String sortField, final String sortDirection) {
        final Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();
        final Pageable pageable = PageRequest.of(pageNo - 1, 10, sort);
        return this.submitRepository.findAll(pageable);
    }

    private void increase(final Submit submit) {
        submit.setScore(submit.getWins() * 3);
        submit.setScore(submit.getScore() - submit.getLoses() - 1);
        submit.setScore(submit.getScore() + submit.getDraws() + 1);
    }
}