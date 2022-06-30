package ar.edu.unju.fi.edu.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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

import ar.edu.unju.fi.edu.entity.OfertaLaboral;
import ar.edu.unju.fi.edu.service.imp.EmpleadorServiceImp;
import ar.edu.unju.fi.edu.service.imp.OfertaLaboralServiceImp;

@Controller
@RequestMapping("/olaboral")
public class OfertaLaboralController {

	@Autowired
	private OfertaLaboralServiceImp olService;
	
	@Autowired
	private EmpleadorServiceImp empleadorService;
	
	private static final Log LOGGER = LogFactory.getLog(OfertaLaboralController.class);
	
	@GetMapping("/lista")
	public String getOLaboralesPage(Model model) {
		model.addAttribute("olaborales", this.olService.getListaOfertaLaboral());
		return "lista_olaboral";
	}
	
	@GetMapping("/nuevo/{e_cuit}")
	public String getNuevaOLaboral(@PathVariable(value = "e_cuit") long e_cuit, Model model) throws Exception {
		model.addAttribute("olaboral", this.olService.getOfertaLaboral(this.empleadorService.buscarEmpleador(e_cuit)));
		return "nuevo_olaboral";
	}
	
	@PostMapping("/guardar")
	public ModelAndView postGuardarNuevaOLaboral(@Validated @ModelAttribute("olaboral") OfertaLaboral nuevaOL, BindingResult bindingR) throws Exception {
		if(bindingR.hasErrors()) {
			LOGGER.info("METHOD: postGuardarNuevaOLaboral() - INFO: Se detecto un error mediante las validaciones");
			ModelAndView modelAV = new ModelAndView("nuevo_olaboral");
			modelAV.addObject("olaboral", nuevaOL);
			return modelAV;
		}
		else{
			ModelAndView modelAV = new ModelAndView("redirect:/olaboral/lista");
			if(this.olService.guardarOfertaLaboral(nuevaOL)) {
				
			}
			else {
				
			}
			return modelAV;
		}
	}
	
	@GetMapping("/editar/{ol_id}")
	public ModelAndView getEditarOLaboral(@PathVariable(value = "ol_id") long ol_id) throws Exception {
		ModelAndView modelAV = new ModelAndView("edicion_olaboral");
		modelAV.addObject("olaboral", this.olService.buscarOfertaLaboral(ol_id));
		return modelAV;
	}
	
	@PostMapping("/modificar")
	public ModelAndView postModificarOLaboral(@Validated @ModelAttribute("olaboral") OfertaLaboral olMod, BindingResult bindingR) throws Exception {
		if(bindingR.hasErrors()) {
			ModelAndView modelAV = new ModelAndView("nuevo_olaboral");
			modelAV.addObject("olaboral", olMod);
			return modelAV;
		}
		else{
			ModelAndView modelAV = new ModelAndView("redirect:/olaboral/lista");
			this.olService.modificarOfertaLaboral(olMod);
			return modelAV;
		}
	}
	
	@GetMapping("/desactivar/{ol_id}")
	public ModelAndView getDesactivarOLaboral(@PathVariable(value = "ol_id") long ol_id) throws Exception {
		ModelAndView modelAV = new ModelAndView("redirect:/olaboral/lista");
		this.olService.modificarDisponibilidad(ol_id);
		return modelAV;
	}
}