package ar.edu.unju.fi.edu.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.fi.edu.entity.Curriculum;


@Repository
public interface CurriculumRepository extends JpaRepository<Curriculum, Long>{
	
	public Optional<Curriculum> findById (Long id);
}
