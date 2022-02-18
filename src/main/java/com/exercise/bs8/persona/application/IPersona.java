package com.exercise.bs8.persona.application;

import com.exercise.bs8.persona.infrastructure.controller.PersonaInputDTO;
import com.exercise.bs8.persona.infrastructure.controller.PersonaOutputDTO;

import java.util.List;

public interface IPersona {
    List<PersonaOutputDTO> findAll();
    List<PersonaOutputDTO> getByUser(String usuario);
    PersonaOutputDTO getById(Integer id) throws Exception;
    PersonaOutputDTO addPersona(PersonaInputDTO personaInputDTO) throws Exception;
    PersonaOutputDTO putPersona(Integer id, PersonaInputDTO personaInputDTO) throws Exception;
    PersonaOutputDTO delPersona(Integer id) throws Exception;
}
