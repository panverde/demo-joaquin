package com.reto.joaquin.demojoaquin.service.impl;

import com.reto.joaquin.demojoaquin.business.MonedaExchangeBusiness;
import com.reto.joaquin.demojoaquin.entity.Moneda;
import com.reto.joaquin.demojoaquin.repository.MonedaRepository;
import com.reto.joaquin.demojoaquin.service.MonedaExchangeService;
import com.reto.joaquin.demojoaquin.types.MonedaExchangeResponse;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MonedaExchangeServiceImpl implements MonedaExchangeService {

    @Autowired
    private MonedaRepository monedaRepository;

    @Override
    public Flowable<Moneda> listAllMonedaExchange() {
        return monedaRepository.findAll();
    }

    @Override
    public Maybe<MonedaExchangeResponse> convertMoneda(String monedaOrigenId, double monto, String monedaDestinoId) {

        return monedaRepository.findByName(monedaOrigenId)
                .flatMap(monedaExchangeOrigen-> monedaRepository.findByName(monedaDestinoId)
                    .map(monedaExchangeDestino -> MonedaExchangeBusiness.MonedaToResponse(monedaExchangeOrigen, monedaExchangeDestino,monto)));
    }

    @Override
    public Maybe<String> updateMonedaExchange(String monedaId, double newMonedaExchange) {
        return monedaRepository.findById(monedaId)
                .flatMap(monedaExchange -> {
                    monedaExchange.setPerUsd(newMonedaExchange);
                    return monedaRepository.save(monedaExchange)
                            .toMaybe()
                            .map(currencyRateNew -> "update successful");
                });
    }
}
