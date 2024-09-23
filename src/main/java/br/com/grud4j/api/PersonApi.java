package br.com.grud4j.api;

import br.com.grud4j.api.dto.PersonDTO;
import br.com.grud4j.service.PersonService;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/pessoa")
public class PersonApi {

    private final PersonService personService;


    @Inject
    public PersonApi(PersonService personService) {
        this.personService = personService;
    }


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Uni<Response> createPerson(PersonDTO personDTO) {
        return personService.createPerson(personDTO)
                .map(response -> Response.status(Response.Status.CREATED).build());

    }


    @GET
    @Path("{name}")
    public Uni<List<PersonDTO>> getPerson(@PathParam("name") String name) {
        return personService.findByName(name)
                .map(persons ->
                        persons.stream()
                                .map(person ->
                                        PersonDTO.builder()
                                                .birthDay(person.getBirthday())
                                                .name(person.getName())
                                                .build()
                                )
                                .toList()
                );
    }

    @GET
    public Uni<List<PersonDTO>> listAll() {
        return personService.listAll()
                .map(persons -> persons.stream()
                        .map(person ->
                                PersonDTO.builder()
                                        .birthDay(person.getBirthday())
                                        .name(person.getName())
                                        .build()
                        )
                        .toList()
                );
    }
}
