package com.atachagua.ms.evaluation.skill.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.atachagua.ms.evaluation.skill.entity.Skill;

public interface SkillRepository extends JpaRepository<Skill, Long>{
	
	@Query("select s from Skill s where s.state='1'")
    List<Skill> findAllCustom();

}
