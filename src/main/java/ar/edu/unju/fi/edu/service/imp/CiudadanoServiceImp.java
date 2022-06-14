package ar.edu.unju.fi.edu.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.unju.fi.edu.entity.Ciudadano;
import ar.edu.unju.fi.edu.repository.CiudadanoRepository;
import ar.edu.unju.fi.edu.service.ICiudadanoService;


public class CiudadanoServiceImp implements ICiudadanoService {
	
	@Autowired
	private CiudadanoRepository ciudadanoRepository;

	@Override
	public Ciudadano getCiudadano() {
		// TODO Auto-generated method stub
		return new Ciudadano();
	}

	@Override
	public boolean guardarCiudadano(Ciudadano ciudadano) {
		ciudadano.setEstado(true);
		if(ciudadanoRepository.save(ciudadano)!=null) {
			return true;
		}
		return false;
	}

	@Override
	public void modificarCiudadano(Ciudadano ciudadano) {
		ciudadanoRepository.save(ciudadano);
		
	}

	@Override
	public void eliminarCiudadano(int dni) {
		//docenteRepository.deleteByLegajo(legajo);
		Ciudadano ciudadano = buscarCiudadano(dni);
		ciudadano.setEstado(false);
		ciudadanoRepository.save(ciudadano);
	}

	@Override
	public List<Ciudadano> getListaCiudadano() {
		return ciudadanoRepository.findByEstado(true);
	}

	@Override
	public Ciudadano buscarCiudadano(int dni) {
		
		return ciudadanoRepository.findByDni(dni);
	}

}
