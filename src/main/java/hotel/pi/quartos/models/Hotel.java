package hotel.pi.quartos.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Hotel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nomeHotel;
	private String cidadeHotel;
	private String gerenteHotel;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeHotel() {
		return nomeHotel;
	}

	public void setNomeHotel(String nomeHotel) {
		this.nomeHotel = nomeHotel;
	}

	public String getCidadeHotel() {
		return cidadeHotel;
	}

	public void setCidadeHotel(String cidadeHotel) {
		this.cidadeHotel = cidadeHotel;
	}

	public String getGerenteHotel() {
		return gerenteHotel;
	}

	public void setGerenteHotel(String gerenteHotel) {
		this.gerenteHotel = gerenteHotel;
	}
}