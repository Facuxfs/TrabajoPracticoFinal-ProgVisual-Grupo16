package ar.edu.unju.fi.edu.service;

import java.util.List;

import ar.edu.unju.fi.edu.entity.Ciudadano;
import ar.edu.unju.fi.edu.entity.Curso;
import ar.edu.unju.fi.edu.entity.Empleador;

public interface ICursoService {

	public Curso getCurso(Empleador profesor);
	public boolean guardarCurso(Curso curso);
	public void modificarCurso(Curso curso);
	public void modificarEstado(long c_codigo) throws Exception;
	public void eliminarCurso(long c_codigo);
	public List<Curso> getListaCurso();
	public Curso buscarCurso(long c_codigo) throws Exception;
	public void agregarInscripcion (long c_codigo, Ciudadano ciudadano) throws Exception;
}