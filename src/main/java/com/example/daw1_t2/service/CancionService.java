package com.example.daw1_t2.service;

import com.example.daw1_t2.entity.Cancion;

import java.util.List;

public interface CancionService {
    List<Cancion> listarTodas();
    Cancion obtenerPorId(Integer id);
    void guardar(Cancion cancion);
    void eliminar(Integer id);
    public Cancion guardarYDevolver(Cancion cancion);
}