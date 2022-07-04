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
import ar.edu.unju.fi.edu.service.ICursoService;
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
	
	@Autowired
	private ICursoService cursoService;
	
	private static final Log LOGGER = LogFactory.getLog(EmpleadorController.class);
	/*
	 * metodo que retorna una lista de los objetos tipo empleador guardados en la bd
	 */
	@GetMapping("/lista")
	public String getEmpleadoresPage(Model model) {
		model.addAttribute("empleadores", this.empleadorService.getListaEmpleado());
		return "lista_empleadores";
	}
	/*
	 * metodo que permite generear un nuevo objeto de tipo empleador y devuelve el formulario de registro del mismo
	 */
	@GetMapping("/nuevo")
	public String getNuevoEmpleador(Model model) {
		model.addAttribute("empleador", this.empleadorService.getEmpleador());
		return "nuevo_empleador";
	}
	/*
	 * metodo que permite guardar un objeto de tipo empleador en la bd
	 */
	@PostMapping("/guardar")
	public ModelAndView postGuardarNuevoEmpleador(@Validated @ModelAttribute("empleador") Empleador nuevoE, BindingResult bindingR) throws Exception {
		
		if(bindingR.hasErrors()) {
			ModelAndView modelAV = new ModelAndView("nuevo_empleador");
			modelAV.addObject("empleador", nuevoE);
			return modelAV;
		}
		else {
			String cuit = String.valueOf(nuevoE.getCuit());
			ModelAndView modelAV = new ModelAndView("redirect:/empleador/vistaempleador/" + cuit);
			
			if(this.empleadorService.guardarEmpleador(nuevoE)) {
				
			}
			else {
				
			}
			return modelAV;
		}
	}
	/*
	 * metodo que permite editas los atributos de un objeto de tipo empleador
	 */
	@GetMapping("/editar/{e_cuit}")
	public ModelAndView getEditarEmpleador(@PathVariable(value = "e_cuit") long e_cuit) throws Exception {
		ModelAndView modelAV = new ModelAndView("edicion_empleador");
		modelAV.addObject("empleador", this.empleadorService.buscarEmpleador(e_cuit));
		return modelAV;
	}
	/*
	 * metodo que guara en la bd los cambios realizados en el objeto de tipo empleador
	 */
	@PostMapping("modificar")
	public ModelAndView postModificarEmpleador(@Validated @ModelAttribute("empleador") Empleador empleadorMod, BindingResult bindingR) throws Exception {
		if(bindingR.hasErrors()) {
			ModelAndView modelAV = new ModelAndView("edicion_empleador");
			modelAV.addObject("empleador", empleadorMod);
			return modelAV;
		}
		else {
			String cuit = String.valueOf(empleadorMod.getCuit());
			ModelAndView modelAV = new ModelAndView("redirect:/empleador/vistaempleador/" + cuit);
			this.empleadorService.modificarEmpleador(empleadorMod);
			return modelAV;
		}		
	}
	/*
	 * metodo que permite elimiar logicamente un objeto de tipo empleador
	 */
	@GetMapping("/desactivarE/{e_cuit}")
	public ModelAndView getDesactivarEmpleador(@PathVariable(value = "e_cuit") long e_cuit) throws Exception {
		ModelAndView modelAV = new ModelAndView("redirect:/empleador/lista");
		this.empleadorService.modificarEstado(e_cuit);
		return modelAV;
	}
	/*
	 * metodo que devuleve la vista principal de un objeto de tipo empleador
	 */
	@GetMapping("/vistaempleador/{cuit}")
	public ModelAndView mostrarMenuCiudadano(@PathVariable(value = "cuit") long ciut) throws Exception {
		ModelAndView mav = new ModelAndView("vista_empleador");
		mav.addObject("empleador", empleadorService.buscarEmpleador(ciut));
		return mav;
	}
	
	/*
	 * metodo que permite ver las ofertas creadas por un objeto  de tipo empleador
	 */
	@GetMapping("/verofertas/{cuit}")
	public ModelAndView mostrarListaOfertas(@PathVariable(value = "cuit") long cuit) throws Exception {
		ModelAndView model = new ModelAndView("lista_ofertasEmpleador");
		model.addObject("ofertas", empleadorService.buscarEmpleador(cuit).getOfertas());
		model.addObject("cuit", cuit);
		return model;
	}
	/*
	 * metodo que permite ver los objetos de tipo ciudadano asociados a una oferta ed un objeto de tipo empleador
	 */
	@GetMapping("/verpostulantes/{id}/{cuit}")
	public ModelAndView mostrarListaPostulantes(@PathVariable(value = "id") long id, @PathVariable(value = "cuit") long cuit) throws Exception {
		ModelAndView model = new ModelAndView("lista_postulantes");
		System.out.println(ofertalaboralService.getListaCandidatos(id));
		model.addObject("postulantes", ofertalaboralService.getListaCandidatos(id));
		model.addObject("id",id);
		model.addObject("cuit",cuit);
		model.addObject("ciudadano",new Ciudadano());
		return model;
	}
	/*
	 * metodo que permite filtrar los objetos de tipo ciudadano asociados a una oferta de un empleador por su atributo provincia
	 */
	@PostMapping("/verpostulantesprovincia/{id}/{cuit}")
	public ModelAndView verPostulantesProvincia(@ModelAttribute ("ciudadano")Ciudadano ciudadano,@PathVariable(value = "id") long id, @PathVariable(value = "cuit") long cuit) throws Exception {
		ModelAndView model = new ModelAndView("lista_postulantes");
		System.out.println(ofertalaboralService.getListaCandidatos(id));
		model.addObject("postulantes", ciudadanoService.getListaCiudadanoProvincia(ciudadano.getProvincia()));
		model.addObject("id",id);
		model.addObject("cuit",cuit);
		return model;
	}
/*
 * metodo que permite agregar en la bd las relacion ciudadano-ofertalaboral de un empleado
 */
	@GetMapping("/aceptarpostulante/{id}/{dni}")
	public ModelAndView aceptarPostulante(@PathVariable(value="id")long id,@PathVariable(value="dni")int dni) throws Exception {
		String idc = String.valueOf(ofertalaboralService.buscarOfertaLaboral(id).getId());
		String cuitc = String.valueOf(ofertalaboralService.buscarOfertaLaboral(id).getContacto().getCuit());
		ModelAndView mav = new ModelAndView("redirect:/empleador/verpostulantes/" + idc +"/"+ cuitc);
		OfertaLaboral unaOferta = ofertalaboralService.buscarOfertaLaboral(id);
		ciudadanoService.agregarOfertaAceptada(dni, unaOferta);
		return mav;
	}
	/*
	 * metodo que elimina la postulacion de un obj tipo ciudadano de una oferta laboral
	 */
	@GetMapping("/rechazarpostulante/{id}/{dni}")
	public ModelAndView rechazarPostulante(@PathVariable(value="id")long id,@PathVariable(value="dni")int dni) throws Exception {
		String idc = String.valueOf(ofertalaboralService.buscarOfertaLaboral(id).getId());
		String cuitc = String.valueOf(ofertalaboralService.buscarOfertaLaboral(id).getContacto().getCuit());
		ModelAndView mav = new ModelAndView("redirect:/empleador/verpostulantes/" + idc +"/"+ cuitc);
		ofertalaboralService.eliminarPostulante(id, ciudadanoService.buscarCiudadano(dni));
	
		return mav;
	}
	/*
	 * metodo que devuelve los cursos creados por un empleador
	 */
	
	@GetMapping("/verocursoss/{cuit}")
	public ModelAndView mostrarListCursos(@PathVariable(value = "cuit") long cuit) throws Exception {
		ModelAndView model = new ModelAndView("lista_cursosEmpleador");
		model.addObject("cursos", empleadorService.buscarEmpleador(cuit).getCursos());
		model.addObject("cuit", cuit);
		return model;
	}
/*
 * metodo que devuelve los objetos tipop ciudadno que se anotaron al curso 
 */
	@GetMapping("/verinscriptos/{c_codigo}")
	public ModelAndView mostrarListaInscriptos(@PathVariable(value = "c_codigo") long codigo) throws Exception {
		ModelAndView model = new ModelAndView("lista_inscriptos");
		model.addObject("postulantes", cursoService.buscarCurso(codigo).getInscriptos());
		model.addObject("id",codigo);
		model.addObject("cuit", cursoService.buscarCurso(codigo).getProfesor().getCuit());
		return model;
	}
	
}