package com.example.encuestas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/respuestas")
@CrossOrigin(origins = "*")
public class RespuestaController {

    @Autowired
    private RespuestaRepository respuestaRepository;

    @Autowired
    private PreguntaRepository preguntaRepository;

    // POST /api/respuestas -> guarda una respuesta nueva
    @PostMapping
    public Respuesta crear(@RequestBody Respuesta nuevaRespuesta) {
        return respuestaRepository.save(nuevaRespuesta);
    }

    // GET /api/respuestas/pregunta/5 -> todas las respuestas de la pregunta con id 5
    @GetMapping("/pregunta/{preguntaId}")
    public List<Respuesta> listarPorPregunta(@PathVariable Long preguntaId) {
        return respuestaRepository.findAll().stream()
                .filter(r -> r.getPregunta() != null && r.getPregunta().getId().equals(preguntaId))
                .toList();
    }
}