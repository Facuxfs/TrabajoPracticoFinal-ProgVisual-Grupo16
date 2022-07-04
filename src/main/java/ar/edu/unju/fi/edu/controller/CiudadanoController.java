package ar.edu.unju.fi.edu.controller;

import java.util.List;

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
import ar.edu.unju.fi.edu.entity.Empleador;
import ar.edu.unju.fi.edu.entity.OfertaLaboral;
import ar.edu.unju.fi.edu.service.ICiudadanoService;
import ar.edu.unju.fi.edu.service.ICurriculumService;
import ar.edu.unju.fi.edu.service.ICursoService;
import ar.edu.unju.fi.edu.service.IEmpleadorService;
import ar.edu.unju.fi.edu.service.IOfertaLaboralService;


@Controller
@RequestMapping("/ciudadano")
public class CiudadanoController {

	@Autowired
	private ICiudadanoService ciudadanoService;

	@Autowired
	private ICurriculumService curriculumService;
	
	@Autowired
	private IOfertaLaboralService ofertalaboralService;

	@Autowired
	private ICursoService cursoService;
	
	@Autowired
	private IEmpleadorService empleadorService;
	
	@GetMapping("/nuevo")
	public String nuevoCiudadano(Model model) {
		model.addAttribute("ciudadano", new Ciudadano());
		return "registro_ciudadano";
	}

	@PostMapping("/guardar")
	public ModelAndView guardarCiudadano(@Validated @ModelAttribute("ciudadano") Ciudadano ciudadano, 
		BindingResult bindingResult) throws Exception{
		if (bindingResult.hasErrors()) {
			ModelAndView modelAV = new ModelAndView("registro_ciudadano");
			modelAV.addObject("ciudadano", ciudadano);
			return modelAV;
		}else {
		String dnic = String.valueOf(ciudadano.getDni());
		ciudadano.setEstado(true);
		ModelAndView mav = new ModelAndView("redirect:/ciudadano/vistaciudadano/" + dnic);
		ciudadanoService.guardarCiudadano(ciudadano);
		mav.addObject(ciudadano);
		return mav;
		}
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
	public ModelAndView EditarCiudadano(@PathVariable(value = "dni") int dni) {
		ModelAndView model = new ModelAndView("editar_ciudadano");
		model.addObject("ciudadano", ciudadanoService.buscarCiudadano(dni));
		model.addObject("dni", dni);
		return model;
	}

	@PostMapping("/modificar")
	public ModelAndView modificarCiudadano(@Validated @ModelAttribute("ciudadano") Ciudadano ciudadano,
			BindingResult bindingResult, Model model) throws Exception {
	//	if (bindingResult.hasErrors()) {
			//System.out.println("nada nada nada");
			//ModelAndView mav = new ModelAndView("editar_ciudadano");
			//mav.addObject("ciudadano", ciudadano);
		//	return mav;
	
	//}else {
			
		String dnic = String.valueOf(ciudadano.getDni());
		ModelAndView mav = new ModelAndView("redirect:/ciudadano/vistaciudadano/" + dnic);
		ciudadanoService.modificarCiudadano(ciudadano);
		model.addAttribute("dni",ciudadano.getDni());
		return mav;
	//	}
	}
	


	@GetMapping("/crearcv/{dni}")
	public String CrearCv(@PathVariable(value = "dni") int dni, Model model) {
		if(ciudadanoService.buscarCiudadano(dni).getCv()==null) {
		model.addAttribute("ciudadano",ciudadanoService.buscarCiudadano(dni));
		Curriculum cv = curriculumService.getCurriculum();
		cv.setCiudadano(ciudadanoService.buscarCiudadano(dni));
		model.addAttribute("cv", cv);
		model.addAttribute("dni", dni);
		return "nuevo_curriculum";
		}
		else {
		
		model.addAttribute("dni", dni);
		return "ya_existe_cv";
		}
		
	}
	
	@GetMapping("/verofertas/{dni}")
	public ModelAndView mostrarListaOfertas(@PathVariable(value = "dni") int dni) {
		ModelAndView model = new ModelAndView("lista_ofertasciudadano");
		model.addObject("ofertas", ofertalaboralService.getListaOfertaLaboral());
		model.addObject("oferta", new OfertaLaboral());
		model.addObject("dni", dni);
		model.addObject("empleador", new Empleador());
		return model;
	}
	
	@PostMapping("/buscarprovincia/{dni}")
	public ModelAndView mostrarOfertaProvincia(@ModelAttribute ("empleador") Empleador empleador,@PathVariable(value = "dni") int dni) {
		ModelAndView model = new ModelAndView("lista_ofertasprovincia");
		model.addObject("ofertas", ofertalaboralService.getListaOfertasProvincia(empleadorService.getListaEmpleadoresProvincia(empleador.getProvincia())));
		model.addObject("dni",dni );
		System.out.println(ofertalaboralService.getListaOfertasProvincia(empleadorService.getListaEmpleadoresProvincia(empleador.getProvincia())));
		return model;
	}
	
	@GetMapping("/verresultados/{dni}")
	public ModelAndView mostrarListaResultados(@PathVariable(value = "dni") int dni) {
		ModelAndView model = new ModelAndView("lista_resultados");
		Ciudadano unCiudadano = ciudadanoService.buscarCiudadano(dni);
		model.addObject("resultados", unCiudadano.getPostulaciones());
		model.addObject("oferta", new OfertaLaboral());
		model.addObject("dni", dni);
		return model;
	}
	
	@GetMapping("/verocursos/{dni}")
	public ModelAndView mostrarListaCursos(@PathVariable(value = "dni") int dni) {
		ModelAndView model = new ModelAndView("lista_cursosciudadano");
		model.addObject("cursos", cursoService.getListaCurso());
		model.addObject("dni", dni);
		return model;
	}

}
