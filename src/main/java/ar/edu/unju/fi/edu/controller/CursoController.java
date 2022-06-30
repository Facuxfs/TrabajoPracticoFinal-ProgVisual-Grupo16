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

import ar.edu.unju.fi.edu.entity.Curso;
import ar.edu.unju.fi.edu.service.imp.CursoServiceImp;
import ar.edu.unju.fi.edu.service.imp.EmpleadorServiceImp;

@Controller
@RequestMapping("/curso")
public class CursoController {
	
	@Autowired
	private CursoServiceImp cursoService;
	
	@Autowired
	private EmpleadorServiceImp empleadorService;
	
	@GetMapping("/lista")
	public String getCursoPage(Model model) {
		model.addAttribute("cursos", this.cursoService.getListaCurso());
		return "lista_cursos";
	}
	
	@GetMapping("/nuevo/{e_cuit}")
	public String getNuevoCurso(@PathVariable(value = "e_cuit") long e_cuit, Model model) throws Exception {
		model.addAttribute("curso", this.cursoService.getCurso(this.empleadorService.buscarEmpleador(e_cuit)));
		return "nuevo_curso";
	}
	
	@PostMapping("/guardar")
	public ModelAndView postGuardarNuevoCurso(@Validated @ModelAttribute("curso") Curso nuevoCurso, BindingResult bindingR) throws Exception {
		if(bindingR.hasErrors()) {
			ModelAndView modelAV = new ModelAndView("nuevo_curso");
			modelAV.addObject("curso", nuevoCurso);
			return modelAV;
		}
		else {
			ModelAndView modelAV = new ModelAndView("redirect:/curso/lista");
			if(this.cursoService.guardarCurso(nuevoCurso)) {
				
			}
			else {
				
			}
			return modelAV;
		}
	}
	
	@GetMapping("/editar/{c_codigo}")
	public ModelAndView getEditarCurso(@PathVariable(value = "c_codigo") long c_codigo) throws Exception {
		ModelAndView modelAV = new ModelAndView("edicion_curso");
		modelAV.addObject("curso", this.cursoService.buscarCurso(c_codigo));
		return modelAV;
	}
	
	@PostMapping("/modificar")
	public ModelAndView postModificarCurso(@Validated @ModelAttribute("curso") Curso cursoMod, BindingResult bindingR) throws Exception {
		if(bindingR.hasErrors()) {
			ModelAndView modelAV = new ModelAndView("nuevo_curso");
			modelAV.addObject("curso", cursoMod);
			return modelAV;
		}
		else {
			ModelAndView modelAV = new ModelAndView("redirect:/curso/lista");
			this.cursoService.modificarCurso(cursoMod);
			return modelAV;
		}
	}
	
	@GetMapping("/desactivar/{c_codigo}")
	public ModelAndView getDesactivarCurso(@PathVariable(value = "c_codigo") long c_codigo) throws Exception {
		ModelAndView modelAV = new ModelAndView("redirect:/curso/lista");
		this.cursoService.modificarEstado(c_codigo);
		return modelAV;
	}
}