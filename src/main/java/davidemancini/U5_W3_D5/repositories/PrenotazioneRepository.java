package davidemancini.U5_W3_D5.repositories;

import davidemancini.U5_W3_D5.entities.PrenotazioneEvento;
import davidemancini.U5_W3_D5.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PrenotazioneRepository extends JpaRepository<PrenotazioneEvento, UUID> {
    List<PrenotazioneEvento> findAllByUser(User user);
}
