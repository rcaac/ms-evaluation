package com.atachagua.ms.evaluation.skill.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder.Default;

@Entity
@Table(name = "skill")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Skill {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSkill;
	
	@NotEmpty(message = "El nombre no puede ser vacío")
    @Column(name="name", nullable=false)
    private String name;

    @NotEmpty(message = "El nivel no puede ser vacía")
    @Column(name="level", nullable=false)
    private Integer level;
    
    @JsonIgnore
    @Column(name = "state", length = 2, nullable = false)    
	@Default
    private String state="1";

}
