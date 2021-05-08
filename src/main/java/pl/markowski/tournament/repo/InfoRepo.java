package pl.markowski.tournament.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.markowski.tournament.model.Info;

import java.util.List;

@Repository
public interface InfoRepo extends JpaRepository<Info, Long> {

    public List<Info> findAllByOrderById();
    Info findByText(String text);
}
