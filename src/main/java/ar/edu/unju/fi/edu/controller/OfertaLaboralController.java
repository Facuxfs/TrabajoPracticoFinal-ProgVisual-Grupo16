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

import ar.edu.unju.fi.edu.entity.Ciudadano;
import ar.edu.unju.fi.edu.entity.OfertaLaboral;
import ar.edu.unju.fi.edu.service.ICiudadanoService;
import ar.edu.unju.fi.edu.service.IOfertaLaboralService;
import ar.edu.unju.fi.edu.service.imp.EmpleadorServiceImp;
import ar.edu.unju.fi.edu.service.imp.OfertaLaboralServiceImp;

@Controller
@RequestMapping("/olaboral")
public class OfertaLaboralController {

	@Autowired
	private OfertaLaboralServiceImp olService;
	
	@Autowired
	private EmpleadorServiceImp empleadorService;
	
	@Autowired
	private ICiudadanoService ciudadanoService;
	
	
	private static final Log LOGGER = LogFactory.getLog(OfertaLaboralController.class);
	/*
	 * metodo que devuelve una lista una lista de objetos de tipo oferta laboral guardados en la bd
	 */
	@GetMapping("/lista")
	public String getOLaboralesPage(Model model) {
		model.addAttribute("olaborales", this.olService.getListaOfertaLaboral());
		return "lista_olaboral";
	}
	/*
	 * metodo que permite generar una nueva oferta laboral
	 */
	@GetMapping("/nuevo/{e_cuit}")
	public String getNuevaOLaboral(@PathVariable(value = "e_cuit") long e_cuit, Model model) throws Exception {
		model.addAttribute("olaboral", this.olService.getOfertaLaboral(this.empleadorService.buscarEmpleador(e_cuit)));
		model.addAttribute("cuit", e_cuit);
		return "nuevo_olaboral";
	}
	/*
	 * metodo que permite guardar en la bd un objeto de tipo oferta  laboral
	 */
	@PostMapping("/guardar")
	public ModelAndView postGuardarNuevaOLaboral(@Validated @ModelAttribute("olaboral") OfertaLaboral nuevaOL, BindingResult bindingR) throws Exception {
		if(bindingR.hasErrors()) {
			LOGGER.info("METHOD: postGuardarNuevaOLaboral() - INFO: Se detecto un error mediante las validaciones");
			ModelAndView modelAV = new ModelAndView("nuevo_olaboral");
			modelAV.addObject("olaboral", nuevaOL);
			return modelAV;
		}
		else{
			String cuit = String.valueOf(nuevaOL.getContacto().getCuit());
			ModelAndView modelAV = new ModelAndView("redirect:/empleador/vistaempleador/" + cuit);
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
	
	@GetMapping("registrarPostulantes/{dni}/{id}")
	public ModelAndView registrarPostulantes(@PathVariable(value = "dni") int dni, @PathVariable("id") long id) throws Exception {
		String dnic = String.valueOf(ciudadanoService.buscarCiudadano(dni).getDni());
		ModelAndView mav = new ModelAndView("redirect:/ciudadano/verofertas/" + dnic);
		mav.addObject("oferta", olService.buscarOfertaLaboral(id));
		Ciudadano unCiudadano = ciudadanoService.buscarCiudadano(dni);
		olService.agregarPostulante(id, unCiudadano);
		System.out.println(olService.buscarOfertaLaboral(id).getPostulantes());
		return mav;
	}
	
}