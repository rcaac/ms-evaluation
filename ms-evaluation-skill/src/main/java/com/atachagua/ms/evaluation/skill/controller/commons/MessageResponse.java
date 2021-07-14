package com.atachagua.ms.evaluation.skill.controller.commons;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonPropertyOrder({"code", "menssage", "date", "data"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MessageResponse {

    private Integer code;

    private String message;

    private Object data;

    @Builder.Default
    private Date date = new Date();

}
