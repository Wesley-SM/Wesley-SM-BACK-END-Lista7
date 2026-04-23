package com.wesley.lista07_http.model;
import java.util.UUID;
import java.time.LocalDate;

public class Emprestimo {
    private UUID emprestimoId;
    private UUID livroId;
    private String usuarioId;
    private LocalDate dataEmprestimo;

    public Emprestimo(UUID livroId, String usuarioId) {
        this.emprestimoId = UUID.randomUUID();
        this.livroId = livroId;
        this.usuarioId = usuarioId;
        this.dataEmprestimo = LocalDate.now();
    }

    public UUID getEmprestimoId() { return emprestimoId; }
    public UUID getLivroId() { return livroId; }
    public String getUsuarioId() { return usuarioId; }
    public LocalDate getDataEmprestimo() { return dataEmprestimo; }
}