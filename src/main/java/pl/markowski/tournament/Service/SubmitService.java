package pl.markowski.tournament.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.markowski.tournament.model.Submit;
import pl.markowski.tournament.repo.SubmitRepo;

@Service
public class SubmitService {

    private SubmitRepo submitRepo;

    @Autowired
    public SubmitService(SubmitRepo submitRepo) {
        this.submitRepo = submitRepo;
    }

    public Page<Submit> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo -1, pageSize, sort);
        return this.submitRepo.findAll(pageable);
     }

     public void deleteSubmitById(long id) {
         Submit submit = submitRepo.findById(id)
                 .orElseThrow(() -> new IllegalArgumentException("Invalid ID : " + id));
        this.submitRepo.delete(submit);
     }

     public void deleteSubmitAll() {
        this.submitRepo.deleteAll();
     }
}
