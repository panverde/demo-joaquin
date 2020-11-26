package com.reto.joaquin.demojoaquin.service.impl;

import com.reto.joaquin.demojoaquin.entity.Moneda;
import com.reto.joaquin.demojoaquin.repository.MonedaRepository;
import com.reto.joaquin.demojoaquin.service.MonedaExchangeService;
import com.reto.joaquin.demojoaquin.types.Detail;
import com.reto.joaquin.demojoaquin.types.MonedaExchangeResponse;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

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
                .flatMap(monedaExchangeOrigen->
                        monedaRepository.findByName(monedaDestinoId).map(monedaExchangeDestino ->
                                MonedaToResponsea(monedaExchangeOrigen, monedaExchangeDestino,monto)));
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

    public MonedaExchangeResponse MonedaToResponsea(Moneda monedaOrigen, Moneda monedaDestino, double monto){
        return MonedaExchangeResponse.builder()
                .monedaOrigen(Detail.builder()
                        .monto(monto)
                        .moneda(monedaOrigen.getName())
                        .build())
                .monedaDestino(Detail.builder()
                        .monto(roundTwoDecimal((monedaOrigen.getPerUsd() /(monedaOrigen.getPerUsd() * monedaOrigen.getPerUsd()) * monto * monedaDestino.getPerUsd()),
                                4))
                        .moneda(monedaDestino.getName())
                        .build())
                .build();
    }

    private static Double roundTwoDecimal(Double amount, int numberDecimal) {
        return new BigDecimal(amount.toString()).setScale(numberDecimal, RoundingMode.HALF_UP).doubleValue();
    }

}
