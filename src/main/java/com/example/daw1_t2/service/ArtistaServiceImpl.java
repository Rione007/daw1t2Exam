package com.example.daw1_t2.service;


import com.example.daw1_t2.entity.Artista;
import com.example.daw1_t2.repository.ArtistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistaServiceImpl implements ArtistaService {

    @Autowired
    private ArtistaRepository artistaRepository;

    @Override
    public List<Artista> listarTodos() {
        return (List<Artista>) artistaRepository.findAll();
    }

    @Override
    public Artista obtenerPorId(Integer id) {
        Optional<Artista> artista = artistaRepository.findById(id);
        return artista.orElse(null);
    }

    @Override
    public void guardar(Artista artista) {
        artistaRepository.save(artista);
    }

    @Override
    public void eliminar(Integer id) {
        artistaRepository.deleteById(id);
    }
    @Override
    public Artista guardarYDevolver(Artista artista) {
        return artistaRepository.save(artista);
    }
}