package hotel.pi.quartos.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import hotel.pi.quartos.models.Hotel;
import hotel.pi.quartos.models.Quarto;

public interface QuartoRepository extends JpaRepository<Quarto, Long> {

	List<Quarto> findByHotel(Hotel hotel);
}