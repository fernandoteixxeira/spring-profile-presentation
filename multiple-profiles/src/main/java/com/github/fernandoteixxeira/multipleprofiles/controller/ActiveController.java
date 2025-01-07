package com.github.fernandoteixxeira.multipleprofiles.controller;

import com.github.fernandoteixxeira.multipleprofiles.config.ActiveProperties;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static lombok.AccessLevel.PRIVATE;

@RestController
@RequestMapping("/active")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class ActiveController {
    ActiveProperties activeProperties;

    @GetMapping
    public ResponseEntity<ActiveProperties> getPerson() {
        return ResponseEntity.ok(activeProperties);
    }
}
