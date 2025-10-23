package org.example.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome da raça é obrigatório")
    private String nomeRaca;

    @Positive(message = "O consumo diário deve ser positivo")
    private double consumoDiario;

    @PositiveOrZero(message = "A postura esperada não pode ser negativa")
    private double posturaEsperada;

    @DecimalMin(value = "0.0", inclusive = true)
    @DecimalMax(value = "100.0", inclusive = true)
    private double taxaMortalidadeMensalAceitavel;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeRaca() {
        return nomeRaca;
    }

    public void setNomeRaca(String nomeRaca) {
        this.nomeRaca = nomeRaca;
    }

    public double getConsumoDiario() {
        return consumoDiario;
    }

    public void setConsumoDiario(double consumoDiario) {
        this.consumoDiario = consumoDiario;
    }

    public double getPosturaEsperada() {
        return posturaEsperada;
    }

    public void setPosturaEsperada(double posturaEsperada) {
        this.posturaEsperada = posturaEsperada;
    }

    public double getTaxaMortalidadeMensalAceitavel() {
        return taxaMortalidadeMensalAceitavel;
    }

    public void setTaxaMortalidadeMensalAceitavel(double taxaMortalidadeMensalAceitavel) {
        this.taxaMortalidadeMensalAceitavel = taxaMortalidadeMensalAceitavel;
    }

}
