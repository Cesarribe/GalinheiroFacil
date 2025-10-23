package org.example.service;

import org.example.model.Lote;
import org.example.model.Vacinacao;
import org.example.repository.LoteRepository;
import org.example.repository.VacinacaoRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class VacinacaoService {

    private final VacinacaoRepository vacinacaoRepository;
    private final LoteRepository loteRepository;

    public VacinacaoService(VacinacaoRepository vacinacaoRepository, LoteRepository loteRepository) {
        this.vacinacaoRepository = vacinacaoRepository;
        this.loteRepository = loteRepository;
    }

    public Vacinacao agendarVacinacao(Long idLote, LocalDate data, String descricao) {
        Lote lote = loteRepository.findById(idLote)
                .orElseThrow(() -> new RuntimeException("Lote não encontrado"));

        Vacinacao vacinacao = new Vacinacao();
        vacinacao.setLote(lote);
        vacinacao.setDataVacinacao(data);
        vacinacao.setDescricao(descricao);
        vacinacao.setRealizada(false);

        return vacinacaoRepository.save(vacinacao);
    }

    public void registrarVacinacao(Long idVacinacao) {
        Vacinacao vacinacao = vacinacaoRepository.findById(idVacinacao)
                .orElseThrow(() -> new RuntimeException("Vacinação não encontrada"));

        vacinacao.setRealizada(true);
        vacinacaoRepository.save(vacinacao);
    }

    @Scheduled(cron = "0 0 8 * * *") // todo dia às 8h
    public void alertarVacinasProximas() {
        LocalDate alerta = LocalDate.now().plusDays(3);
        List<Vacinacao> proximas = vacinacaoRepository.findByDataVacinacaoAndRealizadaFalse(alerta);

        for (Vacinacao vac : proximas) {
            System.out.println("📢 Faltam 3 dias para vacinar o lote " + vac.getLote().getNome());
            // implementar futuramente
        }
    }

    @Scheduled(cron = "0 0 9 * * *") // todo dia às 9h
    public void perguntarVacinasDoDia() {
        LocalDate hoje = LocalDate.now();
        List<Vacinacao> vacinasHoje = vacinacaoRepository.findByDataVacinacaoAndRealizadaFalse(hoje);

        for (Vacinacao vac : vacinasHoje) {
            System.out.println("⚠️ Hoje é dia de vacinar o lote " + vac.getLote().getNome() + ". Deseja registrar?");
        }
    }
}

