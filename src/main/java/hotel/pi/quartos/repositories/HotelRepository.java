package hotel.pi.quartos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hotel.pi.quartos.models.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long>{

}