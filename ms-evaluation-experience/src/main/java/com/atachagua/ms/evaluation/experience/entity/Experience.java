package com.atachagua.ms.evaluation.experience.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder.Default;

@Entity
@Table(name = "experience")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Experience {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idExperince;
	
	@NotEmpty(message = "La empresa no puede ser vacía")
    @Column(name="company", nullable=false)
    private String company;

    @NotEmpty(message = "El cargo no puede ser vacío")
    @Column(name="position", nullable=false)
    private String position;

    @NotEmpty(message = "La descrición no puede ser vacía")
    @Column(name="description", nullable=false)
    private String description;

    @NotEmpty(message = "La fecha de inicio no puede ser vacía")
    @Column(name = "start_date", nullable=false)
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    
    @JsonIgnore
    @Column(name = "state", length = 2, nullable = false)    
	@Default
    private String state="1";

}
