package com.example.daw1_t2.service;

import com.example.daw1_t2.entity.Cancion;
import com.example.daw1_t2.repository.CancionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CancionService {

    private final CancionRepository repo;

    public CancionService(CancionRepository repo) {
        this.repo = repo;
    }

    public List<Cancion> listarTodas() {
        return (List<Cancion>) repo.findAll();
    }

    public Cancion obtenerPorId(Integer id) {
        return repo.findById(id).orElse(null);
    }

    public void guardar(Cancion cancion) {
        repo.save(cancion);
    }

    public Cancion guardarYDevolver(Cancion cancion) {
        return repo.save(cancion);
    }

    public void eliminar(Integer id) {
        repo.deleteById(id);
    }
}
