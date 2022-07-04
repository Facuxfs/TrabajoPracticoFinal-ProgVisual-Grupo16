package ar.edu.unju.fi.edu.service;

import java.util.List;
import java.util.Optional;

import ar.edu.unju.fi.edu.entity.Ciudadano;
import ar.edu.unju.fi.edu.entity.OfertaLaboral;


public interface ICiudadanoService {
	
	public Ciudadano getCiudadano();
	public boolean guardarCiudadano(Ciudadano ciudadano);
	public void modificarCiudadano(Ciudadano ciudadano);
	public void eliminarCiudadano(int dni);
	public List<Ciudadano> getListaCiudadano();
	public Ciudadano buscarCiudadano(int dni);
	public void agregarOfertaAceptada(int dni, OfertaLaboral oferta) throws Exception;
	public List<Ciudadano> getListaCiudadanoProvincia(String provincia);
}
