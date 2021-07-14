package com.atachagua.ms.evaluation.profile.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import com.atachagua.ms.evaluation.profile.controller.commons.MessageResponse;
import com.atachagua.ms.evaluation.profile.controller.constants.Constants;

public class GenericController {

    protected String formatMapMessage(BindingResult result) {
        List<Map<String, String>> errors = result.getFieldErrors().stream()
                .map(err -> {
                    Map<String, String> error = new HashMap<>();
                    error.put(err.getField(), err.getDefaultMessage());
                    return error;
                }).collect(Collectors.toList());
        return errors.toString();

    }

    protected MessageResponse getNotContent() {

        return MessageResponse
                .builder()
                .code(Constants.CODIGO_ALERTA)
                .message(Constants.MSG_ALERTA_CONS)
                .data(null)
                .build();
    }

    protected ResponseEntity<MessageResponse> getNotContentResponseEntity() {

        return ResponseEntity.status(HttpStatus.OK).body(this.getNotContent());

    }
}
