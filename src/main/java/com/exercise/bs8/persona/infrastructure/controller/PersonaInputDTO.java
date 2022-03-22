package com.exercise.bs8.persona.infrastructure.controller;

import com.exercise.bs8.persona.domain.Persona;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class PersonaInputDTO {
    String usuario;
    String password;
    String name;
    String surname;
    String companyEmail;
    String personalEmail;
    String city;
    Boolean active;
    Date createdDate;
    String imagenUrl;
    Date terminationDate;

    public Persona toPersona(){
        Persona persona = new Persona();
        persona.setPassword(password);
        persona.setUsuario(usuario);
        persona.setName(name);
        persona.setSurname(surname);
        persona.setCompanyEmail(companyEmail);
        persona.setPersonalEmail(personalEmail);
        persona.setCity(city);
        persona.setActive(active);
        persona.setCreatedDate(createdDate);
        persona.setImagenUrl(imagenUrl);
        persona.setTerminationDate(terminationDate);
        return persona;
    }
}
