package com.atachagua.ms.evaluation.profile.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atachagua.ms.evaluation.profile.entity.Person;
import com.atachagua.ms.evaluation.profile.repository.PersonRepository;
import com.atachagua.ms.evaluation.profile.service.PersonService;
import com.atachagua.ms.evaluation.profile.service.exception.ExceptionService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Data
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public List<Person> getAll() throws ExceptionService {
        try {
            List<Person> lstPerson = this.getPersonRepository().findAllCustom();
            return lstPerson;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ExceptionService(e);
        }
    }

    @Override
    public List<Person> findByLikeName(String nombre) throws ExceptionService {
        try {

            nombre = (nombre == null) ? "" : nombre;

            return this.getPersonRepository().findByLikeName("%" + nombre.toUpperCase() + "%");

        } catch (Exception e) {
            throw new ExceptionService(e);
        }

    }

    @Override
    public Optional<Person> findById(Person person) throws ExceptionService {
        try {
            Optional<Person> optPerson = this.getPersonRepository().findById(person.getIdPerson());
            if (optPerson == null) {
                return null;
            }
            return optPerson;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ExceptionService(e);
        }
    }

    @Override
    public Person insert(Person person) throws ExceptionService {
        try {
            return this.getPersonRepository().save(person);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ExceptionService(e);
        }
    }

    @Override
    public Person update(Person person) throws ExceptionService {
        try {
            return this.getPersonRepository().save(person);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ExceptionService(e);
        }
    }

    @Override
    public Person delete(Person person) throws ExceptionService {
        try {
            Optional<Person> optPerson = this.getPersonRepository().findById(person.getIdPerson());

            if (optPerson == null) {
                return null;
            }

            Person optionalPerson = optPerson.get();
            optionalPerson.setState("0");
            return this.getPersonRepository().save(optionalPerson);

        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ExceptionService(e);
        }
    }
}
