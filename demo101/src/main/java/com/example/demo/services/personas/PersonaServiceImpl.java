package com.example.demo.services.personas;

import com.example.demo.entities.Persona;
import com.example.demo.repositories.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class PersonaServiceImpl implements PersonaService {

    private final PersonaRepository personaRepository;

    @Autowired
    public PersonaServiceImpl(PersonaRepository personaRepository) {

        this.personaRepository = personaRepository;
    }

    @Override
    public Persona add(Persona persona) {
        Persona persistedPersona = null;
        System.out.println(personaRepository);
        persistedPersona = personaRepository.save(persona);
        return persistedPersona;
    }
}
