package com.atachagua.ms.evaluation.profile.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.atachagua.ms.evaluation.profile.controller.commons.MessageResponse;
import com.atachagua.ms.evaluation.profile.controller.constants.Constants;
import com.atachagua.ms.evaluation.profile.entity.Person;
import com.atachagua.ms.evaluation.profile.service.PersonService;
import com.atachagua.ms.evaluation.profile.service.exception.ExceptionService;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/contacts")
@Data
@EqualsAndHashCode(callSuper=false)
public class PersonController extends GenericController {

    @Autowired
    private PersonService personService;

    @GetMapping
    public ResponseEntity<MessageResponse> getAll() {

        try {
            List<Person> persons = this.getPersonService().getAll();

            if (persons == null || persons.isEmpty()) {
                return super.getNotContentResponseEntity();
            }

            MessageResponse response = MessageResponse
                    .builder()
                    .code(Constants.CODIGO_EXITO)
                    .message(Constants.MSG_EXITO_CONS + " - " + persons.size() + " registros")
                    .data(persons)
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
            Person taller = this.getPersonService().findById(Person.builder().idPerson(id).build()).orElse(null);
            if (taller == null) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(taller);

        } catch (ExceptionService e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @GetMapping("/by-name")
    public ResponseEntity<List<Person>> findLikeNombre(@RequestParam String name) {
        try {
            List<Person> persons = this.getPersonService().findByLikeName(name);
            if (persons == null || persons.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(persons);

        } catch (ExceptionService e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @PostMapping
    public ResponseEntity<Object> insert(@RequestBody @Validated Person person, BindingResult result) {

        if (result.hasErrors()) {
            String msgErr = super.formatMapMessage(result);
            log.info("msgErr " + msgErr);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, msgErr);
        }

        try {

            Person oPerson = this.getPersonService().insert(person);

            if (oPerson == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }

            return ResponseEntity.status(HttpStatus.CREATED).body(oPerson);

        } catch (ExceptionService e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody @Valid Person person,
                                         BindingResult result) {
        person.setIdPerson(id);
        if (result.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, super.formatMapMessage(result));
        }
        try {
            Person oPerson = this.getPersonService().update(person);
            if (oPerson == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            return ResponseEntity.ok(oPerson);

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
            Person oPerson = this.getPersonService().delete(Person.builder().idPerson(id).build());
            if (oPerson == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }

            return ResponseEntity.ok(oPerson);
        } catch (ExceptionService e) {
            String argMsg[] = e.getMessage().split(":");
            MessageResponse msg = MessageResponse.builder().code(0).message(argMsg[1]).build();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(msg);
        }

    }
}
