package com.atachagua.ms.evaluation.training.service;

import java.util.List;
import java.util.Optional;

import com.atachagua.ms.evaluation.training.service.exception.ExceptionService;


public interface GenericService<T> {

    List<T> getAll() throws ExceptionService;

    Optional<T> findById(T t) throws ExceptionService;

    T insert(T t) throws ExceptionService;

    T update(T t) throws ExceptionService;

    T delete(T t) throws ExceptionService;

}
