package com.example.daw1_t2.repository;

import com.example.daw1_t2.dtos.TopArtistaDTO;
import com.example.daw1_t2.entity.Artista;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ArtistaRepository extends CrudRepository<Artista, Integer> {
    @Query("""
SELECT new com.example.daw1_t2.dtos.TopArtistaDTO(
    a.nombre,
    a.paisOrigen,
    a.generoMusical.name,
    SUM(c.numeroReproducciones)
)
FROM Artista a
LEFT JOIN a.canciones c
GROUP BY a.id, a.nombre, a.paisOrigen, a.generoMusical
ORDER BY SUM(c.numeroReproducciones) DESC
""")
    List<TopArtistaDTO> topArtistas();


}
