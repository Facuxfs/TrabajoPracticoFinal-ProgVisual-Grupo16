package ar.edu.unju.fi.edu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.edu.entity.Ciudadano;
import ar.edu.unju.fi.edu.service.ICiudadanoService;

@Controller
@RequestMapping("/ciudadano")
public class CiudadanoController {
	
	
	@Autowired
	private ICiudadanoService ciudadanoService;
	
	
	@GetMapping("/nuevo")
	public String nuevoCiudadano(Model model) {
		model.addAttribute("ciudadano", new Ciudadano());
		return "registro_ciudadano";
	}
	
	@PostMapping("/guardar")
	public ModelAndView guardarCiudadano (@ModelAttribute("ciudadano") Ciudadano ciudadano) {
		ModelAndView  mav = new ModelAndView("redirect:/ciudadano/listaciudadanos");
		ciudadanoService.guardarCiudadano(ciudadano);
		mav.addObject("ciudadanos", ciudadanoService.getListaCiudadano());
		return mav;
	}
	
	@GetMapping("/listaciudadanos")
		public ModelAndView mostrarLista() {
		ModelAndView mav = new ModelAndView("lista_ciudadanos");
		mav.addObject("ciudadanos", ciudadanoService.getListaCiudadano());
		return mav;
	}
	

}
