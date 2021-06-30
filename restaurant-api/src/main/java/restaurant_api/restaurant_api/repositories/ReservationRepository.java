package restaurant_api.restaurant_api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import restaurant_api.restaurant_api.entities.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer>{
	
	Optional<Reservation> findById(int id);
	
	Optional<Reservation> findByLocator(String locator);
	
	@Modifying
	@Transactional
	Optional<Reservation> deleteByLocator(String locator);
	
	Optional<Reservation> findByTurnAndRestaurant(String turn, int restaurantId);

}
