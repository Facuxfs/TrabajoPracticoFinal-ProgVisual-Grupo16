package ar.edu.unju.fi.edu.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.edu.entity.Empleador;
import ar.edu.unju.fi.edu.repository.IEmpleadorRepository;
import ar.edu.unju.fi.edu.service.IEmpleadoService;

@Service
public class EmpleadorServiceImp implements IEmpleadoService {

	@Autowired
	private IEmpleadorRepository empleadorRepository;
	
	@Override
	public Empleador getEmpleador() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean guardarEmpleador(Empleador empleador) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void modificarEmpleador(Empleador empleador) {
		// TODO Auto-generated method stub

	}

	@Override
	public void eliminarEmpleador(long e_cuit) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Empleador> getListaEmpleado() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Empleador buscarEmpleador(long e_cuit) {
		// TODO Auto-generated method stub
		return null;
	}

}