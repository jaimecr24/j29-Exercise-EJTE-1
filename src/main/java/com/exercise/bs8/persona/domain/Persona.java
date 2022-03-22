package com.exercise.bs8.persona.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@Entity
public class Persona {

    @Id
    @GeneratedValue
    Integer id;

    @Size(min=6,max=10)
    @NotBlank(message = "usuario es nulo")
    String usuario;

    @NotBlank(message = "password es nulo")
    String password;

    @NotBlank(message = "nombre es nulo")
    String name;

    String surname;

    @NotBlank(message = "company_email es nulo")
    String companyEmail;

    @NotBlank(message = "personal_email es nulo")
    String personalEmail;

    @NotBlank(message = "ciudad es nulo")
    String city;

    @NotBlank(message = "active es nulo") // Clase Boolean sí puede ser nulo
    Boolean active;

    @NotBlank(message = "created_date es nulo")
    Date createdDate;

    String imagenUrl;

    Date terminationDate;
}
