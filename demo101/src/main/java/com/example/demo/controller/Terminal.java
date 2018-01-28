package com.example.demo.controller;

import com.example.demo.entities.Persona;
import com.example.demo.services.personas.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class Terminal implements CommandLineRunner {
    @Autowired
    private PersonaService personaService;

    @Override
    public void run(String... strings) throws Exception{
        System.out.println(personaService);
        Persona persona = new Persona("Rangel","Stoilov","rori4","dqpkn65", LocalDate.of(1991, 8, 26),"Male","United Kingdom");
        Persona add = personaService.add(persona);
        System.out.println(add);
    }
}
