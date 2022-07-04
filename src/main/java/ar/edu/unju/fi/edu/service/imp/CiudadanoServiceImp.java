package ar.edu.unju.fi.edu.service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.edu.entity.Ciudadano;
import ar.edu.unju.fi.edu.entity.OfertaLaboral;
import ar.edu.unju.fi.edu.repository.CiudadanoRepository;
import ar.edu.unju.fi.edu.repository.IOfertaLaboralRepository;
import ar.edu.unju.fi.edu.service.ICiudadanoService;

@Service
public class CiudadanoServiceImp implements ICiudadanoService {
	
	@Autowired
	private CiudadanoRepository ciudadanoRepository;

	@Autowired
	private IOfertaLaboralRepository ofertaRepository;
	
	
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

	@Override
	public void agregarOfertaAceptada(int dni, OfertaLaboral oferta) throws Exception {
		Ciudadano unCiudadano = this.buscarCiudadano(dni);
//		OfertaLaboral unaOferta = this.buscarOfertaLaboral(id);
		List<OfertaLaboral> unaLista = unCiudadano.getPostulaciones();
//		List<Ciudadano> unaLista = unaOferta.getPostulantes();
		oferta.setCant_vacantes(oferta.getCant_vacantes()-1);
		unaLista.add(oferta);
//		unaLista.add(ciudadano);
		unCiudadano.setPostulaciones(unaLista);
//		unaOferta.setPostulantes(unaLista);
		ofertaRepository.save(oferta);
		ciudadanoRepository.save(unCiudadano);

	}

	@Override
	public List<Ciudadano> getListaCiudadanoProvincia(String provincia) {
		// TODO Auto-generated method stub
		return ciudadanoRepository.findByProvincia(provincia);
	}
	
	
}
