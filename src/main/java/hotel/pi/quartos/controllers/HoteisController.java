package hotel.pi.quartos.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import hotel.pi.quartos.models.Hotel;
import hotel.pi.quartos.models.Visitante;
import hotel.pi.quartos.repositories.HotelRepository;
import hotel.pi.quartos.repositories.VisitanteRepository;

@Controller
@RequestMapping("/hoteis")
public class HoteisController {

	@Autowired
	HotelRepository hr;
	
	@Autowired
	VisitanteRepository vr;

	@GetMapping("/form")
	public String form(Hotel hotel) {
		return "hoteis/formHotel";
	}

	@PostMapping
	public String salvar(@Valid Hotel hotel, BindingResult result) {
		
		if(result.hasErrors()) {
			return form(hotel);
		}

		System.out.println(hotel);
		hr.save(hotel);

		return "redirect:/hoteis";
	}

	@GetMapping
	public ModelAndView listar() {
		List<Hotel> hoteis = hr.findAll();
		ModelAndView mv = new ModelAndView("hoteis/lista");
		mv.addObject("hoteis", hoteis);
		return mv;
	}

	@GetMapping("/{id}")
	public ModelAndView detalhar(@PathVariable Long id, Visitante visitante) {
		ModelAndView md = new ModelAndView();
		Optional<Hotel> opt = hr.findById(id);

		if (opt.isEmpty()) {
			md.setViewName("redirect:/hoteis");
			return md;
		}

		md.setViewName("hoteis/detalhes");
		Hotel hotel = opt.get();
		md.addObject("hotel", hotel);

		List<Visitante> visitantes = vr.findByHotel(hotel);
		md.addObject("visitantes", visitantes);

		return md;
	}

	@PostMapping("/{idHotel}")
	public String salvarVisitante(@PathVariable Long idHotel, @Valid Visitante visitante, BindingResult result) {

		if(result.hasErrors()) {
			return "redirect:/hoteis/{idHotel}";
		}
		
		System.out.println("Id do hotel: " + idHotel);
		System.out.println(visitante);

		Optional<Hotel> opt = hr.findById(idHotel);
		if (opt.isEmpty()) {
			return "redirect:/hoteis";
		}

		Hotel hotel = opt.get();
		visitante.setHotel(hotel);

		vr.save(visitante);

		return "redirect:/hoteis/{idHotel}";
	}

	@GetMapping("/{id}/remover")
	public String apagarHotel(@PathVariable Long id) {

		Optional<Hotel> opt = hr.findById(id);

		if (!opt.isEmpty()) {
			Hotel hotel = opt.get();

			List<Visitante> visitantes = vr.findByHotel(hotel);

			vr.deleteAll(visitantes);
			hr.delete(hotel);
		}

		return "redirect:/hoteis";
	}

	@GetMapping("/{idHotel}/visitantes/{idVisitante}/remover")
	public String apagarHotel(@PathVariable Long idHotel, @PathVariable Long idVisitante) {

		Optional<Visitante> opt = vr.findById(idVisitante);

		if (!opt.isEmpty()) {
			Visitante visitante = opt.get();
			vr.delete(visitante);
		}

		return "redirect:/hoteis/{idHotel}";
	}

	@GetMapping("/{id}/selecionar")
	public ModelAndView selecionarHotel(@PathVariable Long id) {
		ModelAndView md = new ModelAndView();
		Optional<Hotel> opt = hr.findById(id);
		if (opt.isEmpty()) {
			md.setViewName("redirect:/hoteis");
			return md;
		}

		Hotel hotel = opt.get();
		md.setViewName("hoteis/formHotel");
		md.addObject("hotel", hotel);

		return md;
	}

	@GetMapping("/{idHotel}/visitantes/{idVisitante}/selecionar")
	public ModelAndView selecionarVisitante(@PathVariable Long idHotel, @PathVariable Long idVisitante) {
		ModelAndView md = new ModelAndView();

		Optional<Hotel> optHotel = hr.findById(idHotel);
		Optional<Visitante> optVisitante = vr.findById(idVisitante);

		if (optHotel.isEmpty() || optVisitante.isEmpty()) {
			md.setViewName("redirect:/hoteis");
			return md;
		}

		Hotel hotel = optHotel.get();
		Visitante visitante = optVisitante.get();

		if (hotel.getId() != visitante.getHotel().getId()) {
			md.setViewName("redirect:/hoteis");
			return md;
		}

		md.setViewName("hoteis/detalhes");
		md.addObject("visitante", visitante);
		md.addObject("hotel", hotel);
		md.addObject("visitantes", vr.findByHotel(hotel));

		return md;
	}
}