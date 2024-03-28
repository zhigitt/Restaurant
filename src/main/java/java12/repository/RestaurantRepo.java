package java12.repository;

import java12.entities.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface RestaurantRepo extends JpaRepository<Restaurant, Long> {


}
