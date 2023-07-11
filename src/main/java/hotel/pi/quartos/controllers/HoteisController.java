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
import hotel.pi.quartos.models.Quarto;
import hotel.pi.quartos.repositories.HotelRepository;
import hotel.pi.quartos.repositories.QuartoRepository;

@Controller
@RequestMapping("/hoteis")
public class HoteisController {

	@Autowired
	HotelRepository hr;

	@Autowired
	QuartoRepository qr;

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

		List<Quarto> quartos = qr.findByHotel(hotel);
		md.addObject("quartos", quartos);

		return md;
	}

	@PostMapping("/{idHotel}")
	public String salvarQuarto(@PathVariable Long idHotel, Quarto quarto) {

		System.out.println("Id do hotel: " + idHotel);
		System.out.println(quarto);

		Optional<Hotel> opt = hr.findById(idHotel);
		if (opt.isEmpty()) {
			return "redirect:/hoteis";
		}

		Hotel hotel = opt.get();
		quarto.setHotel(hotel);

		qr.save(quarto);

		return "redirect:/hoteis/{idHotel}";
	}

	@GetMapping("/{id}/remover")
	public String apagarHotel(@PathVariable Long id) {

		Optional<Hotel> opt = hr.findById(id);

		if (!opt.isEmpty()) {
			Hotel hotel = opt.get();

			List<Quarto> quartos = qr.findByHotel(hotel);

			qr.deleteAll(quartos);
			hr.delete(hotel);
		}

		return "redirect:/hoteis";
	}

	@GetMapping("/{idHotel}/quartos/{idQuarto}/remover")
	public String apagarHotel(@PathVariable Long idHotel, @PathVariable Long idQuarto) {

		Optional<Quarto> opt = qr.findById(idQuarto);

		if (!opt.isEmpty()) {
			Quarto quarto = opt.get();
			qr.delete(quarto);
		}

		return "redirect:/hoteis/{idHotel}";
	}
}