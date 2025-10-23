package org.example.dto;

public class RelatorioLoteDTO {
    private Long id;
    private String nome;
    private double percentualPerda;
    private boolean posturaBaixa;
    private boolean mortalidadeElevada;

    public RelatorioLoteDTO(Long id, String nome, double percentualPerda, boolean posturaBaixa, boolean mortalidadeElevada) {
        this.id = id;
        this.nome = nome;
        this.percentualPerda = percentualPerda;
        this.posturaBaixa = posturaBaixa;
        this.mortalidadeElevada = mortalidadeElevada;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPercentualPerda() {
        return percentualPerda;
    }

    public void setPercentualPerda(double percentualPerda) {
        this.percentualPerda = percentualPerda;
    }

    public boolean isPosturaBaixa() {
        return posturaBaixa;
    }

    public void setPosturaBaixa(boolean posturaBaixa) {
        this.posturaBaixa = posturaBaixa;
    }

    public boolean isMortalidadeElevada() {
        return mortalidadeElevada;
    }

    public void setMortalidadeElevada(boolean mortalidadeElevada) {
        this.mortalidadeElevada = mortalidadeElevada;
    }
}

