package org.example.service;

import org.example.dto.RelatorioLoteDTO;
import org.example.model.Lote;
import org.example.repository.LoteRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LoteService {

    private final LoteRepository repository;

    public LoteService(LoteRepository repository) {
        this.repository = repository;
    }

    public List<Lote> listarTodos() {
        return repository.findAll();
    }

    public Lote buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lote não encontrado"));
    }

    public Lote salvar(Lote lote) {
        return repository.save(lote);
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Lote não encontrado para exclusão");
        }
        repository.deleteById(id);
    }

    public void ativarPostura(Long id) {
        Lote lote = buscarPorId(id);
        lote.setEmPostura(true);
        repository.save(lote);
    }

    // Análises
    public double calcularPercentualPerda(Long id) {
        Lote lote = buscarPorId(id);
        if (lote.getQuantidadeInicial() == 0) return 0.0;
        int perdas = lote.getQuantidadeInicial() - lote.getQuantidadeAtual();
        return (double) perdas / lote.getQuantidadeInicial() * 100;
    }

    public boolean posturaAbaixoDoEsperado(Lote lote) {
        if (!lote.isEmPostura() || lote.getQuantidadeAtual() == 0) return false;
        double taxa = (double) lote.getPosturaDiaria() / lote.getQuantidadeAtual();
        return taxa < lote.getRaca().getPosturaEsperada();
    }

    public boolean mortalidadeAcimaDoLimite(Lote lote) {
        if (lote.getQuantidadeInicial() == 0) return false;
        int perdas = lote.getQuantidadeInicial() - lote.getQuantidadeAtual();
        double taxa = (double) perdas / lote.getQuantidadeInicial();
        return taxa > (lote.getRaca().getTaxaMortalidadeMensalAceitavel() / 100.0);
    }

    public List<Lote> listarVacinacaoAtrasada() {
        LocalDate hoje = LocalDate.now();
        return repository.findAll().stream()
                .filter(l -> l.getProximaVacinacao() != null && hoje.isAfter(l.getProximaVacinacao()))
                .toList();
    }

    public List<Lote> lotesParaVacinarEm(int dias) {
        LocalDate hoje = LocalDate.now();
        LocalDate limite = hoje.plusDays(dias);
        return repository.findAll().stream()
                .filter(l -> l.getProximaVacinacao() != null && !l.getProximaVacinacao().isAfter(limite))
                .toList();
    }

    public int calcularProducaoTotalDiaria() {
        return repository.findAll().stream()
                .filter(Lote::isEmPostura)
                .mapToInt(Lote::getPosturaDiaria)
                .sum();
    }

    public List<Lote> lotesComPosturaBaixa() {
        return repository.findAll().stream()
                .filter(this::posturaAbaixoDoEsperado)
                .toList();
    }

    public List<Lote> lotesComPerdasElevadas() {
        return repository.findAll().stream()
                .filter(this::mortalidadeAcimaDoLimite)
                .toList();
    }

    public List<RelatorioLoteDTO> gerarRelatorioAnalitico() {
        return repository.findAll().stream()
                .map(lote -> new RelatorioLoteDTO(
                        lote.getId(),
                        lote.getNome(),
                        calcularPercentualPerda(lote.getId()),
                        posturaAbaixoDoEsperado(lote),
                        mortalidadeAcimaDoLimite(lote)
                ))
                .toList();
    }

}
