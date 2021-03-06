package com.atachagua.ms.evaluation.training.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.atachagua.ms.evaluation.training.entity.Training;

public interface TrainingRepository extends JpaRepository<Training, Long>{
	
	@Query("select t from Training t where t.state='1'")
    List<Training> findAllCustom();

}
