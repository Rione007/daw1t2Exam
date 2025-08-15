package com.example.daw1_t2.dtos;

public class TopArtistaDTO {
    private String artistaNombre;
    private String artistaPais;
    private String generoMusical;
    private Long totalReproducciones;

    public TopArtistaDTO(String artistaNombre, String artistaPais, String generoMusical, Long totalReproducciones) {
        this.artistaNombre = artistaNombre;
        this.artistaPais = artistaPais;
        this.generoMusical = generoMusical;
        this.totalReproducciones = totalReproducciones;
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

    public String getGeneroMusical() {
        return generoMusical;
    }

    public void setGeneroMusical(String generoMusical) {
        this.generoMusical = generoMusical;
    }

    public Long getTotalReproducciones() {
        return totalReproducciones;
    }

    public void setTotalReproducciones(Long totalReproducciones) {
        this.totalReproducciones = totalReproducciones;
    }
}
