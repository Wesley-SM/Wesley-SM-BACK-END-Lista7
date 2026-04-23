package com.wesley.lista07_http.model;
import java.util.UUID;

public class Filme {
    private UUID id;
    private String titulo;
    private String genero;
    private int capacidade;
    private int assentosOcupados;

    public Filme(String titulo, String genero, int capacidade) {
        this.id = UUID.randomUUID();
        this.titulo = titulo;
        this.genero = genero;
        this.capacidade = capacidade;
        this.assentosOcupados = 0;
    }

    public UUID getId() { return id; }
    public String getTitulo() { return titulo; }
    public int getCapacidade() { return capacidade; }
    public int getAssentosOcupados() { return assentosOcupados; }
    public void setAssentosOcupados(int assentosOcupados) { this.assentosOcupados = assentosOcupados; }
}