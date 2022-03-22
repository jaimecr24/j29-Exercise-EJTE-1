package com.exercise.bs8.persona.infrastructure.controller;

import com.exercise.bs8.persona.domain.Persona;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PersonaOutputDTO {
    Integer id;
    String usuario;
    String name;
    String surname;
    String companyEmail;
    String personalEmail;
    String city;
    Boolean active;
    Date createdDate;
    String imagenUrl;
    Date terminationDate;

    public PersonaOutputDTO(Persona persona){
        this.id = persona.getId();
        this.usuario = persona.getUsuario();
        this.name = persona.getName();
        this.surname = persona.getSurname();
        this.companyEmail = persona.getCompanyEmail();
        this.personalEmail = persona.getPersonalEmail();
        this.city = persona.getCity();
        this.active = persona.getActive();
        this.createdDate = persona.getCreatedDate();
        this.imagenUrl = persona.getImagenUrl();
        this.terminationDate = persona.getTerminationDate();
    }
}
