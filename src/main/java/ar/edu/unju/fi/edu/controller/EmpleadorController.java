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

import ar.edu.unju.fi.edu.entity.Empleador;
import ar.edu.unju.fi.edu.service.IEmpleadorService;

@Controller
@RequestMapping("/empleador")
public class EmpleadorController {
	
	@Autowired
	private IEmpleadorService empleadorService;
	
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
	public ModelAndView getEditarEmpleador(@PathVariable(value="e_cuit") long e_cuit) throws Exception {
		ModelAndView modelAV = new ModelAndView("edicion_empleador");
		Empleador empleador = this.empleadorService.buscarEmpleador(e_cuit);
		modelAV.addObject("empleador", empleador);
		return modelAV;
	}
	
	@PostMapping("modificar")
	public ModelAndView postEditarEmpleador(@Validated @ModelAttribute("empleador") Empleador empleadorMod, BindingResult bindingR) throws Exception {
		if(bindingR.hasErrors()) {
			ModelAndView modelAV = new ModelAndView("edicion_empleador");
			modelAV.addObject("empleador", empleadorMod);
			return modelAV;
		}
		
		ModelAndView modelAV = new ModelAndView("redirect:/empleador/lista");
		this.empleadorService.modificarEmpleador(empleadorMod);
		return modelAV;
	}
	
	@GetMapping("/eliminar/{e_cuit}")
	public ModelAndView getEliminarEmpleador(@PathVariable(value = "e_cuit") long e_cuit) throws Exception {
		ModelAndView modelAV = new ModelAndView("redirect:/empleador/lista");
		this.empleadorService.modificarEstado(e_cuit);
		return modelAV;
	}
}