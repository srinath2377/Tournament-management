package pl.markowski.tournament.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.markowski.tournament.model.Submit;

@Repository
public interface SubmitRepo extends JpaRepository<Submit, Long> {

    Submit findByName(String name);
}
