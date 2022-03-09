package pl.markowski.tournament.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.markowski.tournament.model.Submit;

@Repository
public interface SubmitRepository extends JpaRepository<Submit, Long> {
}