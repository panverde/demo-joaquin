package com.reto.joaquin.demojoaquin.service;

import com.reto.joaquin.demojoaquin.entity.Moneda;
import com.reto.joaquin.demojoaquin.types.MonedaExchangeResponse;
import io.reactivex.Flowable;
import io.reactivex.Maybe;

public interface MonedaExchangeService {

    Flowable<Moneda> listAllMonedaExchange();

    Maybe<MonedaExchangeResponse> convertMoneda(String monedaOrigenId, double monto, String monedaDestinoId);

    Maybe<String> updateMonedaExchange(String monedaId, double newMonedaExchange);

}
