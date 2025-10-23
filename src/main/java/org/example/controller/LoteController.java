package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.example.dto.RelatorioLoteDTO;
import org.example.model.Lote;
import org.example.model.Vacinacao;
import org.example.service.LoteService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/lotes")
@Tag(name = "Lote", description = "Endpoints para gerenciamento de lotes em produção")
public class LoteController {

    private final LoteService service;

    public LoteController(LoteService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Criar ou atualizar lote", description = "Salva ou atualiza os dados de um lote")
    public Lote salvar(@Valid @RequestBody Lote lote) {
        return service.salvar(lote);
    }

    @GetMapping
    @Operation(summary = "Listar todos os lotes", description = "Retorna uma lista com todos os lotes cadastrados")
    public List<Lote> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar lote por ID", description = "Retorna os dados de um lote específico")
    public Lote buscarPorId(
            @Parameter(description = "ID do lote", example = "1") @PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar lote", description = "Remove um lote do sistema")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }

    @PutMapping("/{id}/ativar-postura")
    @Operation(summary = "Ativar fase de postura", description = "Marca o lote como ativo para cálculo de postura")
    public void ativarPostura(@PathVariable Long id) {
        service.ativarPostura(id);
    }

    @GetMapping("/{id}/percentual-perda")
    @Operation(summary = "Calcular percentual de perda do lote", description = "Retorna a taxa de perda (%) com base na quantidade inicial e atual de aves")
    public double calcularPercentualPerda(
            @Parameter(description = "ID do lote", example = "1") @PathVariable Long id) {
        return service.calcularPercentualPerda(id);
    }

    @GetMapping("/vacinacao-atrasada")
    @Operation(summary = "Listar lotes com vacinação atrasada", description = "Retorna os lotes cuja vacinação está vencida")
    public List<Lote> listarVacinacaoAtrasada() {
        return service.listarVacinacaoAtrasada();
    }

    @GetMapping("/vacinacao-proxima")
    @Operation(summary = "Lotes com vacinação próxima", description = "Retorna lotes que precisam ser vacinados nos próximos dias")
    public List<Lote> vacinacaoProxima(@RequestParam(defaultValue = "7") int dias) {
        return service.lotesParaVacinarEm(dias);
    }

    @PostMapping("/{id}/vacinacao")
    @Operation(summary = "Agendar vacinação", description = "Agenda uma vacinação futura para o lote")
    public Vacinacao agendarVacinacao(
            @PathVariable Long id,
            @RequestParam LocalDate data,
            @RequestParam(required = false) String descricao) {
        return service.agendarVacinacao(id, data, descricao);
    }

    @PutMapping("/vacinacao/{id}/realizar")
    @Operation(summary = "Registrar vacinação realizada", description = "Marca uma vacinação como realizada")
    public void registrarVacinacao(@PathVariable Long id) {
        service.registrarVacinacao(id);
    }

    @GetMapping("/producao-diaria")
    @Operation(summary = "Produção total diária", description = "Retorna a soma da postura diária de todos os lotes em postura")
    public int producaoDiariaTotal() {
        return service.calcularProducaoTotalDiaria();
    }

    @GetMapping("/postura-baixa")
    @Operation(summary = "Lotes com postura abaixo do esperado", description = "Retorna lotes cuja produção está abaixo da expectativa da raça")
    public List<Lote> posturaAbaixoDoEsperado() {
        return service.lotesComPosturaBaixa();
    }

    @GetMapping("/perdas-elevadas")
    @Operation(summary = "Lotes com perdas elevadas", description = "Retorna lotes cuja taxa de perda está acima do limite aceitável da raça")
    public List<Lote> perdasElevadas() {
        return service.lotesComPerdasElevadas();
    }

    @GetMapping("/relatorio")
    @Operation(summary = "Relatório analítico dos lotes", description = "Retorna dados analíticos de cada lote: perdas, postura e mortalidade")
    public List<RelatorioLoteDTO> relatorioAnalitico() {
        return service.gerarRelatorioAnalitico();
    }
}
