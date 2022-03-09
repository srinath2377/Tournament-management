package pl.markowski.tournament.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.markowski.tournament.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(final String username);
}