package com.reto.joaquin.demojoaquin.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Builder
@Document(collection = "moneda-rate")
@AllArgsConstructor
@NoArgsConstructor
public class Moneda {
    @Id
    private String id;
    private String name;
    private double inUsd;
    private double perUsd;
}
