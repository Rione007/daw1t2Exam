package com.example.daw1_t2.repository;

import com.example.daw1_t2.dtos.TopArtistaDTO;
import com.example.daw1_t2.entity.Artista;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ArtistaRepository extends CrudRepository<Artista, Integer> {


}
