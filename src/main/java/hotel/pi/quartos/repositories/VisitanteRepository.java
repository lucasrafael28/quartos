package hotel.pi.quartos.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hotel.pi.quartos.models.Hotel;
import hotel.pi.quartos.models.Visitante;

@Repository
public interface VisitanteRepository extends JpaRepository<Visitante, Long> {

	List<Visitante> findByHotel(Hotel hotel);
}