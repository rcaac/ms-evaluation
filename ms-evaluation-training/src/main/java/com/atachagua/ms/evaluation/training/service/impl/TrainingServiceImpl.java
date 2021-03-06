package com.atachagua.ms.evaluation.training.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atachagua.ms.evaluation.training.entity.Training;
import com.atachagua.ms.evaluation.training.repository.TrainingRepository;
import com.atachagua.ms.evaluation.training.service.TrainingService;
import com.atachagua.ms.evaluation.training.service.exception.ExceptionService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Data
public class TrainingServiceImpl implements TrainingService{
	
	@Autowired
    private TrainingRepository trainingRepository;

    @Override
    public List<Training> getAll() throws ExceptionService {
        try {
            List<Training> lstTraining = this.getTrainingRepository().findAllCustom();
            return lstTraining;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ExceptionService(e);
        }
    }
    

    @Override
    public Optional<Training> findById(Training training) throws ExceptionService {
        try {
            Optional<Training> optTraining = this.getTrainingRepository().findById(training.getIdTraining());
            if (optTraining == null) {
                return null;
            }
            return optTraining;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ExceptionService(e);
        }
    }

    @Override
    public Training insert(Training training) throws ExceptionService {
        try {
            return this.getTrainingRepository().save(training);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ExceptionService(e);
        }
    }

    @Override
    public Training update(Training training) throws ExceptionService {
        try {
            return this.getTrainingRepository().save(training);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ExceptionService(e);
        }
    }

    @Override
    public Training delete(Training training) throws ExceptionService {
        try {
            Optional<Training> optTraining = this.getTrainingRepository().findById(training.getIdTraining());

            if (optTraining == null) {
                return null;
            }

            Training optionalTraining = optTraining.get();
            optionalTraining.setState("0");
            return this.getTrainingRepository().save(optionalTraining);

        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ExceptionService(e);
        }
    }

}
