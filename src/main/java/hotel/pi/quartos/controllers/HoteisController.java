package hotel.pi.quartos.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HoteisController {

	@RequestMapping("/hoteis/form")
	public String form() {
		return "formHotel";
	}
}