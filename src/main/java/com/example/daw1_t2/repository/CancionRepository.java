package com.example.daw1_t2.repository;

import com.example.daw1_t2.entity.Cancion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CancionRepository extends CrudRepository<Cancion, Integer> {

}