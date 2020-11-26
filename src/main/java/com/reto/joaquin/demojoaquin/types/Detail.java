package com.reto.joaquin.demojoaquin.types;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Detail {
    private String moneda;
    private double monto;
}
