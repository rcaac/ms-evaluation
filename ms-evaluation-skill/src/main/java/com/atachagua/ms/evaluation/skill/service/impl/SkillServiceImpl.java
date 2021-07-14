package com.atachagua.ms.evaluation.skill.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.atachagua.ms.evaluation.skill.entity.Skill;
import com.atachagua.ms.evaluation.skill.repository.SkillRepository;
import com.atachagua.ms.evaluation.skill.service.SkillService;
import com.atachagua.ms.evaluation.skill.service.exception.ExceptionService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Data
public class SkillServiceImpl implements SkillService{
	
	@Autowired
    private SkillRepository skillRepository;

    @Override
    public List<Skill> getAll() throws ExceptionService {
        try {
            List<Skill> lstSkill = this.getSkillRepository().findAllCustom();
            return lstSkill;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ExceptionService(e);
        }
    }    

    @Override
    public Optional<Skill> findById(Skill skill) throws ExceptionService {
        try {
            Optional<Skill> optSkill = this.getSkillRepository().findById(skill.getIdSkill());
            if (optSkill == null) {
                return null;
            }
            return optSkill;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ExceptionService(e);
        }
    }

    @Override
    public Skill insert(Skill skill) throws ExceptionService {
        try {
            return this.getSkillRepository().save(skill);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ExceptionService(e);
        }
    }

    @Override
    public Skill update(Skill skill) throws ExceptionService {
        try {
            return this.getSkillRepository().save(skill);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ExceptionService(e);
        }
    }

    @Override
    public Skill delete(Skill skill) throws ExceptionService {
        try {
            Optional<Skill> optSkill = this.getSkillRepository().findById(skill.getIdSkill());

            if (optSkill == null) {
                return null;
            }

            Skill optionalSkill = optSkill.get();
            optionalSkill.setState("0");
            return this.getSkillRepository().save(optionalSkill);

        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ExceptionService(e);
        }
    }

}
