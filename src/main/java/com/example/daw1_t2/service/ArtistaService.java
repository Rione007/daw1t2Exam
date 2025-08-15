package com.example.daw1_t2.service;

import com.example.daw1_t2.entity.Artista;
import com.example.daw1_t2.repository.ArtistaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistaService {

    private final ArtistaRepository repo;

    public ArtistaService(ArtistaRepository repo) {
        this.repo = repo;
    }

    public List<Artista> listarTodos() {
        return (List<Artista>) repo.findAll();
    }

    public Artista obtenerPorId(Integer id) {
        return repo.findById(id).orElse(null);
    }

    public void guardar(Artista artista) {
        repo.save(artista);
    }

    public Artista guardarYDevolver(Artista artista) {
        return repo.save(artista);
    }

    public void eliminar(Integer id) {
        repo.deleteById(id);
    }
}
