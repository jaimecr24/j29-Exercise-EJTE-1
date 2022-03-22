package com.exercise.bs8.persona.application;

import com.exercise.bs8.persona.domain.Persona;
import com.exercise.bs8.persona.infrastructure.controller.PersonaInputDTO;
import com.exercise.bs8.persona.infrastructure.controller.PersonaOutputDTO;
import com.exercise.bs8.persona.infrastructure.repository.PersonaRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonaService implements IPersona {

    private final PersonaRepo personaRepo;

    public PersonaService(PersonaRepo personaRepo){
        super();
        this.personaRepo = personaRepo;
    }

    @Override
    public List<PersonaOutputDTO> findAll() {
        List<Persona> listaPersona = personaRepo.findAll();
        List<PersonaOutputDTO> listaPersonaDTO = new ArrayList<>();
        for (Persona persona:listaPersona) listaPersonaDTO.add(new PersonaOutputDTO(persona));
        return listaPersonaDTO;
    }

    @Override
    public List<PersonaOutputDTO> getByUser(String usuario) {
        List<Persona> listaPersona = personaRepo.findByUsuario(usuario);
        List<PersonaOutputDTO> listaPersonaDTO = new ArrayList<>();
        for (Persona persona:listaPersona) listaPersonaDTO.add(new PersonaOutputDTO(persona));
        return listaPersonaDTO;
    }

    @Override
    public PersonaOutputDTO getById(Integer id) throws NotFoundException {
        Persona persona = personaRepo.findById(id).orElseThrow(()->new NotFoundException("id "+id+" not found."));
        return new PersonaOutputDTO(persona);
    }

    @Override
    public PersonaOutputDTO addPersona(PersonaInputDTO personaInputDTO) throws UnprocesableException {
        this.validate(personaInputDTO);
        Persona persona = personaInputDTO.toPersona();
        personaRepo.save(persona);
        return new PersonaOutputDTO(persona);
    }

    @Override
    public PersonaOutputDTO putPersona(Integer id, PersonaInputDTO personaInputDTO) throws NotFoundException,UnprocesableException {
        this.validate(personaInputDTO);
        Persona persona = personaRepo.findById(id).orElseThrow(()->new NotFoundException("id "+id+" not found."));
        persona.setPassword(personaInputDTO.getPassword());
        persona.setUsuario(personaInputDTO.getUsuario());
        persona.setName(personaInputDTO.getName());
        persona.setUsuario(personaInputDTO.getUsuario());
        persona.setName(personaInputDTO.getName());
        persona.setSurname(personaInputDTO.getSurname());
        persona.setCompanyEmail(personaInputDTO.getCompanyEmail());
        persona.setPersonalEmail(personaInputDTO.getPersonalEmail());
        persona.setCity(personaInputDTO.getCity());
        persona.setActive(personaInputDTO.getActive());
        persona.setCreatedDate(personaInputDTO.getCreatedDate());
        persona.setImagenUrl(personaInputDTO.getImagenUrl());
        persona.setTerminationDate(personaInputDTO.getTerminationDate());
        personaRepo.save(persona);
        return new PersonaOutputDTO(persona);
    }

    @Override
    public PersonaOutputDTO delPersona(Integer id) throws NotFoundException {
        Persona persona = personaRepo.findById(id).orElseThrow(()->new NotFoundException("id "+id+" not found."));
        PersonaOutputDTO personaOutputDTO = new PersonaOutputDTO(persona);
        personaRepo.delete(persona);
        return personaOutputDTO;
    }

    private void validate(PersonaInputDTO personaInputDTO) throws UnprocesableException{
        String usuario = personaInputDTO.getUsuario();
        if (usuario==null) throw new UnprocesableException("Error: user is null.");
        if (usuario.length()<6 || usuario.length()>10) throw new UnprocesableException("Error: user length must be between 6 and 10 characters");
        if (personaInputDTO.getPassword()==null) throw new UnprocesableException("Error: password is null.");
        if (personaInputDTO.getName()==null) throw new UnprocesableException("Error: name is null.");
        if (personaInputDTO.getCompanyEmail()==null) throw new UnprocesableException("Error: Company_email is null.");
        if (personaInputDTO.getPersonalEmail()==null) throw new UnprocesableException("Error: Personal_email is null.");
        if (personaInputDTO.getCity()==null) throw new UnprocesableException("Error: City is null.");
        if (personaInputDTO.getActive()==null) throw new UnprocesableException("Error: Active is null");
        if (personaInputDTO.getCreatedDate()==null) throw new UnprocesableException("Error: Created_date is null");
    }
}

