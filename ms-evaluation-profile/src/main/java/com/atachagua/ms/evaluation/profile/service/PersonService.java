package com.atachagua.ms.evaluation.profile.service;

import java.util.List;

import com.atachagua.ms.evaluation.profile.entity.Person;
import com.atachagua.ms.evaluation.profile.service.exception.ExceptionService;

public interface PersonService extends GenericService<Person> {

    List<Person> findByLikeName(String nombre) throws ExceptionService;

}
