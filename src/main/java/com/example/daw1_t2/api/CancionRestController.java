package com.example.daw1_t2.api;

import com.example.daw1_t2.dtos.CancionCreateDTO;
import com.example.daw1_t2.dtos.CancionDTO;
import com.example.daw1_t2.entity.Artista;
import com.example.daw1_t2.entity.Cancion;
import com.example.daw1_t2.service.ArtistaService;
import com.example.daw1_t2.service.CancionService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/canciones")
@CrossOrigin(origins = "*")
public class CancionRestController {

    private final CancionService cancionService;
    private final ArtistaService artistaService;

    public CancionRestController(CancionService cancionService, ArtistaService artistaService) {
        this.cancionService = cancionService;
        this.artistaService = artistaService;
    }

    @GetMapping
    public ResponseEntity<List<Cancion>> listarCanciones() {
        return ResponseEntity.ok(cancionService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CancionDTO> obtenerCancion(@PathVariable Integer id) {
        Cancion c = cancionService.obtenerPorId(id);
        if (c == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(new CancionDTO(
                c.getId(),
                c.getTitulo(),
                c.getDuracionSegundos(),
                c.getFechaLanzamiento() != null ? c.getFechaLanzamiento().toString() : null,
                c.getNumeroReproducciones(),
                c.getArtista().getNombre()
        ));
    }

    @PostMapping
    public ResponseEntity<CancionDTO> crearCancion(@RequestBody CancionCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(guardarCancionDesdeDTO(null, dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CancionDTO> actualizarCancion(@PathVariable Integer id, @RequestBody CancionCreateDTO dto) {
        if (cancionService.obtenerPorId(id) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(guardarCancionDesdeDTO(id, dto));
    }

    private CancionDTO guardarCancionDesdeDTO(Integer id, CancionCreateDTO dto) {
        Artista artista = artistaService.obtenerPorId(dto.artistaId());
        if (artista == null) {
            throw new IllegalArgumentException("El artista no existe");
        }

        Cancion c = (id != null) ? cancionService.obtenerPorId(id) : new Cancion();
        c.setTitulo(dto.titulo());
        c.setDuracionSegundos(dto.duracionSegundos());
        if (dto.fechaLanzamiento() != null) {
            c.setFechaLanzamiento(LocalDate.parse(dto.fechaLanzamiento()));
        } else {
            c.setFechaLanzamiento(null);
        }
        c.setNumeroReproducciones(dto.reproducciones());
        c.setArtista(artista);

        Cancion guardada = cancionService.guardarYDevolver(c);

        return new CancionDTO(
                guardada.getId(),
                guardada.getTitulo(),
                guardada.getDuracionSegundos(),
                guardada.getFechaLanzamiento() != null ? guardada.getFechaLanzamiento().toString() : null,
                guardada.getNumeroReproducciones(),
                artista.getNombre()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCancion(@PathVariable Integer id) {
        if (cancionService.obtenerPorId(id) == null) {
            return ResponseEntity.notFound().build();
        }
        cancionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
