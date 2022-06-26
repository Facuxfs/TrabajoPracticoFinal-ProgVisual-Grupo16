package ar.edu.unju.fi.edu.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.edu.entity.Curriculum;
import ar.edu.unju.fi.edu.repository.CurriculumRepository;
import ar.edu.unju.fi.edu.service.ICurriculumService;

@Service
public class CurriculumServiceImp implements ICurriculumService {

	@Autowired
	private CurriculumRepository curriculumrepository;
	
	@Override
	public Curriculum getCurriculum() {
		// TODO Auto-generated method stub
		return new Curriculum();
	}

	@Override
	public boolean guardarCurriculum(Curriculum curriculum) {
		curriculum.setEstado(true);
		
		if(curriculumrepository.save(curriculum)!=null) {
			return true;
		}
		return false;
	}

	@Override
	public void modificarCurriculum(Curriculum curriculum) {
		curriculumrepository.save(curriculum);
		

	}



	@Override
	public void eliminarCurriculum(Long id) {
		// TODO Auto-generated method stub
		Curriculum curri = buscarCurriculum(id);
		curri.setEstado(false);
		curriculumrepository.save(curri);
	}

	@Override
	public  Curriculum buscarCurriculum(Long id) {
	
		return null;
	}

}
