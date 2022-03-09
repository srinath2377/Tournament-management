package pl.markowski.tournament.service;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import pl.markowski.tournament.model.Submit;

public interface SubmitService {

    String getAllSubmits(final Model model);

    String showSubmitForm(final Model model);

    String addSubmit(final Submit submit, final BindingResult bindingResult, final Model model);

    String findPaginated(final int pageNo, final String sortField, final String sortDir, final Model model);

    String deleteSubmit(final long id, final Model model, final Submit submit);

    String deleteAll(final Model model, final Submit submit);

    String showUpdateForm(final long id, final Model model);

    String updateSubmit(final long id, final Submit submit, final BindingResult result, final Model model);
}