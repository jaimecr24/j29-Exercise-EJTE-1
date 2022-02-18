package com.exercise.bs8.persona.domain;

import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@Entity
public class Persona {

    @Id
    @GeneratedValue
    Integer id;

    @Size(min=6,max=10)
    @Column(nullable = false)
    String usuario;

    @Column(nullable = false)
    String password;

    @Column(nullable = false)
    String name;

    String surname;

    @Column(nullable = false)
    String company_email;

    @Column(nullable = false)
    String personal_email;

    @Column(nullable = false)
    String city;

    @Column(nullable = false) // Clase Boolean sí puede ser nulo
    Boolean active;

    @Column(nullable = false)
    Date created_date;

    String imagen_url;

    Date termination_date;
}
