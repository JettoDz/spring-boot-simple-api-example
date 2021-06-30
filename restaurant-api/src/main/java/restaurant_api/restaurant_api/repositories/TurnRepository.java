package restaurant_api.restaurant_api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import restaurant_api.restaurant_api.entities.Turn;

@Repository
public interface TurnRepository extends JpaRepository<Turn, Integer> {

	Optional<Turn> findByName(String name);
}
