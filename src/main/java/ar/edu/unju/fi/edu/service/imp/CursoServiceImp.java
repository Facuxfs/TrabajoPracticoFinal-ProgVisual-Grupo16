package ar.edu.unju.fi.edu.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.edu.entity.Curso;
import ar.edu.unju.fi.edu.entity.Empleador;
import ar.edu.unju.fi.edu.repository.ICursoRepository;
import ar.edu.unju.fi.edu.service.ICursoService;

@Service
public class CursoServiceImp implements ICursoService {

	@Autowired
	private ICursoRepository cursoRepository;
	
	@Override
	public Curso getCurso(Empleador profesor) {
		// Retorna un nuevo Curso
		return new Curso(profesor);
	}

	@Override
	public boolean guardarCurso(Curso curso) {
		// Guarda un Curso en la base de datos
		if(this.cursoRepository.save(curso) != null) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public void modificarCurso(Curso curso) {
		// El metodo "save()" busca en la base de datos el Curso a modificar y actualiza los cambios
		this.cursoRepository.save(curso);
	}

	@Override
	public void modificarEstado(long c_codigo) throws Exception {
		// Desactiva el Curso para que no sea visible y asi no borrarlo de la base de datos
		Curso curso = buscarCurso(c_codigo);
		curso.setEstado(false);
		this.cursoRepository.save(curso);
	}

	@Override
	public void eliminarCurso(long c_codigo) {
		// Elimina un Curso
		this.cursoRepository.deleteByCodigo(c_codigo);
	}

	@Override
	public List<Curso> getListaCurso() {
		// Devuelve la lista de Cursos con estado activo
		return this.cursoRepository.findByEstado(true);
	}

	@Override
	public Curso buscarCurso(long c_codigo) throws Exception {
		// Busca un Curso de acuerdo a su codigo.
		// En cas de no encontrar resultado, devuelve una excepcion.
		return this.cursoRepository.findByCodigo(c_codigo).orElseThrow(()-> new Exception("El Curso no existe"));
	}
}