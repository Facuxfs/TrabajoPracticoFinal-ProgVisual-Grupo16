package ar.edu.unju.fi.edu.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.edu.entity.OfertaLaboral;
import ar.edu.unju.fi.edu.repository.IOfertaLaboralRepository;
import ar.edu.unju.fi.edu.service.IOfertaLaboralService;

@Service
public class OfertaLaboralServiceImp implements IOfertaLaboralService {

	@Autowired
	private IOfertaLaboralRepository olRepository;
	
	@Override
	public OfertaLaboral getOfertaLaboral() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean guardarOfertaLaboral(OfertaLaboral ofertaLaboral) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void modificarOfertaLaboral(OfertaLaboral ofertaLaboral) {
		// TODO Auto-generated method stub

	}

	@Override
	public void eliminarOfertaLaboral(long ol_id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<OfertaLaboral> getListaOfertaLaboral() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OfertaLaboral buscarOfertaLaboral(long ol_id) {
		// TODO Auto-generated method stub
		return null;
	}

}