package ar.edu.unju.fi.edu.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.edu.entity.Ciudadano;
import ar.edu.unju.fi.edu.entity.Curriculum;
import ar.edu.unju.fi.edu.service.ICiudadanoService;
import ar.edu.unju.fi.edu.service.ICurriculumService;
import ar.edu.unju.fi.edu.service.imp.CurriculumServiceImp;

@Controller
@RequestMapping("/ciudadano")
public class CiudadanoController {

	@Autowired
	private ICiudadanoService ciudadanoService;

	@Autowired
	private ICurriculumService curriculumService;

	@GetMapping("/nuevo")
	public String nuevoCiudadano(Model model) {
		model.addAttribute("ciudadano", new Ciudadano());
		return "registro_ciudadano";
	}

	@PostMapping("/guardar")
	public ModelAndView guardarCiudadano(@ModelAttribute("ciudadano") Ciudadano ciudadano) {
		String dnic = String.valueOf(ciudadano.getDni());
		ModelAndView mav = new ModelAndView("redirect:/ciudadano/vistaciudadano/" + dnic);
		ciudadanoService.guardarCiudadano(ciudadano);
		mav.addObject(ciudadano);
		return mav;
	}

	@GetMapping("/listaciudadanos")
	public ModelAndView mostrarLista() {
		ModelAndView mav = new ModelAndView("lista_ciudadanos");
		mav.addObject("ciudadanos", ciudadanoService.getListaCiudadano());
		return mav;
	}

	@GetMapping("/vistaciudadano/{dni}")
	public ModelAndView mostrarMenuCiudadano(@PathVariable(value = "dni") int dni) {
		ModelAndView mav = new ModelAndView("vista_ciudadano");
		mav.addObject("ciudadano", ciudadanoService.buscarCiudadano(dni));
		return mav;
	}

	@GetMapping("/editar/{dni}")
	public String EditarCiudadano(@PathVariable(value = "dni") int dni, Model model) {
		model.addAttribute("ciudadano", ciudadanoService.buscarCiudadano(dni));
		return "editar_ciudadano";
	}

	@PostMapping("/modificar")
	public ModelAndView modificarCiudadano(@Validated @ModelAttribute("ciudadano") Ciudadano ciudadano,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			ModelAndView mav = new ModelAndView("editar_ciudadano");
			mav.addObject("ciudadano", ciudadano);
			System.out.println(mav.toString());
			return mav;
		}
		ModelAndView mav = new ModelAndView("redirect:/ciudadano/listaciudadanos");
		ciudadanoService.modificarCiudadano(ciudadano);
		return mav;
	}

	@GetMapping("/crearcv/{dni}")
	public String CrearCv(@PathVariable(value = "dni") int dni, Model model) {
		model.addAttribute("ciudadano",ciudadanoService.buscarCiudadano(dni));
	
		Curriculum cv = curriculumService.getCurriculum();
		cv.setCiudadano(ciudadanoService.buscarCiudadano(dni));
		model.addAttribute("cv", cv);
		
		return "nuevo_curriculum";
	}

	@PostMapping("/guardarcv")
	public ModelAndView guardarCv(@ModelAttribute("cv") Curriculum cv,@ModelAttribute("ciudadano") Ciudadano ciudadano) {
		curriculumService.guardarCurriculum(cv);
		System.out.println(ciudadano.getDni());
		System.out.println(cv.getCiudadano().getDni()+ "dni ------------");
		String dnic = String.valueOf(ciudadano.getDni());
		ModelAndView mav = new ModelAndView("redirect:/ciudadano/vistaciudadano/" + dnic);
		
		mav.addObject(ciudadano);
		
		return mav;
	}

}
