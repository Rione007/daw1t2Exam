package com.example.daw1_t2.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "artista")
public class Artista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_artista")
    private Integer id;

    @Column(name = "nombre_artista", length = 100, nullable = false)
    private String nombre;

    @Column(name = "pais_origen", length = 50)
    private String paisOrigen;

    @Enumerated(EnumType.STRING)
    @Column(name = "genero_musical", length = 50)
    private GeneroMusical generoMusical;

    @Column(name = "fecha_debut")
    private LocalDate fechaDebut;

    @OneToMany(mappedBy = "artista", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cancion> canciones;


    public Artista() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPaisOrigen() {
        return paisOrigen;
    }

    public void setPaisOrigen(String paisOrigen) {
        this.paisOrigen = paisOrigen;
    }

    public GeneroMusical getGeneroMusical() {
        return generoMusical;
    }

    public void setGeneroMusical(GeneroMusical generoMusical) {
        this.generoMusical = generoMusical;
    }

    public LocalDate getFechaDebut() {
        return fechaDebut;
    }

    public void setFechaDebut(LocalDate fechaDebut) {
        this.fechaDebut = fechaDebut;
    }

    public List<Cancion> getCanciones() {
        return canciones;
    }

    public void setCanciones(List<Cancion> canciones) {
        this.canciones = canciones;
    }
}
