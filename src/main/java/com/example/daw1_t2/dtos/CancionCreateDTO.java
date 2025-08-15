package com.example.daw1_t2.dtos;

public record CancionCreateDTO(
        String titulo,
        Integer duracionSegundos,
        String fechaLanzamiento, // formato yyyy-MM-dd
        Integer reproducciones,
        Integer artistaId
) {}
