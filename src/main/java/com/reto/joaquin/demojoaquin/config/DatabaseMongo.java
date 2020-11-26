package com.reto.joaquin.demojoaquin.config;

import com.reto.joaquin.demojoaquin.entity.Moneda;
import com.reto.joaquin.demojoaquin.repository.MonedaRepository;
import io.reactivex.Flowable;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Log
@Component
public class DatabaseMongo implements CommandLineRunner {

    @Autowired
    private MonedaRepository monedaRepository;


    @Override
    public void run(String... args) throws Exception {

        monedaRepository.deleteAll().subscribe();

        Flowable<Moneda> cambioMoneda = Flowable.fromArray(Moneda.builder()
                        .id("abcSOLES")
                        .name("SOLES")
                        .inUsd(0.28)
                        .perUsd(3.60)
                        .build(),
                Moneda.builder()
                        .id("abcDOLARES")
                        .name("DOLARES")
                        .inUsd(1)
                        .perUsd(1)
                        .build(),
                Moneda.builder()
                        .id("abcYENES")
                        .name("YENES")
                        .inUsd(0.0096)
                        .perUsd(104.24)
                        .build(),
                Moneda.builder()
                        .id("abcEUROS")
                        .name("EUROS")
                        .inUsd(1.19)
                        .perUsd(0.84)
                        .build(),
                Moneda.builder()
                        .id("abcBOLIVARES")
                        .name("BOLIVARES")
                        .inUsd(0.0000011)
                        .perUsd(924349)
                        .build());
        monedaRepository.saveAll(cambioMoneda).subscribe();

    }
}
