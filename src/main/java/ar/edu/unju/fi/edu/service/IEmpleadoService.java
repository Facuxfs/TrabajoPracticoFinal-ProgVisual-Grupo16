package ar.edu.unju.fi.edu.service;

import java.util.List;

import ar.edu.unju.fi.edu.entity.Empleador;

public interface IEmpleadoService {

	public Empleador getEmpleador();
	public boolean guardarEmpleador(Empleador empleador);
	public void modificarEmpleador(Empleador empleador);
	public void eliminarEmpleador(long e_cuit);
	public List<Empleador> getListaEmpleado();
	public Empleador buscarEmpleador(long e_cuit);
}