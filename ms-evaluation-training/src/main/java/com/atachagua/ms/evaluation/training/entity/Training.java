package com.atachagua.ms.evaluation.training.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder.Default;

@Entity
@Table(name = "training")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Training {	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTraining;
	
	@NotEmpty(message = "El grado no puede ser vacío")
    @Column(name = "degree", nullable = false)
    private String degree;
	
	@NotEmpty(message = "Las fechas no puede ser vacío")
    @Column(name = "years", nullable = false)
    private String years;
	
	@NotEmpty(message = "La institución no puede ser vacío")
    @Column(name = "institution", nullable = false)
	private String institution;	
	
	@JsonIgnore
    @Column(name = "state", length = 2, nullable = false)    
	@Default
    private String state="1";	
	
}
