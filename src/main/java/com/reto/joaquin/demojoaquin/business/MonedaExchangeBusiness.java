package com.reto.joaquin.demojoaquin.business;

import com.reto.joaquin.demojoaquin.entity.Moneda;
import com.reto.joaquin.demojoaquin.types.Detail;
import com.reto.joaquin.demojoaquin.types.MonedaExchangeResponse;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MonedaExchangeBusiness {

    public static MonedaExchangeResponse MonedaToResponse(Moneda monedaOrigen, Moneda monedaDestino, double monto) {

        return MonedaExchangeResponse.builder()
                .monedaOrigen(Detail.builder()
                        .monto(monto)
                        .moneda(monedaOrigen.getName())
                        .build())
                .monedaDestino(Detail.builder()
                        .monto(roundTwoDecimal((monedaOrigen.getPerUsd() /
                                        (monedaOrigen.getPerUsd() * monedaOrigen.getPerUsd()) * monto * monedaDestino.getPerUsd()),
                                4))
                        .moneda(monedaDestino.getName())
                        .build())
                .build();
    }

    public MonedaExchangeBusiness() {
    }

    private static Double roundTwoDecimal(Double amount, int numberDecimal) {
        return new BigDecimal(amount.toString()).setScale(numberDecimal, RoundingMode.HALF_UP).doubleValue();
    }

}
