package com.atachagua.ms.evaluation.experience.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.atachagua.ms.evaluation.experience.entity.Experience;

public interface ExperienceRepository extends JpaRepository<Experience, Long>{
	
	@Query("select e from Experience e where e.state='1'")
    List<Experience> findAllCustom();

}
