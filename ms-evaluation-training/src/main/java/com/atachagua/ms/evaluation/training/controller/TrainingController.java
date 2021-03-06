package com.atachagua.ms.evaluation.training.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.atachagua.ms.evaluation.training.controller.commons.MessageResponse;
import com.atachagua.ms.evaluation.training.controller.constants.Constants;
import com.atachagua.ms.evaluation.training.entity.Training;
import com.atachagua.ms.evaluation.training.service.TrainingService;
import com.atachagua.ms.evaluation.training.service.exception.ExceptionService;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/training")
@Data
@EqualsAndHashCode(callSuper=false)
public class TrainingController extends GenericController{
	
	@Autowired
    private TrainingService trainingService;

    @GetMapping
    public ResponseEntity<MessageResponse> getAll() {

        try {
            List<Training> trainings = this.getTrainingService().getAll();

            if (trainings == null || trainings.isEmpty()) {
                return super.getNotContentResponseEntity();
            }

            MessageResponse response = MessageResponse
                    .builder()
                    .code(Constants.CODIGO_EXITO)
                    .message(Constants.MSG_EXITO_CONS + " - " + trainings.size() + " registros")
                    .data(trainings)
                    .build();

            return ResponseEntity.ok(response);

        } catch (ExceptionService e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        if (id <= 0) {

            String json = "{\"mensaje\":\"id debe ser positivo\"}";

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(json);
        }

        try {
            Training training = this.getTrainingService().findById(Training.builder().idTraining(id).build()).orElse(null);
            if (training == null) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(training);

        } catch (ExceptionService e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }
    

    @PostMapping
    public ResponseEntity<Object> insert(@RequestBody @Validated Training training, BindingResult result) {

        if (result.hasErrors()) {
            String msgErr = super.formatMapMessage(result);
            log.info("msgErr " + msgErr);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, msgErr);
        }

        try {

            Training oTraining = this.getTrainingService().insert(training);

            if (oTraining == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }

            return ResponseEntity.status(HttpStatus.CREATED).body(oTraining);

        } catch (ExceptionService e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody @Valid Training training,
                                         BindingResult result) {
        training.setIdTraining(id);
        if (result.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, super.formatMapMessage(result));
        }
        try {
            Training oTraining = this.getTrainingService().update(training);
            if (oTraining == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            return ResponseEntity.ok(oTraining);

        } catch (ExceptionService e) {
            log.error(e.getMessage());
            String argMsg[] = e.getMessage().split(":");
            MessageResponse msg = MessageResponse.builder().code(0).message(argMsg[1]).build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(msg);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        try {
            Training oTraining = this.getTrainingService().delete(Training.builder().idTraining(id).build());
            if (oTraining == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }

            return ResponseEntity.ok(oTraining);
        } catch (ExceptionService e) {
            String argMsg[] = e.getMessage().split(":");
            MessageResponse msg = MessageResponse.builder().code(0).message(argMsg[1]).build();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(msg);
        }

    }

}
