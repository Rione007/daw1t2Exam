package com.example.daw1_t2.dtos;

public record CancionDTO(
        Integer id,
        String titulo,
        Integer duracionSegundos,
        String fechaLanzamiento,
        Integer reproducciones,
        String artista
) {}
