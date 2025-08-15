// Parte A - DTO para respuesta de canciones por género
package com.example.daw1_t2.dtos;

import java.time.LocalDate;

public class CancionGeneroDTO {
    private String titulo;
    private Integer duracionSegundos;
    private LocalDate fechaLanzamiento;
    private Integer numeroReproducciones;
    private String artistaNombre;
    private String artistaPais;

    public CancionGeneroDTO(String titulo, Integer duracionSegundos, LocalDate fechaLanzamiento,
                            Integer numeroReproducciones, String artistaNombre, String artistaPais) {
        this.titulo = titulo;
        this.duracionSegundos = duracionSegundos;
        this.fechaLanzamiento = fechaLanzamiento;
        this.numeroReproducciones = numeroReproducciones;
        this.artistaNombre = artistaNombre;
        this.artistaPais = artistaPais;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getDuracionSegundos() {
        return duracionSegundos;
    }

    public void setDuracionSegundos(Integer duracionSegundos) {
        this.duracionSegundos = duracionSegundos;
    }

    public LocalDate getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(LocalDate fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public Integer getNumeroReproducciones() {
        return numeroReproducciones;
    }

    public void setNumeroReproducciones(Integer numeroReproducciones) {
        this.numeroReproducciones = numeroReproducciones;
    }

    public String getArtistaNombre() {
        return artistaNombre;
    }

    public void setArtistaNombre(String artistaNombre) {
        this.artistaNombre = artistaNombre;
    }

    public String getArtistaPais() {
        return artistaPais;
    }

    public void setArtistaPais(String artistaPais) {
        this.artistaPais = artistaPais;
    }
}
