package com.wesley.lista07_http.model;
import java.util.UUID;
import java.time.LocalDateTime;

public class Tweet {
    private UUID tweetId;
    private String mensagem;
    private boolean editado;
    private LocalDateTime dataCriacao;

    public Tweet(String mensagem) {
        this.tweetId = UUID.randomUUID();
        this.mensagem = mensagem;
        this.editado = false;
        this.dataCriacao = LocalDateTime.now();
    }

    public UUID getTweetId() { return tweetId; }
    public String getMensagem() { return mensagem; }
    public void setMensagem(String mensagem) { this.mensagem = mensagem; }
    public boolean isEditado() { return editado; }
    public void setEditado(boolean editado) { this.editado = editado; }
    public LocalDateTime getDataCriacao() { return dataCriacao; }
}