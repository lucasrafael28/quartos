package hotel.pi.quartos.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import hotel.pi.quartos.models.Hotel;

@Controller
public class HoteisController {

	@RequestMapping("/hoteis/form")
	public String form() {
		return "formHotel";
	}
	
	@PostMapping("/hoteis")
	public String adicionar(Hotel hotel) {

		System.out.println(hotel);

		return "hotel-adicionado";
	}
}