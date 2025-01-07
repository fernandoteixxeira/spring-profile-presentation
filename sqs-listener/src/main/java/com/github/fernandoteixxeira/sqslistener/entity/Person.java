package com.github.fernandoteixxeira.sqslistener.entity;

import java.time.LocalDate;

public record Person(String id, String name, LocalDate birthdate) {
}
