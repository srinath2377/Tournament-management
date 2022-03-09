package pl.markowski.tournament.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.markowski.tournament.model.Information;

import java.util.List;

@Repository
public interface InformationRepository extends JpaRepository<Information, Long> {

    List<Information> findAllByOrderByIdDesc();
}