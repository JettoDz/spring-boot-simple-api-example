package restaurant_api.restaurant_api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import restaurant_api.restaurant_api.entities.Restaurant;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer>{
	
	Optional<Restaurant> findById(int id);
	
	Optional<Restaurant> findByName(String name);
	
	
}
