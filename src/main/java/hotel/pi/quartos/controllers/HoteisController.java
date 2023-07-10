package hotel.pi.quartos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import hotel.pi.quartos.models.Hotel;
import hotel.pi.quartos.repositories.HotelRepository;

@Controller
public class HoteisController {

	@Autowired
	HotelRepository hr;
	
	@RequestMapping("/hoteis/form")
	public String form() {
		return "hoteis/formHotel";
	}
	
	@PostMapping("/hoteis")
	public String adicionar(Hotel hotel) {

		System.out.println(hotel);
		hr.save(hotel);

		return "hoteis/hotel-adicionado";
	}
}