package hotel.pi.quartos.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import hotel.pi.quartos.models.Hotel;
import hotel.pi.quartos.repositories.HotelRepository;

@Controller
@RequestMapping("/hoteis")
public class HoteisController {

	@Autowired
	HotelRepository hr;

	@GetMapping("/form")
	public String form() {
		return "hoteis/formHotel";
	}

	@PostMapping
	public String adicionar(Hotel hotel) {

		System.out.println(hotel);
		hr.save(hotel);

		return "hoteis/hotel-adicionado";
	}

	@GetMapping
	public ModelAndView listar() {
		List<Hotel> hoteis = hr.findAll();
		ModelAndView mv = new ModelAndView("hoteis/lista");
		mv.addObject("hoteis", hoteis);
		return mv;
	}

	@GetMapping("/{id}")
	public ModelAndView detalhar(@PathVariable Long id) {
		ModelAndView md = new ModelAndView();
		Optional<Hotel> opt = hr.findById(id);

		if (opt.isEmpty()) {
			md.setViewName("redirect:/hoteis");
			return md;
		}

		md.setViewName("hoteis/detalhes");
		Hotel hotel = opt.get();
		md.addObject("hotel", hotel);

		return md;
	}
}