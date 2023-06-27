package br.com.akaji.dojo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.com.akaji.dojo.models.AcademicEducation;
@Service
public interface AcademicEducationService extends JpaRepository<AcademicEducation, Integer>{

}
