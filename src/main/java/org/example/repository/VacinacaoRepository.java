package org.example.repository;

import org.example.model.Vacinacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface VacinacaoRepository extends JpaRepository<Vacinacao, Long> {
    List<Vacinacao> findByDataVacinacaoAndRealizadaFalse(LocalDate data);
}
