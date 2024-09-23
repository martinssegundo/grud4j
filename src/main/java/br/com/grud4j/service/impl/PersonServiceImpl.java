package br.com.grud4j.service.impl;

import br.com.grud4j.api.dto.PersonDTO;
import br.com.grud4j.data.Person;
import br.com.grud4j.service.PersonService;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class PersonServiceImpl implements PersonService {
    @Override
    public Uni<List<Person>> findByName(String name) {
        return Person.findByName(name);
    }

    @Override
    public Uni<List<Person>> listAll() {
        return Person.listAll();
    }

    @Override
    public Uni<Void> createPerson(PersonDTO personDTO) {
        var person  = Person.builder()
                .birthday(personDTO.birthDay())
                .name(personDTO.name())
                .build();
        return person
                .persistAndFlush()
                .onFailure().invoke(ex -> {
                    System.err.println("Error persisting person: " + ex.getMessage());
                })
                .replaceWithVoid();

    }


}
