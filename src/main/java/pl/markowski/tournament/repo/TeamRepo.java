package pl.markowski.tournament.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.markowski.tournament.model.Team;

@Repository
public interface TeamRepo extends JpaRepository<Team, Long> {

    Team findByName(String name);
}
