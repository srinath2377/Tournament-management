package pl.markowski.tournament.service;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import pl.markowski.tournament.model.Information;

public interface InformationService {

    String getAllInformation(final Model model);

    String showInformationForm(final Model model);

    String addInformation(final Information information, final BindingResult bindingResult, final Model model);

    String findPaginated(final int pageNo, final Model model);

    String deleteInformationSubmit(final long id, final Model model);

    String deleteAllInformation(final Model model);

    String showUpdateInformationForm(final long id, final Model model);

    String updateInformationSubmit(final long id, final Information information, final BindingResult result, final Model model);
}