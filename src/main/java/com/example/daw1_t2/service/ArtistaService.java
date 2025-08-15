package com.example.daw1_t2.service;

import com.example.daw1_t2.entity.Artista;

import java.util.List;

public interface ArtistaService {
    List<Artista> listarTodos();
    Artista obtenerPorId(Integer id);
    void guardar(Artista artista);
    void eliminar(Integer id);
    public Artista guardarYDevolver(Artista artista);
}