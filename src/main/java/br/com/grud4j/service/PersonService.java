package br.com.grud4j.service;

import br.com.grud4j.api.dto.PersonDTO;
import br.com.grud4j.data.Person;
import io.smallrye.mutiny.Uni;

import java.util.List;

public interface PersonService {
    Uni<List<Person>> findByName(String name);
    Uni<List<Person>> listAll();
    Uni<Void> createPerson(PersonDTO personDTO);
}

