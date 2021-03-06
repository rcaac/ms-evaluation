package com.atachagua.ms.evaluation.skill.controller;

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

import com.atachagua.ms.evaluation.skill.controller.commons.MessageResponse;
import com.atachagua.ms.evaluation.skill.controller.constants.Constants;
import com.atachagua.ms.evaluation.skill.entity.Skill;
import com.atachagua.ms.evaluation.skill.service.SkillService;
import com.atachagua.ms.evaluation.skill.service.exception.ExceptionService;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/skills")
@Data
@EqualsAndHashCode(callSuper=false)
public class SkillController extends GenericController{
	
	@Autowired
    private SkillService skillService;

    @GetMapping
    public ResponseEntity<MessageResponse> getAll() {

        try {
            List<Skill> skills = this.getSkillService().getAll();

            if (skills == null || skills.isEmpty()) {
                return super.getNotContentResponseEntity();
            }

            MessageResponse response = MessageResponse
                    .builder()
                    .code(Constants.CODIGO_EXITO)
                    .message(Constants.MSG_EXITO_CONS + " - " + skills.size() + " registros")
                    .data(skills)
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
            Skill skill = this.getSkillService().findById(Skill.builder().idSkill(id).build()).orElse(null);
            if (skill == null) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(skill);

        } catch (ExceptionService e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }    

    @PostMapping
    public ResponseEntity<Object> insert(@RequestBody @Validated Skill skill, BindingResult result) {

        if (result.hasErrors()) {
            String msgErr = super.formatMapMessage(result);
            log.info("msgErr " + msgErr);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, msgErr);
        }

        try {

            Skill oSkill = this.getSkillService().insert(skill);

            if (oSkill == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }

            return ResponseEntity.status(HttpStatus.CREATED).body(oSkill);

        } catch (ExceptionService e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody @Valid Skill skill,
                                         BindingResult result) {
        skill.setIdSkill(id);
        if (result.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, super.formatMapMessage(result));
        }
        try {
            Skill oSkill = this.getSkillService().update(skill);
            if (oSkill == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            return ResponseEntity.ok(oSkill);

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
            Skill oSkill = this.getSkillService().delete(Skill.builder().idSkill(id).build());
            if (oSkill == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }

            return ResponseEntity.ok(oSkill);
        } catch (ExceptionService e) {
            String argMsg[] = e.getMessage().split(":");
            MessageResponse msg = MessageResponse.builder().code(0).message(argMsg[1]).build();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(msg);
        }

    }

}
