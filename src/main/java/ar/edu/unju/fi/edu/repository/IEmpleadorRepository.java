package ar.edu.unju.fi.edu.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.fi.edu.entity.Empleador;

@Repository
public interface IEmpleadorRepository extends JpaRepository<Empleador, Long> {

	public Optional<Empleador> findByCuit(long e_cuit);
	
	
}