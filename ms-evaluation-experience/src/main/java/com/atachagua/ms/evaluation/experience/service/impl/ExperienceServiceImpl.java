package com.atachagua.ms.evaluation.experience.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atachagua.ms.evaluation.experience.entity.Experience;
import com.atachagua.ms.evaluation.experience.repository.ExperienceRepository;
import com.atachagua.ms.evaluation.experience.service.ExperienceService;
import com.atachagua.ms.evaluation.experience.service.exception.ExceptionService;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Data
public class ExperienceServiceImpl implements ExperienceService{
	
	
	@Autowired
    private ExperienceRepository experienceRepository;

    @Override
    public List<Experience> getAll() throws ExceptionService {
        try {
            List<Experience> lstExperience = this.getExperienceRepository().findAllCustom();
            return lstExperience;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ExceptionService(e);
        }
    }
    

    @Override
    public Optional<Experience> findById(Experience Experience) throws ExceptionService {
        try {
            Optional<Experience> optExperience = this.getExperienceRepository().findById(Experience.getIdExperince());
            if (optExperience == null) {
                return null;
            }
            return optExperience;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ExceptionService(e);
        }
    }

    @Override
    public Experience insert(Experience experience) throws ExceptionService {
        try {
            return this.getExperienceRepository().save(experience);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ExceptionService(e);
        }
    }

    @Override
    public Experience update(Experience experience) throws ExceptionService {
        try {
            return this.getExperienceRepository().save(experience);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ExceptionService(e);
        }
    }

    @Override
    public Experience delete(Experience experience) throws ExceptionService {
        try {
            Optional<Experience> optExperience = this.getExperienceRepository().findById(experience.getIdExperince());

            if (optExperience == null) {
                return null;
            }

            Experience optionalExperience = optExperience.get();
            optionalExperience.setState("0");
            return this.getExperienceRepository().save(optionalExperience);

        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ExceptionService(e);
        }
    }

}
