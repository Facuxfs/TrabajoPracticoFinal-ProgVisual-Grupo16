package ar.edu.unju.fi.edu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

@Controller

@RequestMapping("/curriculum")
public class CurriculumController {

	@Autowired
	private ICurriculumService curriculumService;

	@Autowired
	private ICiudadanoService ciudadanoService;
/*
 * metodo que permite guardar un objeto de tipo curriculum asociado a un objeto de tipo ciudadano en la bd
 */
	@PostMapping("/guardarcv")
	public ModelAndView guardarCv(@ModelAttribute("cv") Curriculum cv,@ModelAttribute("ciudadano") Ciudadano ciudadano, BindingResult bindingR) {
		if(bindingR.hasErrors()) {
			ModelAndView modelAV = new ModelAndView("nuevo_curriculum");
			modelAV.addObject("cv", cv);
			modelAV.addObject("ciudadano", ciudadano);
			return modelAV;
		}
		else {
			curriculumService.guardarCurriculum(cv);
			String dnic = String.valueOf(cv.getCiudadano().getDni());
			ModelAndView mav = new ModelAndView("redirect:/ciudadano/vistaciudadano/" + dnic);
			mav.addObject(ciudadano);
			
			return mav;
		}		
	}
	/*
	 * metodo que permite ver el curriculum de un objeto de tipo ciudadano
	 */
	@GetMapping("/vercv/{dni}")
	public String verCv(@PathVariable(value = "dni") int dni,Model model) {
		if(ciudadanoService.buscarCiudadano(dni).getCv()!=null) {
			System.out.println("este es el usuario "+dni);
			model.addAttribute("ciudadano",ciudadanoService.buscarCiudadano(dni));
			
			return "ver_curriculum";}
		else {
			model.addAttribute("dni", dni);
			return "no_existe_cv";
		}
	}
	/*
	 * metodo que permite al objeto de tipo empleador ver el curriculum de un objeto de tipo ciudadano 
	 */
	@GetMapping("/vercvempleador/{dni}/{id}/{cuit}")
	public String verCvEmpleador(@PathVariable(value = "dni") int dni,@PathVariable(value = "id") long id,@PathVariable(value = "cuit") long cuit,Model model) {
		if(ciudadanoService.buscarCiudadano(dni).getCv()!=null) {
		System.out.println("este es el usuario "+dni);
		model.addAttribute("ciudadano",ciudadanoService.buscarCiudadano(dni));
		model.addAttribute("id", id);
		return "ver_cvempleador";}
		else {
			model.addAttribute("dni", dni);
			model.addAttribute("cuit", cuit);
			return "no_creo_cv";
		}
	}
	
	/*
	 * metodo que permite modificar los atributos de un ibjeto de tipo curriculum
	 */
	@GetMapping("/modificar/{dni}")
	public String modificarCv(@PathVariable(value = "dni") int dni,Model model) {
		if (ciudadanoService.buscarCiudadano(dni).getCv()!=null) {
		model.addAttribute("ciudadano",ciudadanoService.buscarCiudadano(dni));
		Curriculum cv = ciudadanoService.buscarCiudadano(dni).getCv();
		model.addAttribute("cv", cv);
		model.addAttribute("dni", dni);
		return "editar_curriculum";}
		else {
			model.addAttribute("dni", dni);
			return "no_existe_cv";
		}
	}
	/*
	 * metodo que permite guardar los cambios en los atributos de un objteo de tipo curriculum en la bd
	 */
	@PostMapping("/modificarcv/{dni}")
	public ModelAndView modificarCvModificado(@ModelAttribute("cv") Curriculum cv,@ModelAttribute("ciudadano") Ciudadano ciudadano) {
		curriculumService.guardarCurriculum(cv);
		String dnic = String.valueOf(cv.getCiudadano().getDni());
		ModelAndView mav = new ModelAndView("redirect:/ciudadano/vistaciudadano/" + dnic);
		mav.addObject(ciudadano);
		
		return mav;
	}
}