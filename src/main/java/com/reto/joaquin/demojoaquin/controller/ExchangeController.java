package com.reto.joaquin.demojoaquin.controller;

import com.reto.joaquin.demojoaquin.entity.Moneda;
import com.reto.joaquin.demojoaquin.repository.MonedaRepository;
import com.reto.joaquin.demojoaquin.service.MonedaExchangeService;
import com.reto.joaquin.demojoaquin.types.MonedaExchangeResponse;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/")
public class ExchangeController {

    @Autowired
    private MonedaExchangeService monedaExchangeService;

    @Autowired
    private MonedaRepository monedaRepository;

    @GetMapping("")
    public Flowable<Moneda> getAllCurrency() {
        return monedaExchangeService.listAllMonedaExchange();
    }

    @GetMapping(value = "/convert")
    public Maybe<MonedaExchangeResponse> convertCurrency(@RequestParam() String monedaOrigen,
                                                         @RequestParam() double monto,
                                                         @RequestParam String monedaDestino) {
        return monedaExchangeService.convertMoneda(monedaOrigen, monto, monedaDestino);
    }

    @PostMapping("/{codMoneda}")
    public Maybe<String> convertCurrency(@RequestParam() double newExchange,
                                         @PathVariable() String codMoneda) {
        return monedaExchangeService.updateMonedaExchange(codMoneda, newExchange);
    }

    @GetMapping("id")
    public Maybe<Moneda> convertCurrency(@RequestParam() String originCurrencyId) {
        return monedaRepository.findById(originCurrencyId);
    }
}
