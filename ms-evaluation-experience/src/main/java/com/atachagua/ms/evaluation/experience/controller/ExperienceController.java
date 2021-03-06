package com.atachagua.ms.evaluation.experience.controller;

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

import com.atachagua.ms.evaluation.experience.controller.commons.MessageResponse;
import com.atachagua.ms.evaluation.experience.controller.constants.Constants;
import com.atachagua.ms.evaluation.experience.entity.Experience;
import com.atachagua.ms.evaluation.experience.service.ExperienceService;
import com.atachagua.ms.evaluation.experience.service.exception.ExceptionService;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/experiences")
@Data
@EqualsAndHashCode(callSuper=false)
public class ExperienceController extends GenericController{
	
	@Autowired
    private ExperienceService experienceService;

    @GetMapping
    public ResponseEntity<MessageResponse> getAll() {

        try {
            List<Experience> experiences = this.getExperienceService().getAll();

            if (experiences == null || experiences.isEmpty()) {
                return super.getNotContentResponseEntity();
            }

            MessageResponse response = MessageResponse
                    .builder()
                    .code(Constants.CODIGO_EXITO)
                    .message(Constants.MSG_EXITO_CONS + " - " + experiences.size() + " registros")
                    .data(experiences)
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
            Experience experience = this.getExperienceService().findById(Experience.builder().idExperince(id).build()).orElse(null);
            if (experience == null) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(experience);

        } catch (ExceptionService e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }    

    @PostMapping
    public ResponseEntity<Object> insert(@RequestBody @Validated Experience experience, BindingResult result) {

        if (result.hasErrors()) {
            String msgErr = super.formatMapMessage(result);
            log.info("msgErr " + msgErr);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, msgErr);
        }

        try {

            Experience oExperience = this.getExperienceService().insert(experience);

            if (oExperience == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }

            return ResponseEntity.status(HttpStatus.CREATED).body(oExperience);

        } catch (ExceptionService e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody @Valid Experience experience,
                                         BindingResult result) {
        experience.setIdExperince(id);
        if (result.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, super.formatMapMessage(result));
        }
        try {
            Experience oExperience = this.getExperienceService().update(experience);
            if (oExperience == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            return ResponseEntity.ok(oExperience);

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
            Experience oExperience = this.getExperienceService().delete(Experience.builder().idExperince(id).build());
            if (oExperience == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }

            return ResponseEntity.ok(oExperience);
        } catch (ExceptionService e) {
            String argMsg[] = e.getMessage().split(":");
            MessageResponse msg = MessageResponse.builder().code(0).message(argMsg[1]).build();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(msg);
        }

    }

}
