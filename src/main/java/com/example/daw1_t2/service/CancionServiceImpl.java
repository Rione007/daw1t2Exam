package com.example.daw1_t2.service;

import com.example.daw1_t2.entity.Cancion;
import com.example.daw1_t2.repository.CancionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CancionServiceImpl implements CancionService {

    @Autowired
    private CancionRepository cancionRepository;

    @Override
    public List<Cancion> listarTodas() {
        return (List<Cancion>) cancionRepository.findAll();
    }

    @Override
    public Cancion obtenerPorId(Integer id) {
        Optional<Cancion> cancion = cancionRepository.findById(id);
        return cancion.orElse(null);
    }

    @Override
    public void guardar(Cancion cancion) {
        cancionRepository.save(cancion);
    }

    @Override
    public void eliminar(Integer id) {
        cancionRepository.deleteById(id);
    }
    @Override
    public Cancion guardarYDevolver(Cancion cancion) {
        return cancionRepository.save(cancion);
    }
}
