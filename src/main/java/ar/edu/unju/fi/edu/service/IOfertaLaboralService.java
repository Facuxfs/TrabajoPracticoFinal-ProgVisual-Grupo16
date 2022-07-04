package ar.edu.unju.fi.edu.service;

import java.util.List;

import ar.edu.unju.fi.edu.entity.Ciudadano;
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
	public List<OfertaLaboral> getListaOfertaLaboralCuit(long cuit) throws Exception;
	public List<Ciudadano> getListaCandidatos(long id)throws Exception;
	public void agregarPostulante (long id, Ciudadano ciudadano) throws Exception;
	public void eliminarPostulante (long id, Ciudadano ciudadano) throws Exception;
}