package ar.edu.unju.fi.edu.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.fi.edu.entity.OfertaLaboral;

@Repository
public interface IOfertaLaboralRepository extends JpaRepository<OfertaLaboral, Long>{
	
	public Optional<OfertaLaboral> findById(long ol_id);
	
}