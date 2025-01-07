package com.github.fernandoteixxeira.multipleprofiles.controller;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@RestController
@RequestMapping("/profiles")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class SetProfilesController {
    @Value("${spring.profiles.active}")
    List<String> active;

    @GetMapping
    public ResponseEntity<List<String>> getProfiles() {
        return ResponseEntity.ok(active);
    }
}
