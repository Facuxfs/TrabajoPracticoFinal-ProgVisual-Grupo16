package ar.edu.unju.fi.edu.service;

import ar.edu.unju.fi.edu.entity.Curriculum;

public interface ICurriculumService {
	
	public Curriculum getCurriculum();
	public boolean guardarCurriculum(Curriculum curriculum);
	public void modificarCurriculum(Curriculum curriculum);
	public void eliminarCurriculum(Long id);
	public Curriculum buscarCurriculum(Long id);

}
