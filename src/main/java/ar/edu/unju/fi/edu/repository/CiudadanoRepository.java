package ar.edu.unju.fi.edu.repository;

import java.util.List;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.fi.edu.entity.Ciudadano;

@Repository
public interface CiudadanoRepository extends JpaRepository<Ciudadano, Long> {
	
	public Ciudadano  findByDni (int dni);
	
	public List<Ciudadano> findByEstado(boolean estado);
	
	

}
