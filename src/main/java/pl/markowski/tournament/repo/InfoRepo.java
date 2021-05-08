package pl.markowski.tournament.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.markowski.tournament.model.Info;

@Repository
public interface InfoRepo extends JpaRepository<Info, Long> {

    Info findByText(String text);
}
