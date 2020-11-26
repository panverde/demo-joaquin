package com.reto.joaquin.demojoaquin.repository;

import com.reto.joaquin.demojoaquin.entity.Moneda;
import io.reactivex.Maybe;
import org.springframework.data.repository.reactive.RxJava2CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonedaRepository extends RxJava2CrudRepository<Moneda,String> {

    Maybe<Moneda> findByName(String name);

}
