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
import ar.edu.unju.fi.edu.entity.Empleador;
import ar.edu.unju.fi.edu.entity.OfertaLaboral;
import ar.edu.unju.fi.edu.service.ICiudadanoService;
import ar.edu.unju.fi.edu.service.IEmpleadorService;
import ar.edu.unju.fi.edu.service.IOfertaLaboralService;

@Controller
@RequestMapping("/empleador")
public class EmpleadorController {
	
	@Autowired
	private IEmpleadorService empleadorService;
	
	@Autowired
	private IOfertaLaboralService ofertalaboralService;
	
	@Autowired
	private ICiudadanoService ciudadanoService;
	
	
	private static final Log LOGGER = LogFactory.getLog(EmpleadorController.class);
	
	@GetMapping("/lista")
	public String getEmpleadoresPage(Model model) {
		model.addAttribute("empleadores", this.empleadorService.getListaEmpleado());
		return "lista_empleadores";
	}
	
	@GetMapping("/nuevo")
	public String getNuevoEmpleador(Model model) {
		model.addAttribute("empleador", this.empleadorService.getEmpleador());
		return "nuevo_empleador";
	}
	
	@PostMapping("/guardar")
	public ModelAndView postGuardarNuevoEmpleador(@Validated @ModelAttribute("empleador") Empleador nuevoE, BindingResult bindingR) throws Exception {
		
		if(bindingR.hasErrors()) {
			ModelAndView modelAV = new ModelAndView("nuevo_empleador");
			modelAV.addObject("empleador", nuevoE);
			return modelAV;
		}
		else {
			ModelAndView modelAV = new ModelAndView("redirect:/empleador/lista");
			
			/*
			if(this.empleadorService.buscarEmpleador(nuevoE.getCuit()) == null) {				
				if(this.empleadorService.guardarEmpleador(nuevoE)) {
					
				}
				else {
					
				}
				return modelAV;
			}
			else {
				return modelAV;
			}
			*/
			if(this.empleadorService.guardarEmpleador(nuevoE)) {
				
			}
			else {
				
			}
			return modelAV;
		}
	}
	
	@GetMapping("/editar/{e_cuit}")
	public ModelAndView getEditarEmpleador(@PathVariable(value = "e_cuit") long e_cuit) throws Exception {
		ModelAndView modelAV = new ModelAndView("edicion_empleador");
		modelAV.addObject("empleador", this.empleadorService.buscarEmpleador(e_cuit));
		return modelAV;
	}
	
	@PostMapping("modificar")
	public ModelAndView postModificarEmpleador(@Validated @ModelAttribute("empleador") Empleador empleadorMod, BindingResult bindingR) throws Exception {
		if(bindingR.hasErrors()) {
			ModelAndView modelAV = new ModelAndView("edicion_empleador");
			modelAV.addObject("empleador", empleadorMod);
			return modelAV;
		}
		else {
			ModelAndView modelAV = new ModelAndView("redirect:/empleador/lista");
			this.empleadorService.modificarEmpleador(empleadorMod);
			return modelAV;
		}		
	}
	
	@GetMapping("/desactivarE/{e_cuit}")
	public ModelAndView getDesactivarEmpleador(@PathVariable(value = "e_cuit") long e_cuit) throws Exception {
		ModelAndView modelAV = new ModelAndView("redirect:/empleador/lista");
		this.empleadorService.modificarEstado(e_cuit);
		return modelAV;
	}
	
	@GetMapping("/vistaempleador/{cuit}")
	public ModelAndView mostrarMenuCiudadano(@PathVariable(value = "cuit") long ciut) throws Exception {
		ModelAndView mav = new ModelAndView("vista_empleador");
		mav.addObject("empleador", empleadorService.buscarEmpleador(ciut));
		return mav;
	}
	
	
	@GetMapping("/verofertas/{cuit}")
	public ModelAndView mostrarListaOfertas(@PathVariable(value = "cuit") long cuit) throws Exception {
		ModelAndView model = new ModelAndView("lista_ofertasEmpleador");
		model.addObject("ofertas", empleadorService.buscarEmpleador(cuit).getOfertas());
		model.addObject("oferta", new OfertaLaboral());
		return model;
	}
	
	@GetMapping("/verpostulantes/{id}")
	public ModelAndView mostrarListaPostulantes(@PathVariable(value = "id") long id) throws Exception {
		ModelAndView model = new ModelAndView("lista_postulantes");
		System.out.println(ofertalaboralService.getListaCandidatos(id));
		model.addObject("postulantes", ofertalaboralService.getListaCandidatos(id));
		model.addObject("id",id);
		return model;
	}
	
	@GetMapping("/aceptarpostulante/{id}/{dni}")
	public ModelAndView aceptarPostulante(@PathVariable(value="id")long id,@PathVariable(value="dni")int dni) throws Exception {
		String idc = String.valueOf(ofertalaboralService.buscarOfertaLaboral(id).getId());
		ModelAndView mav = new ModelAndView("redirect:/empleador/verpostulantes/" + idc);
		OfertaLaboral unaOferta = ofertalaboralService.buscarOfertaLaboral(id);
		ciudadanoService.agregarOfertaAceptada(dni, unaOferta);
		return mav;
	}
	
}