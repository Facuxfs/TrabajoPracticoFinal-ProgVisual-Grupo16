package ar.edu.unju.fi.edu.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ar.edu.unju.fi.edu.entity.Curso;

@Repository
public interface ICursoRepository extends JpaRepository<Curso, Long> {
	
	public Optional<Curso> findByCodigo(long c_codigo);
	
	@Query("delete from Curso c where c.codigo = ?1")
	public void deleteByCodigo(long c_codigo);
	
	public List<Curso> findByEstado(boolean estado);
	
	public List<Curso> findByCategoria (String categoria);
}