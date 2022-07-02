package ar.edu.unju.fi.edu.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.edu.entity.Ciudadano;
import ar.edu.unju.fi.edu.entity.Empleador;
import ar.edu.unju.fi.edu.entity.OfertaLaboral;
import ar.edu.unju.fi.edu.repository.IOfertaLaboralRepository;
import ar.edu.unju.fi.edu.service.IOfertaLaboralService;

@Service
public class OfertaLaboralServiceImp implements IOfertaLaboralService {

	@Autowired
	private IOfertaLaboralRepository olRepository;
	
	@Override
	public OfertaLaboral getOfertaLaboral(Empleador contacto) {
		// Retorna una OfertaLaboral
		return new OfertaLaboral(contacto);
	}

	@Override
	public boolean guardarOfertaLaboral(OfertaLaboral ofertaLaboral) {
		// Guarda una OfertaLaboral en la base de datos
		if(this.olRepository.save(ofertaLaboral) != null) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public void modificarOfertaLaboral(OfertaLaboral ofertaLaboral) {
		// El metodo "save()" busca en la base de datos la OfertaLaboral a modificar y actualiza los cambios
		this.olRepository.save(ofertaLaboral);
	}

	@Override
	public void modificarDisponibilidad(long ol_id) throws Exception {
		// Desactiva la disponibilidad de una OfertaLaboral
		OfertaLaboral ol = buscarOfertaLaboral(ol_id);
		ol.setDisponible(false);
		this.olRepository.save(ol);
	}
	
	@Override
	public void eliminarOfertaLaboral(long ol_id) {
		// Elimina una OfertaLaboral de la base de datos
		this.olRepository.deleteById(ol_id);

	}

	@Override
	public List<OfertaLaboral> getListaOfertaLaboral() {
		// Devuelve la lista de OfertasLaborales disponibles
		return this.olRepository.findByDisponible(true);
	}

	@Override
	public OfertaLaboral buscarOfertaLaboral(long ol_id) throws Exception {
		// Busca una OfertaLaboral de acuerdo a su id.
		// En caso de no encontrar resultado, devuelve una escepcion.
		return this.olRepository.findById(ol_id).orElseThrow(()-> new Exception("La OfertaLaboral no existe"));
	}

	@Override
	public List<OfertaLaboral> getListaOfertaLaboralCuit(long cuit) throws Exception {
		
		return this.olRepository.findByContacto(cuit);
	}

	@Override
	public List<Ciudadano> getListaCandidatos(long id) throws Exception {
		return olRepository.findById(id).get().getPostulantes();
	}

	@Override
	public void agregarPostulante(long id, Ciudadano ciudadano) throws Exception {
		OfertaLaboral unaOferta = this.buscarOfertaLaboral(id);
		List<Ciudadano> unaLista = unaOferta.getPostulantes();
		unaLista.add(ciudadano);
		unaOferta.setPostulantes(unaLista);
		olRepository.save(unaOferta);
	}
}