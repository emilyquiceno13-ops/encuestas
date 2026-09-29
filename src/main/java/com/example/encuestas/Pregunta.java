package com.example.encuestas;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Pregunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String texto;

    // "TEXTO" = respuesta libre, "OPCION_MULTIPLE" = elegir entre varias opciones
    private String tipo;

    @ElementCollection
    private List<String> opciones = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "encuesta_id")
    @JsonIgnore
    private Encuesta encuesta;

    // Getters y setters (necesarios para que Spring pueda leer/escribir estos campos)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTexto() { return texto; }
    public void setTexto(String texto) { this.texto = texto; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public List<String> getOpciones() { return opciones; }
    public void setOpciones(List<String> opciones) { this.opciones = opciones; }

    public Encuesta getEncuesta() { return encuesta; }
    public void setEncuesta(Encuesta encuesta) { this.encuesta = encuesta; }
}