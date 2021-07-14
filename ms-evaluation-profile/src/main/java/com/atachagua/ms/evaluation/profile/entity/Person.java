package com.atachagua.ms.evaluation.profile.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "person")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPerson;

    @NotEmpty(message = "El nombre no debe ser vacío")
    @Size(min = 8, max = 8, message = "El tamaño del número de documento es 8")
    @Column(name = "dni", unique = true, length = 8, nullable = false)
    private String dni;

    @NotEmpty(message = "El nombre no puede ser vacío")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotEmpty(message = "El nombre no puede ser vacío")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Temporal(TemporalType.DATE)
    private Date birthday;

    private String country;

    @Column(name = "civil_status")
    private String civilStatus;

    @NotEmpty(message = "El teléfono no debe ser vacío")
    @Size(min = 6, max = 9, message = "El tamaño máximo del telefono es 9")
    private String phone;

    @NotEmpty(message = "El correo no puede estar vacío")
    @Email(message = "El correo no tiene el formato correcto")
    @Column(unique = true, nullable = false)
    private String email;

    @JsonIgnore
    @Column(name = "state", length = 2, nullable = false)    
	@Default
    private String state="1";
}
