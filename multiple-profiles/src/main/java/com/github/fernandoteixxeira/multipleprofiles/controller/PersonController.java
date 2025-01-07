package com.github.fernandoteixxeira.multipleprofiles.controller;

import com.github.fernandoteixxeira.multipleprofiles.config.PersonProperties;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static lombok.AccessLevel.PRIVATE;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class PersonController {
    PersonProperties personProperties;

    @GetMapping
    public ResponseEntity<PersonProperties> getPerson() {
        return ResponseEntity.ok(personProperties);
    }
}
