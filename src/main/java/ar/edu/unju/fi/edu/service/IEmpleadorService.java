package ar.edu.unju.fi.edu.service;

import java.util.List;

import ar.edu.unju.fi.edu.entity.Empleador;

public interface IEmpleadorService {

	public Empleador getEmpleador();
	public boolean guardarEmpleador(Empleador empleador);
	public void modificarEmpleador(Empleador empleador);
	public void modificarEstado(long e_cuit) throws Exception;
	public void eliminarEmpleador(long e_cuit);
	public List<Empleador> getListaEmpleado();
	public Empleador buscarEmpleador(long e_cuit) throws Exception;
	public List<Empleador> getListaEmpleadoresProvincia(String provincia);
}