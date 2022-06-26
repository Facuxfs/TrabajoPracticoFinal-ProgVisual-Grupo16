package ar.edu.unju.fi.edu.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.edu.entity.Empleador;
import ar.edu.unju.fi.edu.repository.IEmpleadorRepository;
import ar.edu.unju.fi.edu.service.IEmpleadorService;

@Service
public class EmpleadorServiceImp implements IEmpleadorService {

	@Autowired
	private IEmpleadorRepository empleadorRepository;
	
	@Override
	public Empleador getEmpleador() {
		// Retorna un nuevo Empleador
		return new Empleador();
	}

	@Override
	public boolean guardarEmpleador(Empleador empleador) {
		// Guarda un Empleador en la base de datos
		if(this.empleadorRepository.save(empleador) != null) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public Empleador modificarEmpleador(Empleador empleador) throws Exception {
		// El metodo "save()" busca en la base de datos el empleado a modificar y actualiza los cambios
		return this.empleadorRepository.save(empleador);
	}

	@Override
	public void modificarEstado(long e_cuit) throws Exception {
		// Desactiva el empleado para que no sea visible y asi no borrarlo de la base de datos
		Empleador empleador = buscarEmpleador(e_cuit);
		empleador.setEstado(false);
		this.empleadorRepository.save(empleador);
	}
	
	@Override
	public void eliminarEmpleador(long e_cuit){
		// Elimina un Empleador
		this.empleadorRepository.deleteByCuit(e_cuit);		
	}

	@Override
	public List<Empleador> getListaEmpleado() {
		// Devuelve la lista de Empleadores con el estado activo
		return this.empleadorRepository.findByEstado(true);
	}

	@Override
	public Empleador buscarEmpleador(long e_cuit) throws Exception {
		// Busca un Empleador de acuerdo a su numero de cuit.
		// En caso de no encontrar resultado, devuelve una excepcion.
		return this.empleadorRepository.findByCuit(e_cuit).orElseThrow(()-> new Exception("El Empleador no existe"));
	}
}