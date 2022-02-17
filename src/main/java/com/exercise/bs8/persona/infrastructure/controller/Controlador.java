package com.exercise.bs8.persona.infrastructure.controller;

import com.exercise.bs8.persona.application.IPersona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controlador {

    @Autowired
    IPersona personaService;

    @GetMapping
    public ResponseEntity<List<PersonaOutputDTO>> getLista()
    {
        return new ResponseEntity<>(personaService.getAllPersona(), HttpStatus.OK);
    }

    @GetMapping("/persona/{id}")
    public ResponseEntity<PersonaOutputDTO> getById(@PathVariable Integer id) throws Exception
    {
        return new ResponseEntity<>(personaService.getPersonaById(id), HttpStatus.OK);
    }

    @GetMapping("/usuario/{usuario}")
    public ResponseEntity<List<PersonaOutputDTO>> getByNombre(@PathVariable String usuario)
    {
        return new ResponseEntity<>(personaService.getPersonaByUsuario(usuario), HttpStatus.OK);
    }

    @PostMapping("/persona")
    public ResponseEntity<PersonaOutputDTO> addPersona(@RequestBody PersonaInputDTO personaInputDTO) throws Exception
    {
        return new ResponseEntity<>(personaService.addPersona(personaInputDTO), HttpStatus.OK);
    }

    @PutMapping("/persona")
    public ResponseEntity<PersonaOutputDTO> setPersona(@RequestBody PersonaInputDTO personaInputDTO) throws Exception
    {
        return new ResponseEntity<>(personaService.setPersona(personaInputDTO), HttpStatus.OK);
    }

    @DeleteMapping("/persona/{id}")
    public ResponseEntity<PersonaOutputDTO> delById(@PathVariable Integer id) throws Exception
    {
        return new ResponseEntity<>(personaService.delPersona(id), HttpStatus.OK);
    }

}

