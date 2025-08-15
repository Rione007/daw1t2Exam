package com.example.daw1_t2.repository;

import com.example.daw1_t2.dtos.CancionGeneroDTO;
import com.example.daw1_t2.entity.Cancion;
import com.example.daw1_t2.entity.GeneroMusical;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CancionRepository extends CrudRepository<Cancion, Integer> {

    // Parte A
    @Query("""
        SELECT new com.example.daw1_t2.dtos.CancionGeneroDTO(
            c.titulo, c.duracionSegundos, c.fechaLanzamiento,
            c.numeroReproducciones, a.nombre, a.paisOrigen
        )
        FROM Cancion c
        JOIN c.artista a
        WHERE a.generoMusical = :genero
    """)
    List<CancionGeneroDTO> findByGenero(String genero);

    // Total reproducciones por género (Parte A)
    @Query("""
        SELECT SUM(c.numeroReproducciones)
        FROM Cancion c
        JOIN c.artista a
        WHERE a.generoMusical = :genero
    """)
    Long totalReproduccionesPorGenero(String genero);
}