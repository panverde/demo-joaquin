package com.reto.joaquin.demojoaquin.types;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MonedaExchangeResponse {
    private Detail monedaOrigen;
    private Detail monedaDestino;
}
