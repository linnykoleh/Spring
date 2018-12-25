package com.pluralsight.web.reactive.controller;

import com.pluralsight.web.reactive.model.Person;
import com.pluralsight.web.reactive.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonRepository repository;

    @Autowired
    public PersonController(PersonRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public Flux<Person> list() {
        final List<Person> people = repository.findAll();

        final Flux<Person> fluxPeople = Flux.fromIterable(people);

        return fluxPeople;
    }

    @GetMapping("{id}")
    public Mono<Person> findById(@PathVariable Long id) {
        final Optional<Person> person = repository.findById(id);

        return Mono.just(person.orElse(Person.empty()));
    }

}
