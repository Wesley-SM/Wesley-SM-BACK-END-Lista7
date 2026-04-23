package com.wesley.lista07_http.model;
import java.util.UUID;

public class Ingresso {
    private UUID ingressoId;
    private UUID filmeId;
    private int assentoNumero;

    public Ingresso(UUID filmeId, int assentoNumero) {
        this.ingressoId = UUID.randomUUID();
        this.filmeId = filmeId;
        this.assentoNumero = assentoNumero;
    }

    public UUID getIngressoId() { return ingressoId; }
    public int getAssentoNumero() { return assentoNumero; }
}