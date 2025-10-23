package org.example.model;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Entity
public class Lote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private int quantidadeInicial;
    private int quantidadeAtual;

    private int perdasDiarias;
    private int posturaDiaria;

    private LocalDate dataEntrada;
    private LocalDate ultimaVacinacao;
    private LocalDate proximaVacinacao;

    private boolean emPostura;

    @ManyToOne
    @JoinColumn(name = "animal_id")
    private Animal raca;

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

    public int getQuantidadeInicial() {
        return quantidadeInicial;
    }

    public void setQuantidadeInicial(int quantidadeInicial) {
        this.quantidadeInicial = quantidadeInicial;
    }

    public int getQuantidadeAtual() {
        return quantidadeAtual;
    }

    public void setQuantidadeAtual(int quantidadeAtual) {
        this.quantidadeAtual = quantidadeAtual;
    }

    public int getPerdasDiarias() {
        return perdasDiarias;
    }

    public void setPerdasDiarias(int perdasDiarias) {
        this.perdasDiarias = perdasDiarias;
    }

    public int getPosturaDiaria() {
        return posturaDiaria;
    }

    public void setPosturaDiaria(int posturaDiaria) {
        this.posturaDiaria = posturaDiaria;
    }

    public LocalDate getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(LocalDate dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public LocalDate getUltimaVacinacao() {
        return ultimaVacinacao;
    }

    public void setUltimaVacinacao(LocalDate ultimaVacinacao) {
        this.ultimaVacinacao = ultimaVacinacao;
    }

    public LocalDate getProximaVacinacao() {
        return proximaVacinacao;
    }

    public void setProximaVacinacao(LocalDate proximaVacinacao) {
        this.proximaVacinacao = proximaVacinacao;
    }

    public boolean isEmPostura() {
        return emPostura;
    }

    public void setEmPostura(boolean emPostura) {
        this.emPostura = emPostura;
    }

    public Animal getRaca() {
        return raca;
    }

    public void setRaca(Animal raca) {
        this.raca = raca;
    }
}
