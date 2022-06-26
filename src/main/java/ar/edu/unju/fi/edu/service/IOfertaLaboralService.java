package ar.edu.unju.fi.edu.service;

import java.util.List;

import ar.edu.unju.fi.edu.entity.Empleador;
import ar.edu.unju.fi.edu.entity.OfertaLaboral;

public interface IOfertaLaboralService {
	
	public OfertaLaboral getOfertaLaboral(Empleador contacto);
	public boolean guardarOfertaLaboral(OfertaLaboral ofertaLaboral);
	public void modificarOfertaLaboral(OfertaLaboral ofertaLaboral);
	public void modificarDisponibilidad(long ol_id) throws Exception;
	public void eliminarOfertaLaboral(long ol_id);
	public List<OfertaLaboral> getListaOfertaLaboral();
	public OfertaLaboral buscarOfertaLaboral(long ol_id) throws Exception;
}