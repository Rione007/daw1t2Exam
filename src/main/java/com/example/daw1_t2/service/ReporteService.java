package com.example.daw1_t2.service;

import com.example.daw1_t2.dtos.CancionGeneroDTO;
import com.example.daw1_t2.dtos.TopArtistaDTO;
import com.example.daw1_t2.entity.Artista;
import com.example.daw1_t2.entity.Cancion;
import com.example.daw1_t2.repository.ArtistaRepository;
import com.example.daw1_t2.repository.CancionRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReporteService {

    private final CancionRepository cancionRepo;
    private final ArtistaRepository artistaRepo;

    public ReporteService(CancionRepository cancionRepo, ArtistaRepository artistaRepo) {
        this.cancionRepo = cancionRepo;
        this.artistaRepo = artistaRepo;
    }


    public Map<String, Object> cancionesPorGenero(String genero) {
        List<CancionGeneroDTO> canciones = cancionRepo.findByGenero(genero);
        Long total = cancionRepo.totalReproduccionesPorGenero(genero);
        return Map.of("canciones", canciones, "total_reproducciones", total != null ? total : 0);
    }

    public List<TopArtistaDTO> topArtistas(int limite) {
        return artistaRepo.topArtistas()
                .stream()
                .limit(limite)
                .toList();
    }


}
