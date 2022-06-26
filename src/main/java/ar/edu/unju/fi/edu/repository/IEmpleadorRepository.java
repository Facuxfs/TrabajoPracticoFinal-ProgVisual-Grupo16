package ar.edu.unju.fi.edu.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ar.edu.unju.fi.edu.entity.Empleador;

@Repository
public interface IEmpleadorRepository extends JpaRepository<Empleador, Long> {

	public Optional<Empleador> findByCuit(long e_cuit);
	
	@Query("delete from Empleador e where e.cuit = ?1")
	public void deleteByCuit(long e_cuit);
	
	public List<Empleador> findByEstado(boolean estado);
}