package antoniobertuccio.u5w2d3.repositories;

import antoniobertuccio.u5w2d3.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AuthorsDAO extends JpaRepository<Author, UUID> {
  Optional<Author> findByEmail(String email);

  boolean existsByEmail(String email);
}
