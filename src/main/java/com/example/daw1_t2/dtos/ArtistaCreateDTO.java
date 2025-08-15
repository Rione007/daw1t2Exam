package com.example.daw1_t2.dtos;

public record ArtistaCreateDTO(
        String nombre,
        String paisOrigen,
        String generoMusical, // Se enviará como string: "POP", "ROCK", etc.
        String fechaDebut     // En formato "yyyy-MM-dd"
) {}
